package de.htwg.se.connectfour.model.impl

import de.htwg.se.connectfour.model.impl.Coin

case class Cell(content: Option[Coin] = None) {

  def isEmpty(): Boolean = content match {
    case Some(c) => false
    case None => true
  }

  def insertCoin(newCoin: Coin): Cell = copy(content = Option(newCoin))
  def reset(): Cell = copy(content = None)

}