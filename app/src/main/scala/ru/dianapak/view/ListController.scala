package ru.dianapak.view

import javafx.collections.{FXCollections, ObservableList}
import javafx.fxml.{FXML, Initializable}
import javafx.scene.control.{Alert, ListView}
import ru.dianapak.core.list.{SingleLinkedList, SingleLinkedListImpl}

import java.io._
import java.net.URL
import java.util.ResourceBundle

abstract class ListController[T <: Comparable[T]] extends Initializable {

  protected var list: SingleLinkedList[T] = new SingleLinkedListImpl[T]
  private final val observableList: ObservableList[T] = FXCollections.observableArrayList

  @FXML
  private var listView: ListView[T] = _

  override def initialize(url: URL, resourceBundle: ResourceBundle): Unit = {
    listView.setItems(observableList)
  }

  protected def getValue: T

  protected def getValueName: String

  protected def showErrorDialog(message: String): Unit = {
    val alert = new Alert(Alert.AlertType.ERROR)
    alert.setContentText(message)
    alert.showAndWait
  }

  @FXML
  private def insert(): Unit = {
    val element = getValue
    if (element == null) return
    list.insert(getValue)
    updateListView()
  }

  @FXML
  private def insertByPos(): Unit = {
    val pos = listView.getSelectionModel.getSelectedIndex
    if (pos == -1) {
      showErrorDialog("Позиция в списке не выбрана")
      return
    }
    val element = getValue
    if (element == null) return
    list.insert(element, pos)
    updateListView()
  }

  @FXML
  private def remove(): Unit = {
    val pos = listView.getSelectionModel.getSelectedIndex
    if (pos == -1) {
      showErrorDialog("Элемент списка не выбран")
      return
    }
    list.remove(pos)
    updateListView()
  }

  @FXML
  private def sort(): Unit = {
    list.singleMergeSort()
    updateListView()
  }

  @FXML
  private def serialize(): Unit = {
    try {
      val fos = new FileOutputStream(getValueName + "-list")
      val out = new ObjectOutputStream(fos)
      try out.writeObject(list)
      finally {
        if (fos != null) fos.close()
        if (out != null) out.close()
      }
    }
  }

  @FXML
  private def deserialize(): Unit = {
    try {
      val fis = new FileInputStream(getValueName + "-list")
      val ois = new ObjectInputStream(fis)
      try {
        list = ois.readObject.asInstanceOf[SingleLinkedList[T]]
        updateListView()
      } catch {
        case e: FileNotFoundException =>
          showErrorDialog("Файл не найден")
      } finally {
        if (fis != null) fis.close()
        if (ois != null) ois.close()
      }
    }
  }

  private def updateListView(): Unit = {
    observableList.setAll(list.toArray: _*)
  }
}
