package de.htwg.se.connectfour.model

import java.awt.Color

case class Player(id: Integer, name: String, color: Color, gamesWon: Int) {
  def this(id: Integer, name: String, color: Color) = this(id, name, color, 0)
}