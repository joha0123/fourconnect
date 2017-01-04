package de.htwg.se.connectfour.model.impl

import scala.swing.Color

case class Player(id: Integer, name: String, color: Color, gamesWon: Int) {
  def this(id: Integer, name: String, color: Color) = this(id, name, color, 0)
  def this(name: String, color: Color) = this(Player.inc, name, color, 0)
  def this(id: Integer) = this(id, "", new Color(0,0,0), 0)
  override def canEqual(a: Any) = a.isInstanceOf[Player]
  override def equals(that: Any): Boolean =
    that match {
      case that: Player => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
  override def hashCode: Int = {
    return id
  }
}
//Companion object to have an id
object Player {
  private var current = 0
  private def inc = { current += 1; current }
}

