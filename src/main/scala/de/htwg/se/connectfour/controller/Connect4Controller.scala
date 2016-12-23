package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.IGrid
import de.htwg.se.connectfour.model.impl.Player
import de.htwg.se.connectfour.util.Observeable
import de.htwg.se.connectfour.util.CommandManager
import de.htwg.se.connectfour.util.InserCoinCommand
import java.awt.Color



class Connect4Controller(var grid: IGrid, commandManager: CommandManager) extends Observeable {

  def restart() {
    //restart game
    //evtl Design Pattern?
  }

  def undo: Boolean = {
    var flag = false
    if (commandManager.isUndoAvailable) {
      grid = commandManager.undo
      flag = true
    }
    notifyObservers()
    return flag

  }

  def redo: Boolean = {
    var flag = false
    if (commandManager.isRedoAvailable) {
      grid = commandManager.redo
      flag = true
    }
    notifyObservers()
    return flag
  }

  def getDimension: String = return "[0," + grid.width + "]"
  def hasWon(): Boolean = grid.hasWon()
  def isValid(number: Int): Boolean = grid.isWithinGrid(number)
  def printout(): String = grid.printout()
  def playerName(playerID:Int):String=grid.getPlayerName(playerID)
  
  
  def insertCoin(number: Int, playerID: Int) = {
    require(playerID==0||playerID==1)
    grid = commandManager.executeCommand(new InserCoinCommand(grid, number, playerID))
    println("Player " + playerName(playerID)+ " inserts " + number)
    notifyObservers()
  }
  

}