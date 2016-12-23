package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.IGrid
import scala.collection.mutable.Stack

class CommandManager() {

  private var undos: Stack[Command] = new Stack
  private var redos: Stack[Command] = new Stack

  def executeCommand(command: Command): IGrid = {
    var grid = command.execute
    undos = undos.push(command)
    redos.clear()
    return grid
  }
  def isUndoAvailable: Boolean = undos.isEmpty.unary_!

  def isRedoAvailable: Boolean = redos.isEmpty.unary_!

  def undo: IGrid = {
    if (isUndoAvailable) {
      val command = undos.pop()
      val grid = command.undo
      redos = redos.push(command)
      return grid
    } else {
      return undos.pop().execute
    }
  }

  def redo: IGrid = {
    if (isRedoAvailable) {
      val command = redos.pop()
      val grid = command.execute
      undos = undos.push(command)
      return grid
    } else {
      return undos.pop().execute
    }
  }

}