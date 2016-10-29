package de.htwg.se.connectfour

import java.awt.Color

object Main {
  def main(args: Array[String]): Unit = {

  var grid = new model.Grid()  
    
  val p1 = new model.Player(1, "Joha", Color.RED)
  val p2 = new model.Player(2, "Patrick", Color.YELLOW)  
  var players = Array(p1, p2)
  var index = 0
  
  while(true){
    println("Row:"); val row = scala.io.StdIn.readLine().toInt    
    println("Col:"); val col = scala.io.StdIn.readLine().toInt
    
    grid = grid.insertCoinAt(row, col, players(index))    
    index = 1 - index    
    grid.printout()
  }
  


  }
}