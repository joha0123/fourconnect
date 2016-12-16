package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player
import de.htwg.se.connectfour.util.Observeable

class Connect4Controller(var grid:Grid, player:Array[Player])extends Observeable {
  
  
  def restart(){
    //restart game
  }
  
  def getDimension:String=return "[0,"+grid.width+"]"
   
  
  def hasWon()=grid.hasWon()
  
  def isValid(number:Int):Boolean=grid.checkInput(number)
  
  def insertCoin(number:Int, index:Int)= {
    grid=grid.insertCoinCol(number, player(index))
    println("Player " + player(index).name + " inserts " + number) 
    notifyObservers()
  }
  
  def printout():String=grid.printout()

  
  
}