package de.htwg.se.connectfour.model

import org.scalatest._
import java.awt.Color
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner
import de.htwg.se.connectfour.model.impl.Player
import de.htwg.se.connectfour.model.impl.Coin

class CoinSpec extends FlatSpec {
  behavior of "A Coin"

  it should "have a Player" in {
    val coin = new Coin(new Player(1, "", Color.red))
    assert(coin.player.isInstanceOf[Player])
  }

  it should "have the correct Player" in {
    val coin = new Coin(new Player(2, "", Color.red))
    assert(coin.player.id.equals(2))
  }

}