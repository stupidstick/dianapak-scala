package ru.dianapak.view

import javafx.fxml.{FXML, FXMLLoader}
import javafx.scene.Scene
import javafx.stage.Stage
import ru.dianapak.Application

import java.util.Objects

class MenuController {
  @FXML
  private def openListStringMenu(): Unit = {
    createStage("list-string.fxml", "string menu").show()
  }

  @FXML
  private def openListIntegerMenu(): Unit = {
    createStage("list-integer.fxml", "integer menu").show()
  }

  @FXML
  private def openListDoubleMenu(): Unit = {
    createStage("list-double.fxml", "double menu").show()
  }

  @FXML
  private def openListPointMenu(): Unit = {
    createStage("list-point.fxml", "point.fxml").show()
  }

  private def createStage(viewName: String, title: String) = {
    val scene = new Scene(FXMLLoader.load(Objects.requireNonNull(classOf[Application].getClassLoader.getResource(viewName))))
    val stage = new Stage
    stage.setTitle(title)
    stage.setScene(scene)
    stage
  }
}
