package de.htwg.se.connectfour.view

import de.htwg.se.connectfour.controller.Connect4Controller
import de.htwg.se.connectfour.model.Player
import scala.util.matching.Regex
import de.htwg.se.connectfour.util.IObserver

class tui(var controller:Connect4Controller) extends IObserver {
  //Observer zur subscriber List hinzufügen
  controller.add(this)
  printTui
  var index = 0
  
  def hasWon()=controller.hasWon()
  
  /**
   * q=quit; [0,6]=input col; r=restart
   */
  def processInputLine()= {
    var continue=controller.hasWon().unary_!
    val numbers=new Regex("[0-9]")
    val input=scala.io.StdIn.readLine()
    input match{
      case "q" => continue=false
      case "r" => controller.restart
      case  numbers() => if(controller.isValid(input.trim().toInt)){
        insertCoin(input.trim().toInt,index)
        index=1-index
      }
      case _ =>println("Please insert a number within: " + controller.getDimension) 
    }
    continue
    
  }
  
  def printTui = {
    println(controller.printout())
    println("Enter command: q-Quit r-Restart "+controller.getDimension+" Col")
  }
  
  
  
  private def insertCoin(i:Int, player:Int) = {
    controller.insertCoin(i,player)
  }
  
  @Override
  def update()=printTui
  
  
}