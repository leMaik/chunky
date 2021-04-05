/* Copyright (c) 2016 Jesper Öqvist <jesper@llbit.se>
 *
 * This file is part of Chunky.
 *
 * Chunky is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Chunky is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * You should have received a copy of the GNU General Public License
 * along with Chunky.  If not, see <http://www.gnu.org/licenses/>.
 */
package se.llbit.chunky.ui;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.function.Consumer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.Property;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.util.converter.NumberStringConverter;

/**
 * A control for editing numeric values with a text field.
 */
public abstract class Adjuster<T extends Number> extends HBox {

  private final StringProperty name = new SimpleStringProperty("Name");
  protected final Label nameLbl = new Label();
  protected final TextField valueField = new TextField();
  protected final Property<Number> value;
  private ChangeListener<Number> listener;
  private double dragStart;
  private double dragStartScreenX;
  private double dragStartScreenY;
  private double firstDragStart;
  private double delta = 0;
  private double dragStartValue;
  private boolean altWasPressed;
  private boolean shiftWasPressed;

  protected Adjuster(Property<Number> value) {
    this.value = value;
    nameLbl.textProperty().bind(Bindings.concat(name, ":"));
    nameLbl.setOnMousePressed(this::handleDragStart);
    nameLbl.setOnMouseDragged(this::handleDragged);
    nameLbl.setOnMouseReleased(this::handleReleased);
    nameLbl.setCursor(Cursor.H_RESIZE);
    setAlignment(Pos.CENTER_LEFT);
    setSpacing(10);
    valueField.setPrefWidth(103);
    valueField.textProperty().bindBidirectional(value, new NumberStringConverter());
    getChildren().addAll(nameLbl, valueField);
  }

  private void handleReleased(MouseEvent mouseEvent) {
    nameLbl.setCursor(Cursor.H_RESIZE);
    try {
      new Robot().mouseMove((int) dragStartScreenX, (int) dragStartScreenY);
    } catch (AWTException e) {
      e.printStackTrace();
    }
  }

  private void handleDragStart(MouseEvent mouseEvent) {
    dragStart = mouseEvent.getX();
    dragStartScreenX = mouseEvent.getScreenX();
    dragStartScreenY = mouseEvent.getScreenY();
    firstDragStart = dragStart;
    delta = 0;
    dragStartValue = value.getValue().doubleValue();
    altWasPressed = mouseEvent.isAltDown();
    shiftWasPressed = mouseEvent.isShiftDown();
    nameLbl.setCursor(Cursor.NONE);
  }

  private void handleDragged(MouseEvent mouseEvent) {
    // for very smooth dragging (if the delta is very small) we need to save the
    // delta for the current movement (alt+shift combination) and apply that whenever
    // the mouse is moved
    if (altWasPressed != mouseEvent.isAltDown() || shiftWasPressed != mouseEvent.isShiftDown()) {
      dragStartValue = value.getValue().doubleValue();
      this.delta = 0;
    } else {
      double scale = 1;
      if (mouseEvent.isShiftDown()) {
        scale *= 5;
      } else if (mouseEvent.isAltDown()) {
        scale *= 0.1;
      }
      this.delta += (mouseEvent.getX() - dragStart);
      T newValue = clamp(dragStartValue + this.delta * scale);
      this.delta -= (dragStartValue + this.delta * scale - newValue.doubleValue()) * scale;
      setAndUpdate(newValue);
    }
    dragStart = mouseEvent.getX();

    if (Math.abs(mouseEvent.getScreenX() - dragStartScreenX) > 20) {
      try {
        new Robot().mouseMove((int) dragStartScreenX, (int) dragStartScreenY);
        dragStart = firstDragStart;
      } catch (AWTException e) {
        e.printStackTrace();
      }
    }

    altWasPressed = mouseEvent.isAltDown();
    shiftWasPressed = mouseEvent.isShiftDown();
  }

  public void setName(String name) {
    this.name.set(name);
  }

  public String getName() {
    return name.get();
  }

  // TODO: not used - should be removed?
  public StringProperty nameProperty() {
    return name;
  }

  /**
   * Set value without triggering listeners to update.
   */
  public void set(Number newValue) {
    if (listener != null) {
      value.removeListener(listener);
      value.setValue(newValue);
      value.addListener(listener);
    } else {
      value.setValue(newValue);
    }
  }

  /**
   * Sets the value and updates the listeners.
   */
  public void setAndUpdate(Number newValue) {
    value.setValue(newValue);
  }

  public T get() {
    return (T) value.getValue();
  }

  public void setTooltip(String tooltip) {
    nameLbl.setTooltip(new Tooltip(tooltip));
    valueField.setTooltip(new Tooltip(tooltip));
  }

  protected abstract T clamp(Number value);

  public void onValueChange(Consumer<T> changeConsumer) {
    if (listener != null) {
      value.removeListener(listener);
    }
    listener = (observable, oldValue, newValue) -> changeConsumer.accept(clamp(newValue));
    value.addListener(listener);
  }

  public Property<Number> valueProperty() {
    return value;
  }
}
