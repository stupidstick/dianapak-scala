package ru.dianapak.view

import javafx.fxml.FXML
import javafx.scene.control.TextField


class ListDoubleController extends ListController[java.lang.Double] {
  @FXML
  private var valueField: TextField = _

  override protected def getValue: java.lang.Double = {
    val value = valueField.getText
    try {
      java.lang.Double.valueOf(value)
    } catch {
      case _: NumberFormatException =>
        showErrorDialog("Не дабл")
        null
    }
  }

  override protected def getValueName = "double"
}
