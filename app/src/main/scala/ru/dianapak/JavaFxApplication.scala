package ru.dianapak

import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Scene
import javafx.stage.Stage
import java.util.Objects

class JavaFxApplication extends Application {

  override def start(stage: Stage): Unit = {
    val scene = new Scene(FXMLLoader.load(Objects.requireNonNull(classOf[Application].getClassLoader.getResource("menu.fxml"))))
    stage.setTitle("main menu")
    stage.setScene(scene)
    stage.show()
  }

  def launchApplication(args: Array[String]): Unit = {
    Application.launch(args: _*)
  }
}