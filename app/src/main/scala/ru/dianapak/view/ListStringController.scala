package ru.dianapak.view

import javafx.fxml.FXML
import javafx.scene.control.TextField

class ListStringController extends ListController[String] {
  @FXML
  private var valueField: TextField = null

  override protected def getValue: String = {
    val value = valueField.getText
    if (value.isEmpty) {
      showErrorDialog("Значение не введено")
      return null
    }
    value
  }

  override protected def getValueName = "string"
}
