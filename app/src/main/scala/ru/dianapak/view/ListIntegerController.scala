package ru.dianapak.view

import javafx.fxml.FXML
import javafx.scene.control.TextField


class ListIntegerController extends ListController[Integer] {
  @FXML
  private var valueField: TextField = _

  override protected def getValue: Integer = {
    val value = valueField.getText
    try {
      Integer.valueOf(value)
    }
    catch {
      case e: NumberFormatException =>
        showErrorDialog("Не дабл")
        null
    }
  }

  override protected def getValueName = "integer"
}
