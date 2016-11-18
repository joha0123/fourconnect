package de.htwg.se.connectfour

import java.awt.Color

object Main {
  def main(args: Array[String]): Unit = {

  var grid = new model.Grid()  
  
  println("Player 1 name:");  val name1=scala.io.StdIn.readLine()
  println("Player 2 name:"); val name2=scala.io.StdIn.readLine()
  
  val p1 = new model.Player(name1, Color.RED)
  val p2 = new model.Player(name2, Color.YELLOW)  
  var players = Array(p1, p2)
  var index = 0
  
  while(!grid.hasWon()){
    //println("Row:"); val row = scala.io.StdIn.readLine().toInt    
    println("Col:"); val col = (grid.checkInput(scala.io.StdIn.readLine()))
    println("Player "+players(index).name+" inserts " +col._1)
    
    if(col._2==true){
    grid=grid.insertCoinCol(col._1,players(index) )
    println(grid.hasWon())
    //grid = grid.insertCoinAt(row, col, players(index))    
    index = 1 - index    
    grid.printout()
    
    
    } else{
      println("Please insert a number within: "+"[0,"+(grid.width-1)+"]")
    }
  }
  println("The game is over!")
  


  }
}