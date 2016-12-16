package de.htwg.se.connectfour.view

import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.model.Player
import scala.util.matching.Regex

class tui(var controller:Connect4Controller) {
  
  def hasWon()=controller.hasWon()
  
  /**
   * q=quit; [0,6]=input col; r=restart
   */
  def processInputLine(player:Player)= {
    val numbers=new Regex("[0-9]")
    println("Col:"); val input=scala.io.StdIn.readLine()
    input match{
      case "q" => controller.quit
      case "r" => controller.restart
      case  numbers() => if(controller.isValid(input.trim().toInt)) insertCoin(input.trim().toInt,player) 
      case _ =>println("Please insert a number within: " + controller.getDimension) 
    }
    
  }
  
  
  
  private def insertCoin(i:Int, player:Player) = {
    controller.insertCoin(i,player)
    println(controller.printout())
  }
  
  
}