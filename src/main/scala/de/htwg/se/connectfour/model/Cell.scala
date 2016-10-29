package de.htwg.se.connectfour.model

import java.awt.Color

case class Cell(content:Option[Coin]) {
  
  def isEmpty(): Boolean = content match {
    case Some(coin) => false
    case None => true
  }

  def insertCoin(newCoin: Coin): Cell = copy(content = Option(newCoin))

  def reset(): Cell = copy(content = None)

}