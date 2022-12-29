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
package se.llbit.chunky.ui.render.tabs;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.util.StringConverter;
import se.llbit.chunky.renderer.postprocessing.PostProcessingFilter;
import se.llbit.chunky.renderer.postprocessing.PostProcessingFilters;
import se.llbit.chunky.renderer.scene.Scene;
import se.llbit.chunky.resources.BitmapImage;
import se.llbit.chunky.ui.DoubleAdjuster;
import se.llbit.chunky.ui.controller.RenderControlsFxController;
import se.llbit.chunky.ui.render.RenderControlsTab;
import se.llbit.chunky.ui.render.settings.HableToneMappingFilterSettings;
import se.llbit.chunky.ui.render.settings.UE4ToneMappingFilterSettings;
import se.llbit.util.ProgressListener;
import se.llbit.util.TaskTracker;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import se.llbit.util.TaskTracker.Task;

public class PostprocessingTab extends ScrollPane implements RenderControlsTab, Initializable {
  private Scene scene;
  private RenderControlsFxController controller;

  @FXML private DoubleAdjuster exposure;
  @FXML private ChoiceBox<PostProcessingFilter> postprocessingFilter;
  @FXML private TitledPane postprocessingFilterDetailsPane;

  private final HableToneMappingFilterSettings hableToneMappingFilterSettingsBox = new HableToneMappingFilterSettings();
  private final UE4ToneMappingFilterSettings UE4ToneMappingFilterSettingsBox = new UE4ToneMappingFilterSettings();

  public PostprocessingTab() throws IOException {
    FXMLLoader loader = new FXMLLoader(getClass().getResource("PostprocessingTab.fxml"));
    loader.setRoot(this);
    loader.setController(this);
    loader.load();
  }

  @Override public void setController(RenderControlsFxController controller) {
    this.controller = controller;
    scene = controller.getRenderController().getSceneManager().getScene();
    hableToneMappingFilterSettingsBox.setRenderController(controller.getRenderController());
    UE4ToneMappingFilterSettingsBox.setRenderController(controller.getRenderController());
  }

  @Override public void update(Scene scene) {
    postprocessingFilter.getSelectionModel().select(scene.getPostProcessingFilter());
    exposure.set(scene.getExposure());
  }

  @Override public String getTabTitle() {
    return "Postprocessing";
  }

  @Override public Node getTabContent() {
    return this;
  }

  @Override public void initialize(URL location, ResourceBundle resources) {
    Label noOptions = new Label("The selected postprocessing filter has no configuration options available.");
    VBox noOptionsBox = new VBox(10.0);
    noOptionsBox.setPadding(new Insets(10, 10, 10, 10));
    noOptionsBox.getChildren().addAll(noOptions);

    postprocessingFilter.setTooltip(new Tooltip("Set the postprocessing filter to be used on the image."));
    postprocessingFilter.getItems().add(PostProcessingFilters.NONE);
    postprocessingFilter.getItems().add(new PostprocessingSeparator());
    for (PostProcessingFilter filter : PostProcessingFilters.getFilters()) {
      if (filter != PostProcessingFilters.NONE) {
        postprocessingFilter.getItems().add(filter);
      }
    }
    postprocessingFilter.getSelectionModel().select(Scene.DEFAULT_POSTPROCESSING_FILTER);
    postprocessingFilterDetailsPane.setContent(noOptionsBox);
    postprocessingFilter.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
          scene.setPostprocess(newValue);
          scene.postProcessFrame(new TaskTracker(ProgressListener.NONE));
          controller.getCanvas().forceRepaint();
          System.out.println(newValue.getId());
          switch (newValue.getId()) {
            case "NONE":
            case "TONEMAP2":
            case "GAMMA":
            case "TONEMAP1": {
              postprocessingFilterDetailsPane.setContent(noOptionsBox);
              break;
            }
            case "TONEMAP3": {
              hableToneMappingFilterSettingsBox.setFilter(scene.getPostProcessingFilter());
              postprocessingFilterDetailsPane.setContent(hableToneMappingFilterSettingsBox);
              hableToneMappingFilterSettingsBox.update();
              break;
            }
            case "UE4_FILMIC": {
              UE4ToneMappingFilterSettingsBox.setFilter(scene.getPostProcessingFilter());
              postprocessingFilterDetailsPane.setContent(UE4ToneMappingFilterSettingsBox);
              UE4ToneMappingFilterSettingsBox.update();
              break;
            }
          }
        });
    postprocessingFilter.setConverter(new StringConverter<PostProcessingFilter>() {
      @Override
      public String toString(PostProcessingFilter object) {
        return object == null ? null : object.getName();
      }

      @Override
      public PostProcessingFilter fromString(String string) {
        return PostProcessingFilters.getPostProcessingFilterFromName(string).orElse(Scene.DEFAULT_POSTPROCESSING_FILTER);
      }
    });
    exposure.setName("Exposure");
    exposure.setTooltip("Linear exposure of the image.");
    exposure.setRange(Scene.MIN_EXPOSURE, Scene.MAX_EXPOSURE);
    exposure.makeLogarithmic();
    exposure.clampMin();
    exposure.onValueChange(value -> {
      scene.setExposure(value);
      scene.postProcessFrame(new TaskTracker(ProgressListener.NONE));
      controller.getCanvas().forceRepaint();
    });
  }

  /**
   * Fake post processing filter that is also a separator for the combobox.
   */
  private static class PostprocessingSeparator extends Separator implements PostProcessingFilter {

    @Override
    public void processFrame(int width, int height, double[] input, BitmapImage output,
        double exposure, Task task) {
    }

    @Override
    public String getName() {
      return "";
    }
  }
}
