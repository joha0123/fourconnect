package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player
import de.htwg.se.connectfour.util.Observeable
import de.htwg.se.connectfour.util.CommandManager

class Connect4Controller(var grid: Grid, player: Array[Player],commandManager:CommandManager) extends Observeable {

  def restart() {
    //restart game
    //evtl Design Pattern?
  }
  
  def undo:Boolean={
    var flag=false
    if(commandManager.isUndoAvailable){
      grid=commandManager.undo
      flag=true
    }
    notifyObservers()
    return flag
    
  }
  
  def redo:Boolean={
    var flag=false
    if(commandManager.isRedoAvailable){
      grid=commandManager.redo
      flag=true
    }
    notifyObservers()
    return flag
  }
  

  def getDimension: String = return "[0," + grid.width + "]"

  def hasWon(): Boolean = grid.hasWon()

  def isValid(number: Int): Boolean = grid.checkInput(number)

  def insertCoin(number: Int, playerID: Int) = {
    grid=commandManager.executeCommand(new InserCoinCommand(grid,number,player(playerID)))
    println("Player " + player(playerID).name + " inserts " + number)
    notifyObservers()
  }

  def printout(): String = grid.printout()

}