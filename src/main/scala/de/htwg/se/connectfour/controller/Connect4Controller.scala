package de.htwg.se.connectfour.controller

import de.htwg.se.connectfour.model.Grid
import de.htwg.se.connectfour.model.Player

class Connect4Controller(var grid:Grid, player:Array[Player]) {
  
  def quit(){
    //quit game
  }
  
  def restart(){
    //restart game
  }
  
  def getDimension:String=return "[0,"+grid.width+"]"
   
  
  def hasWon()=grid.hasWon()
  
  def isValid(number:Int):Boolean=grid.checkInput(number)
  
  def insertCoin(number:Int, player:Player)= {
    grid=grid.insertCoinCol(number, player)
    println("Player " + player.name + " inserts " + number)  
  }
  
  def printout():String=grid.printout()

  
  
}