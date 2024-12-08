package ru.dianapak.core.list

import java.util.function.Consumer

class SingleLinkedListImpl[T <: Comparable[T]] extends SingleLinkedList[T] with Serializable {
  private var size = 0
  private var head: Node[T] = _

  override def insert(value: T): Unit = {
    if (size == 0) {
      head = new Node[T](value)
      size += 1
      return
    }
    insertAfterTail(value)
  }

  override def insert(value: T, pos: Int): Unit = {
    if (pos == size) {
      insert(value)
      return
    }
    checkPos(pos)
    val oldNode = getNode(pos)
    oldNode.next = new Node[T](oldNode.value, oldNode.next)
    oldNode.value = value
    size += 1
  }

  override def remove(pos: Int): Unit = {
    checkPos(pos)
    if (pos == 0) {
      head = head.next
      size -= 1
      return
    }
    val beforeNode = getNode(pos - 1)
    beforeNode.next = beforeNode.next.next
    size -= 1
  }

  override def get(pos: Int): T = {
    checkPos(pos)
    getNode(pos).value
  }

  override def forEach(action: Consumer[T]): Unit = {
    var current = head
    while (current != null) {
      action.accept(current.value)
      current = current.next
    }
  }

  override def quickSort(): Unit = {
    val arr = toArray
    quickSort(arr, 0, arr.length - 1)
    var current = head
    for (value <- arr) {
      current.value = value
      current = current.next
    }
  }

  override def singleMergeSort(): Unit = {
    val sortedList = singleMergeSort(toArray.toList)
    var current = head
    for (value <- sortedList) {
      current.value = value
      current = current.next
    }
  }

  override def getSize: Int = size

  override def toArray: Array[T] = {
    val arr = new Array[Any](size).asInstanceOf[Array[T]]
    var current = head
    for (i <- 0 until size) {
      arr(i) = current.value
      current = current.next
    }
    arr
  }

  private def insertAfterTail(value: T): Unit = {
    getTail.next = new Node[T](value)
    size += 1
  }

  private def getTail = {
    var current = head
    while (current.next != null) current = current.next
    current
  }

  private def getNode(pos: Int) = {
    var current = head
    for (i <- 0 until pos) {
      current = current.next
    }
    current
  }

  private def checkPos(pos: Int): Unit = {
    if (size == 0) throw new IndexOutOfBoundsException("List is empty")
    if (pos < 0 || pos >= size) throw new IndexOutOfBoundsException("Position " + pos + " is out of bounds (0, " + (size - 1) + ")")
  }

  private def merge(left: List[T], right: List[T]): List[T] = {
    (left, right) match {
      case (Nil, _) => right
      case (_, Nil) => left
      case (x :: xs, y :: ys) =>
        if (x.compareTo(y) <= 0) x :: merge(xs, right)
        else y :: merge(left, ys)
    }
  }

  private def singleMergeSort(list: List[T]): List[T] = {
    if (list.length <= 1) list
    else {
      val (left, right) = list.splitAt(list.length / 2)
      merge(singleMergeSort(left), singleMergeSort(right))
    }
  }

  private def quickSort(arr: Array[T], low: Int, high: Int): Unit = {
    if (low < high) {
      val pivotIndex = partition(arr, low, high)
      quickSort(arr, low, pivotIndex - 1)
      quickSort(arr, pivotIndex + 1, high)
    }
  }

  private def partition(arr: Array[T], low: Int, high: Int) = {
    val pivot = arr(high)
    var i = low - 1
    for (j <- low until high) {
      if (arr(j).compareTo(pivot) <= 0) {
        i += 1
        val temp = arr(i)
        arr(i) = arr(j)
        arr(j) = temp
      }
    }
    val temp = arr(i + 1)
    arr(i + 1) = arr(high)
    arr(high) = temp
    i + 1
  }

  private class Node[E] extends Serializable {
    private[list] var value: E = _
    private[list] var next: Node[E] = _

    def this(value: E, next: Node[E]) {
      this()
      this.value = value
      this.next = next
    }

    def this(value: E) {
      this()
      this.value = value
    }
  }
}

