package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.Grid

trait Command {
  def execute:Grid;
  def undo:Grid;
}