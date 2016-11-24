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
  
  it should "have all attributes" in {
    var p2=new Player(1)
    var p3=new Player(1,"Joha",Color.red)
    var p4=new Player(2,"patt",Color.BLACK,0)
//    assert(p2.id==1);   assert(p2.name.equals(""));   assert(p2.color.equals(Color.BLACK))
//    assert(p3.name.equals("Joha"));     assert(p3.id==1);    assert(p3.gamesWon==0);  
//    assert(p4.id==2); assert(p4.color.equals(Color.BLACK)); assert(p4.gamesWon==0);
//    assert(p4.name.equals("patt"))
  }
  
  it should "be equal" in {
    var grid=new Grid(0,0)
    var p2=new Player(1)
    var p3=new Player(1,"Joha",Color.red)
    var p4=new Player(2,"patt",Color.BLACK,0)
     assert(p3.equals(p2));         assert(!p3.equals(p4))
     assert(!p3.equals(grid))
  }
  
  behavior of "The second new Player"
  
  it should "have a 2 as id" in{
    assert(player2.id==2)
  }
  
  it should "have id as hashcode" in{
    assert(player2.id.equals(player2.hashCode()))   
  }
  
}