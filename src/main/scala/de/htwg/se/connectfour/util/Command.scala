package de.htwg.se.connectfour.util

import de.htwg.se.connectfour.model.IGrid

trait Command {
  def execute: IGrid;
  def undo: IGrid;
}