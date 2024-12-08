package ru.dianapak.view

import javafx.fxml.FXML
import javafx.scene.control.TextField
import ru.dianapak.core.point.Point


class ListPointController extends ListController[Point] {
  @FXML
  private var xField: TextField = _
  @FXML
  private var yField: TextField = _

  override protected def getValue: Point = {
    try {
      new Point(xField.getText.toInt, yField.getText.toInt)
    } catch {
      case _: NumberFormatException =>
        showErrorDialog("Неправильно введено")
        null
    }
  }

  override protected def getValueName = "point"
}
