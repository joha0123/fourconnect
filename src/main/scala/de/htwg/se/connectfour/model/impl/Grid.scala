package de.htwg.se.connectfour.model.impl

import scala.swing.Color
import de.htwg.se.connectfour.model.IGrid
import scala.Vector

object Grid {
  //Companion Object to initialize Players
  def apply(height: Int, width: Int): Grid = new Grid(height, width, initPlayer())
  def apply(): Grid = apply(6, 7)

  def initPlayer(): Array[Player] = {
    println("Player 1 name:"); val name1 = scala.io.StdIn.readLine()
    println("Player 2 name:"); val name2 = scala.io.StdIn.readLine()
    val p1 = new Player(name1, new Color(255, 0, 0))
    val p2 = new Player(name2, new Color(255, 255, 0))
    Array(p1, p2)
  }

}

case class Grid(cells: Vector[Cell], height: Int, width: Int, players: Array[Player], activePlayerIndex: Int = 0) extends IGrid {
  def this(height: Int, width: Int, players: Array[Player]) = this(Vector.fill(height * width)(new Cell(None)), height, width, players)
  def this(height: Int, width: Int) = this(height, width, Array(new Player(0), new Player(1)))

  def getGridHeight(): Int = height
  def getGridWidth(): Int = width

  def changeActivePlayer(): Grid = copy(activePlayerIndex = 1 - activePlayerIndex)
  def getActivePlayer(): Player = players(activePlayerIndex)
  def getPlayer(index: Int): Player = players(index)

  def row(r: Int): Vector[Cell] = cells.slice(r * width, (r + 1) * width)
  def cell(r: Int, c: Int): Cell = row(r)(c)
  def col(c: Int): Vector[Cell] = for (r <- (0 until height).toVector) yield cell(r, c)
  def isWithinGrid(col: Int): Boolean = if (col >= 0 && col < width) true else false

  def insertCoinAt(c: Int, r: Int, p: Player): Grid = {
    if (c < width && r < height && c >= 0 && r >= 0) {
      return copy(cells.updated(r * width + c, new Cell(Option(Coin(p)))))
    } else {
      throw new IllegalArgumentException
    }
  }

  /**
   * This function inserts a Coin in the first empty row of the chosen column
   */
  def insertCoinCol(c: Int, player: Player): Grid = {
    for (r <- 0 until height) {
      if (cells(r * width + c).isEmpty()) {
        return copy(cells.updated(r * width + c, new Cell(Option(Coin(player)))))
      }
    }
    //return unchanged grid
    return copy(cells)
  }

  def restart(): Grid = {
    copy(cells = Vector.fill(height * width)(new Cell(None)), activePlayerIndex = 0)
  }

  def hasWon(player: Player): Boolean = {
    val c1 = new Cell(Option(Coin(player)))
    val winningCoins = Vector(c1, c1, c1, c1)
    //iterate over each index of row(0)
    //slice each col and match
    row(0).zipWithIndex.foreach {
      case (cell, i) =>
        col(i) match {
          case v: Vector[Cell] if (v.containsSlice(winningCoins)) => return true;
          case _ =>
        }
    }

    col(0).zipWithIndex.foreach {
      case (cell, i) =>
        row(i) match {
          case v: Vector[Cell] if (v.containsSlice(winningCoins)) => return true;
          case _ =>
        }
    }
    diagonal().foreach { x =>

      x match {
        case v: Vector[Cell] if (v.containsSlice(winningCoins)) => return true;
        case _ =>
      }

    }
    false

  }
  def diagonal(): Array[Vector[Cell]] = {
    //var array: Array[Array[Cell]] = toTwoDimArray()
    val numDiagonals = (width + height - 1) * 2
    var d: Array[Vector[Cell]] = new Array[Vector[Cell]](numDiagonals)
    var zähler = 0;

    //Diagonals from (0,0) until (0,6) to the right

    for (x1 <- (0 until width)) {

      var v: List[Cell] = List();
      var x: Integer = x1
      var y: Integer = 0
      while (x < width && y < height) {

        v = v :+ col(x)(y)
        y = y + 1
        x = x + 1

      }
      d(zähler) = v.toVector;
      zähler = zähler + 1;
    }

    for (y1 <- (1 until height)) {

      var v: List[Cell] = List();
      var x: Integer = 0
      var y: Integer = y1
      while (x < width && y < height) {

        v = v :+ col(x)(y)
        y = y + 1
        x = x + 1

      }
      d(zähler) = v.toVector;
      zähler = zähler + 1;
    }
    for (x1 <- (0 until width) reverse) {

      var v: List[Cell] = List();
      var x: Integer = x1
      var y: Integer = 0
      while (x >= 0 && x < width && y < height) {

        v = v :+ col(x)(y)
        y = y + 1
        x = x - 1

      }
      d(zähler) = v.toVector;
      zähler = zähler + 1;
    }

    for (y1 <- (1 until height) reverse) {

      var v: List[Cell] = List();
      var x: Integer = width - 1
      var y: Integer = y1
      while (x >= 0 && x < width && y < height) {

        v = v :+ col(x)(y)
        y = y + 1
        x = x - 1

      }
      d(zähler) = v.toVector;
      zähler = zähler + 1;
    }
    return d

  }

  override def equals(that: Any): Boolean =
    that match {
      case that: Grid => that.canEqual(this) && this.hashCode == that.hashCode
      case _ => false
    }
  override def hashCode: Int = {
    return this.cells.hashCode()
  }

}