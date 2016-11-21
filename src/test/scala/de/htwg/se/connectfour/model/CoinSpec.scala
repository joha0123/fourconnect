package de.htwg.se.connectfour.model

import org.scalatest._
import java.awt.Color

class CoinSpec extends FlatSpec {
  behavior of "A Coin"
  
  it should "have a Player" in {
    val coin = new Coin(new Player("",Color.red))
    assert(coin.player.isInstanceOf[Player])
  }
  
  it should "have the correct Player" in {
   val coin = new Coin(new Player("",Color.red))
    assert(coin.player.id.equals(1)) 
  }
  
  

}