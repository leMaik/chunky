/* Copyright (c) 2016 Jesper Öqvist <jesper@llbit.se>
 * Copyright (c) 2022 Chunky contributors
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
package se.llbit.chunky.ui.render.settings;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import se.llbit.chunky.renderer.RenderController;
import se.llbit.chunky.renderer.postprocessing.PostProcessingFilter;
import se.llbit.chunky.renderer.postprocessing.PostProcessingFilters;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.renderer.postprocessing.HableToneMappingFilter;
import se.llbit.chunky.ui.DoubleAdjuster;
import se.llbit.math.QuickMath;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HableToneMappingFilterSettings extends VBox implements Initializable {
  private Scene scene;
  private HableToneMappingFilter filter;

  @FXML private MenuButton presetChooser;
  @FXML private DoubleAdjuster shoulderStrength;
  @FXML private DoubleAdjuster linearStrength;
  @FXML private DoubleAdjuster linearAngle;
  @FXML private DoubleAdjuster toeStrength;
  @FXML private DoubleAdjuster toeNumerator;
  @FXML private DoubleAdjuster toeDenominator;
  @FXML private DoubleAdjuster linearWhitePointValue;

  public HableToneMappingFilterSettings() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("HableToneMappingFilterSettings.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  @Override public void initialize(URL location, ResourceBundle resources) {
    for (HableToneMappingFilter.Preset preset : HableToneMappingFilter.Preset.values()) {
      MenuItem menuItem = new MenuItem(preset.toString());
      menuItem.setOnAction(e -> {
        filter.applyPreset(preset);
        update();
      });
      presetChooser.getItems().add(menuItem);
    }

    shoulderStrength.setName("Shoulder strength");
    shoulderStrength.setRange(0, 10);
    shoulderStrength.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setShoulderStrength(floatValue);
    });

    linearStrength.setName("Linear strength");
    linearStrength.setRange(0, 10);
    linearStrength.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setLinearStrength(floatValue);
    });

    linearAngle.setName("Linear angle");
    linearAngle.setRange(0, 10);
    linearAngle.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setLinearAngle(floatValue);
    });

    toeStrength.setName("Toe strength");
    toeStrength.setRange(0, 10);
    toeStrength.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setToeStrength(floatValue);
    });

    toeNumerator.setName("Toe numerator");
    toeNumerator.setRange(0, 10);
    toeNumerator.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setToeNumerator(floatValue);
    });

    toeDenominator.setName("Toe denominator");
    toeDenominator.setRange(0, 10);
    toeDenominator.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setToeDenominator(floatValue);
    });

    linearWhitePointValue.setName("Linear white point value");
    linearWhitePointValue.setRange(-100, 100);
    linearWhitePointValue.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setLinearWhitePointValue(floatValue);
    });
  }

  public void setFilter(PostProcessingFilter filter) {
    this.filter = (HableToneMappingFilter) filter;
  }

  public void setRenderController(RenderController controller) {
    scene = controller.getSceneManager().getScene();
  }

  public void update() {
    shoulderStrength.set(filter.getShoulderStrength());
    linearStrength.set(filter.getLinearStrength());
    linearAngle.set(filter.getLinearAngle());
    toeStrength.set(filter.getToeStrength());
    toeNumerator.set(filter.getToeNumerator());
    toeDenominator.set(filter.getToeDenominator());
    linearWhitePointValue.set(filter.getLinearWhitePointValue());
  }
}
