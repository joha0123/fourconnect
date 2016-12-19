package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.Grid
import scala.collection.mutable.Stack

class CommandManager() {
  
  private var undos:Stack[Command]=new Stack
  private var redos:Stack[Command]=new Stack
  
  
  def executeCommand(command:Command):Grid={
    var grid=command.execute
    undos=undos.push(command)
    redos.clear()
    return grid
  }
  def isUndoAvailable:Boolean=undos.isEmpty.unary_!
  
  def isRedoAvailable:Boolean=redos.isEmpty.unary_!
  
  def undo:Grid={
    if(isUndoAvailable){
      val command=undos.pop()
      val grid= command.undo
      redos=redos.push(command)
      return grid
    } else{
      return new Grid(6,7)
    }
  }
  
  def redo:Grid={
    if(isRedoAvailable){
      val command=redos.pop()
      val grid= command.execute
      undos=undos.push(command)
      return grid
    } else{
      if(isUndoAvailable)return undos.pop().execute else new Grid(6,7)
    }
  }
  
}