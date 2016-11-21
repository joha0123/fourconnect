package de.htwg.se.connectfour.model

import java.awt.Color

case class Grid(cells: Vector[Cell], height: Int, width: Int) {

  def this(height: Int = 6, width: Int = 7) = this(Vector.fill(height * width)(new Cell(None)), height, width)
  
  /**
   * returns the row
   */
  def row(r: Int): Vector[Cell] = {
    cells.slice(r * width, (r + 1) * width)
  }
 
 
  /**
   * returns the cell
   */
  def cell(r: Int, c: Int): Cell = {
    row(r)(c)
  }
  
  /**
   * returns the col
   */
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

  def insertCoinAt(c: Int,r:Int, p: Player): Grid = {
    if(c<width&&r<height&&c>=0&&r>=0){
    return copy(cells.updated(r * width + c, new Cell(Option(Coin(p)))))
    } else{
     throw new IllegalArgumentException 
    }
  }

  /**
   * This function inserts a Coin in the first empty row of the chosen column
   */
  def insertCoinCol(c: Int, p: Player): Grid = {
    for (r <- 0 until height) {      
      if (cells(r * width + c).isEmpty()) {
        return copy(cells.updated(r * width + c, new Cell(Option(Coin(p)))))
      }
    }
    //return unchanged grid
    return copy(cells)
  }

  def printout(): Unit = {
    for (r <- (0 until height) reverse) {
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
          case v: Vector[Cell] if (v.containsSlice(ListP1)) => println("Player1 won"); return true;
          case v: Vector[Cell] if (v.containsSlice(ListP2)) => println("Player2 won"); return true;
          case _ =>
        }
    }

    col(0).zipWithIndex.foreach {
      case (cell, i) =>
        row(i) match {
          case v: Vector[Cell] if (v.containsSlice(ListP1)) => println("Player1 won"); return true;
          case v: Vector[Cell] if (v.containsSlice(ListP2)) => println("Player2 won"); return true;
          case _ =>
        }
    }
    diagonal().foreach { x =>
      
      x match {
        case v: Vector[Cell] if (v.containsSlice(ListP1)) => println("Player1 won"); return true;
        case v: Vector[Cell] if (v.containsSlice(ListP2)) => println("Player2 won"); return true;
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
//  def toTwoDimArray(): Array[Array[Cell]] = {
//
//    val array = Array.ofDim[Array[Cell]](width)
//    for (c <- (0 until width)) {
//      array(c) = Array.ofDim[Cell](height)
//    }
//    var i = 0;
//    for (y <- (0 until height)) {
//      for (x <- (0 until width)) {
//        array(x)(y) = cells(i)
//        i = i + 1
//      }
//    }
//    
//    return array;
//  }
  
//  def arrToVector(twoDimArr:Array[Array[Cell]]):Grid={
//    var i=0;
//    var grid=new Grid(this.height,this.width)
//    
//    
//    for(y <- (0 until grid.height)) {
//      for (x <- (0 until grid.width)) {
//        grid.cells.updated(i,twoDimArr(x)(y))
//        i = i + 1
//      }
//    }
//    return grid
//    
//  }
  
}