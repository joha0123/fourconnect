package de.htwg.se.connectfour.view

import scala.swing.{ Reactor, Color }
import de.htwg.se.connectfour.controller._
import de.htwg.se.connectfour.model.impl.Player
import scala.util.matching.Regex
import scala.language.postfixOps

class Tui(var controller: Connect4Controller) extends Reactor {
  //Subscribe Reactor to Publisher
  listenTo(controller)

  reactions += {
    case e: GridChanged => {
      printTui()
    }
    case e: PlayerHasWon => {
      printWinningMessage(e.winner)
      controller.restart()
    }
    case e: Draw => {
      printDrawMessage()
      controller.restart()
    }
  }

  initPlayers()
  printTui()

  def initPlayers() = {
    println("### Player 1 ###");
    val p1 = new Player(inputPlayerName(), inputPlayerColor())

    println("### Player 2 ###");
    val p2 = new Player(inputPlayerName(), inputPlayerColor())
    controller.setPlayers(Vector(p1, p2))
  }

  def inputPlayerName() = {
    println("Please insert your name:")
    scala.io.StdIn.readLine()
  }

  def inputPlayerColor(): Color = {
    println("Please enter a color: 0-red | 1-yellow | 2-green | 3-black")
    scala.io.StdIn.readLine() match {
      case "0" => new Color(255, 0, 0)
      case "1" => new Color(255, 255, 0)
      case "2" => new Color(0, 255, 0)
      case "3" => new Color(0, 0, 0)
      case _ => inputPlayerColor()
    }
  }

  def processInputLine(): Unit = {
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

  def printWinningMessage(player: Player) = println("Congratulations, " + player.name + " has won the Game!")
  def printDrawMessage() = println("No more moves possible. Game Draw.")
  def printInsertNextCoinMessage() = println(controller.getActivePlayer().name + " please insert a Coin (0-" + (controller.getGridWidth() - 1) + ") | q: quit | u: undo | r: redo")
  def printInputErrorMessage() = println("Incorrect Input! Available commands are: (0-" + (controller.getGridWidth() - 1) + ") | q: quit | u: undo | r: redo")
  def printExitMessage() = println("Shutting down the game. Thank you for playing :)")
  //def printScoreMessage() = println("| " + controller.getPlayer(0) + " " +  )
}