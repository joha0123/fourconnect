package de.htwg.se.connectfour.model

import java.awt.Color

case class Grid(cells: Vector[Cell], height: Int, width: Int) {

  def this(height: Int = 6, width: Int = 7) = this(Vector.fill(height * width)(new Cell(None)), height, width)

  def row(r: Int): Vector[Cell] = {
    cells.slice(r * width, (r + 1) * width)
  }

  def cell(r: Int, c: Int): Cell = {
    row(r)(c)
  }

  def col(c: Int): Vector[Cell] = {
    for (r <- (0 until height).toVector) yield cell(r, c)
  }

  /*
   *returns a Tupel of Type (Int,Boolean). True if the column is within the grid.
   *  
   */
  def checkInput(c: String): (Int, Boolean) = {
    if (c.trim().matches("[0-9]")) {
      val col = c.trim.toInt
      if (col >= 0 && col < width) {
        return (col, true)
      }
    }
    return (0, false)
  }
  //cell.coin.isInstanceOf[Coin]

  def insertCoinAt(r: Int, c: Int, p: Player): Grid = {
    copy(cells.updated(r * width + c, new Cell(Option(Coin(p)))))
  }

  /**
   * This function inserts a Coin in the first empty row of the chosen column
   */
  def insertCoinCol(c: Int, p: Player): Grid = {
    for (r <- 0 until height) {
      var i = height - r - 1;
      if (cells(i * width + c).isEmpty()) {
        return copy(cells.updated(i * width + c, new Cell(Option(Coin(p)))))
      }
    }
    //return unchanged grid
    return copy(cells)
  }

  def printout(): Unit = {
    for (r <- (0 until height)) {
      row(r).foreach {
        c =>
          c.content match {
            case Some(coin) => print(coin.player.id + " ")
            case None => print("N ")
          }
      }
      println()
    }

  }

}