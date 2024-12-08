package ru.dianapak.core.list

import java.util
import java.util.function.Consumer


trait SingleLinkedList[T <: Comparable[T]] {

  def insert(value: T): Unit

  def insert(value: T, pos: Int): Unit

  def remove(pos: Int): Unit

  def get(pos: Int): T

  def forEach(action: Consumer[T]): Unit

  def quickSort(): Unit

  def singleMergeSort(): Unit

  def getSize: Int

  def toArray: Array[T]
}
