package de.htwg.se.connectfour.model.impl


import java.awt.Color
import de.htwg.se.connectfour.model.IGrid
import scala.Vector


object Grid {
  //Companion Object to initialize Players
  def apply(height: Int, width: Int): Grid = new Grid(height, width,initPlayer())
  def apply():Grid=apply(6,7)

  def initPlayer(): Array[Player] = {
    println("Player 1 name:"); val name1 = scala.io.StdIn.readLine()
    println("Player 2 name:"); val name2 = scala.io.StdIn.readLine()
    val p1 = new Player(name1, Color.RED)
    val p2 = new Player(name2, Color.YELLOW)
    Array(p1, p2)
  }

}

case class Grid(cells: Vector[Cell], height: Int, width: Int, player:Array[Player]) extends IGrid {
    def this(height:Int, width:Int,player:Array[Player])= this(Vector.fill(height * width)(new Cell(None)), height, width, player)
      def this(height:Int,width:Int)=this(height,width,Array(new Player(0),new Player(1)))
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
  def getPlayerName(playerID:Int):String=player(playerID).name
  

  /**
   * This function inserts a Coin in the first empty row of the chosen column
   */
  def insertCoinCol(c: Int, playerID: Int): Grid = {
    for (r <- 0 until height) {
      if (cells(r * width + c).isEmpty()) {
        return copy(cells.updated(r * width + c, new Cell(Option(Coin(player(playerID))))))
      }
    }
    //return unchanged grid
    return copy(cells)
  }

  
  def printout(): String = {
    var builder: StringBuilder = new StringBuilder()

    for (r <- (0 until height) reverse) {
      row(r).foreach {
        c =>
          c.content match {
            case Some(coin) => builder.append(coin.player.id + " ")
            case None => builder.append("N ")
          }
      }
      builder.append("\n")
    }
    return builder.toString()
  }

  def hasWon(): Boolean = {
    val c1 = new Cell(Option(Coin(new Player(1))))
    val c2 = new Cell(Option(Coin(new Player(2))))

    val ListP1 = Vector(c1, c1, c1, c1)
    val ListP2 = Vector(c2, c2, c2, c2)
    //iterate over each index of row(0)
    //slice each col and match
    row(0).zipWithIndex.foreach {
      case (cell, i) =>
        col(i) match {
          case v: Vector[Cell] if (v.containsSlice(ListP1)) =>return true;
          case v: Vector[Cell] if (v.containsSlice(ListP2)) => return true;
          case _ =>
        }
    }

    col(0).zipWithIndex.foreach {
      case (cell, i) =>
        row(i) match {
          case v: Vector[Cell] if (v.containsSlice(ListP1)) =>return true;
          case v: Vector[Cell] if (v.containsSlice(ListP2)) =>return true;
          case _ =>
        }
    }
    diagonal().foreach { x =>

      x match {
        case v: Vector[Cell] if (v.containsSlice(ListP1)) =>return true;
        case v: Vector[Cell] if (v.containsSlice(ListP2)) =>return true;
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