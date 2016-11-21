package de.htwg.se.connectfour.model

import org.scalatest._
import java.awt.Color
import org.scalatest.junit.JUnitRunner

class CellSpec extends FlatSpec {
  behavior of "An empty Cell"

  it should "have no coin" in {
    
    var cell = new Cell()   
    assert(cell.isEmpty() == true)
  }

//  it should "throw a Nullpointer Exception if the cells coin is accessed" in {
//    var cell = new Cell
//    intercept[NullPointerException] {
//      cell.content.color
//    }
//  }

  it should "be possible to insert a coin" in {
    var cell = new Cell
    cell.insertCoin(new Coin(new Player("",Color.RED)))
    assert(cell.content.isInstanceOf[Option[Coin]] == true)

//    var failCell = new Cell
//    assert(cell.insertCoin(null).isInstanceOf[N])

  }
  

  behavior of "A Cell"

  it should "be empty after reset" in {
    var cell = new Cell()
    cell.insertCoin(new Coin(new Player("",Color.red)))
    cell.reset()
    assert(cell.content == None)
  }

}