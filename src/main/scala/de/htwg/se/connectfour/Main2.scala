package de.htwg.se.connectfour

import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.model.Player
import java.awt.Color
import de.htwg.se.connectfour.view.tui


  object Main2 {
    println("Player 1 name:"); val name1 = scala.io.StdIn.readLine()
    println("Player 2 name:"); val name2 = scala.io.StdIn.readLine()
    val p1 = new model.Player(name1, Color.RED)
    val p2 = new model.Player(name2, Color.YELLOW)
    var players = Array(p1, p2)
    var index = 0
    val grid=new Grid()
    val controller=new Connect4Controller(grid,players)
    
    val tui=new tui(controller)
  
  
  def main(args: Array[String]): Unit = {
    

    
   
    
    while(!tui.hasWon()){
      
      tui.processInputLine(players(index))    
      index=1-index
      }
    
    println("game is over")
  }
}
  
  
