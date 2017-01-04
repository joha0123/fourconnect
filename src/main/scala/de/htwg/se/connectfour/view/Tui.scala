package de.htwg.se.connectfour.view

import scala.swing.Reactor
import de.htwg.se.connectfour.controller._
import de.htwg.se.connectfour.model.impl.Player
import scala.util.matching.Regex
import scala.language.postfixOps

class Tui(var controller: Connect4Controller) extends Reactor {
  //Subscribe Reactor to Publisher
  listenTo(controller)

  reactions += {
    case GridChanged() => {
      printTui()
    }
    case PlayerHasWon() => {
      printWinningMessage()
      //Exit the Game
      System.exit(-1)
    }
  }

  printTui()

  def processInputLine(): Boolean = {
    var continue = true

    val numbers = new Regex("[0-9]*")
    val input = scala.io.StdIn.readLine()
    input match {
      case "q" => {
        printExitMessage()
        continue = false
      }
      case "u" => controller.undo // Spieler muss nichtmehr geändert werden, da das letzte Grid zurückgegeben wird
      case "r" => controller.redo // und darin ist auch gespeichert wer dran war
      case numbers() => {
        if (controller.isValid(input.trim().toInt)) controller.insertCoin(input.trim().toInt, controller.getActivePlayer())
        else printInputErrorMessage()
      }
      case _ => printInputErrorMessage()
    }
    continue
  }

  def printTui() = {
    var builder: StringBuilder = new StringBuilder()
    for (viewRow <- (0 until controller.getGridHeight()) reverse) {
      for (viewCol <- (0 until controller.getGridWidth())) {
        controller.getCell(viewRow, viewCol).content match {
          case Some(coin) => builder.append(coin.player.id.toString()).append(" ")
          case None => builder.append("N ")
        }
      }
      builder.append("\n")
    }
    println(builder.toString())
    printInsertNextCoinMessage()
  }

  def printWinningMessage() = println("Congratulations, Player " + controller.getActivePlayer().name + " has won the Game!")
  def printInsertNextCoinMessage() = println(controller.getActivePlayer().name + " please insert a Coin (0-" + (controller.getGridWidth() - 1) + ") | q: quit | u: undo | r: redo")
  def printInputErrorMessage() = println("Incorrect Input! Available commands are: (0-" + (controller.getGridWidth() - 1) + ") | q: quit | u: undo | r: redo")
  def printExitMessage() = println("Shutting down the game. Thank you for playing :)")
}