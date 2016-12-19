package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.util.Command
import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player

class InserCoinCommand(grid:Grid,number:Int,player:Player) extends Command {
  private var prevGrid:Grid=grid
  private var nextGrid:Grid=null
  
  def execute:Grid={nextGrid=grid.insertCoinCol(number, player); return nextGrid;}
  def undo:Grid=prevGrid
  
  
}

// def insertCoin(number: Int, playerID: Int) = {
//    grid = grid.insertCoinCol(number, player(playerID - 1))
//    println("Player " + player(playerID - 1).name + " inserts " + number)
//    notifyObservers()
//  }