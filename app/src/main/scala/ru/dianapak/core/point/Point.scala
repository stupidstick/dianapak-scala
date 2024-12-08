package ru.dianapak.core.point

class Point(val x: Int, val y: Int) extends Comparable[Point] with Serializable {

  override def compareTo(point: Point): Int = this.x * this.x + this.y * this.y - point.x * point.x - point.y * point.y

  override def toString: String = "x=" + x + ", y=" + y
}
