package de.htwg.se.connectfour.model

import org.scalatest.FlatSpec

import java.awt.Color
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith



@RunWith(classOf[JUnitRunner])
class PlayerSpec extends FlatSpec {
  val player1=new Player("Joha",Color.BLACK)
  val player2=new Player("Pat", Color.RED)
  
  behavior of "The first new Player"

  it should "have 1 as id" in {
    assert(player1.id==1)
  }
  
  it should "have 0 gameswon" in{
    assert(player1.gamesWon==0)
  }
  
  behavior of "A new Player"
  
  it should "have a name and Color" in {
    
    assert(player1.color.isInstanceOf[Color])
    assert(player1.color.equals(Color.BLACK))
    assert(player1.name.equals("Joha"))
  }
  
  behavior of "The second new Player"
  
  it should "have a 2 as id" in{
    assert(player2.id==2)
  }
  
  it should "have id as hashcode" in{
    assert(player2.id.equals(player2.hashCode()))   
  }
  
}