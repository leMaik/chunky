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
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.VBox;
import se.llbit.chunky.renderer.RenderController;
import se.llbit.chunky.renderer.postprocessing.PostProcessingFilter;
import se.llbit.chunky.renderer.postprocessing.UE4ToneMappingFilter;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.ui.DoubleAdjuster;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class UE4ToneMappingFilterSettings extends VBox implements Initializable {
  private Scene scene;
  private UE4ToneMappingFilter filter;

  @FXML private MenuButton presetChooser;
  @FXML private DoubleAdjuster saturation;
  @FXML private DoubleAdjuster slope;
  @FXML private DoubleAdjuster toe;
  @FXML private DoubleAdjuster shoulder;
  @FXML private DoubleAdjuster blackClip;
  @FXML private DoubleAdjuster whiteClip;

  public UE4ToneMappingFilterSettings() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("UE4ToneMappingFilterSettings.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  @Override public void initialize(URL location, ResourceBundle resources) {
    for (UE4ToneMappingFilter.Preset preset : UE4ToneMappingFilter.Preset.values()) {
      MenuItem menuItem = new MenuItem(preset.toString());
      menuItem.setOnAction(e -> {
        filter.applyPreset(preset);
        update();
      });
      presetChooser.getItems().add(menuItem);
    }

    saturation.setName("Saturation");
    saturation.setRange(0, 1);
    saturation.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setSaturation(floatValue);
    });

    slope.setName("Slope");
    slope.setRange(0, 1);
    slope.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setSlope(floatValue);
    });

    toe.setName("Toe");
    toe.setRange(0, 1);
    toe.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setToe(floatValue);
    });

    shoulder.setName("Shoulder");
    shoulder.setRange(0, 1);
    shoulder.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setShoulder(floatValue);
    });

    blackClip.setName("Black clip");
    blackClip.setRange(0, 1);
    blackClip.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setBlackClip(floatValue);
    });

    whiteClip.setName("White clip");
    whiteClip.setRange(0, 1);
    whiteClip.onValueChange(value -> {
      float floatValue = value.floatValue();
      filter.setWhiteClip(floatValue);
    });
  }

  public void setFilter(PostProcessingFilter filter) {
    this.filter = (UE4ToneMappingFilter) filter;
  }

  public void setRenderController(RenderController controller) {
    scene = controller.getSceneManager().getScene();
  }

  public void update() {
    saturation.set(filter.getSaturation());
    slope.set(filter.getSlope());
    toe.set(filter.getToe());
    shoulder.set(filter.getShoulder());
    blackClip.set(filter.getBlackClip());
    whiteClip.set(filter.getWhiteClip());
  }
}
