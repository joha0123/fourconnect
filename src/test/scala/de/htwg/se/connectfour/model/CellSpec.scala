package de.htwg.se.connectfour.model

import org.scalatest._
import java.awt.Color
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import org.scalatest.junit.JUnitRunner

//@RunWith(classOf[JUnitRunner])
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
    cell = cell.insertCoin(new Coin(new Player(1, "", Color.RED)))
    assert(cell.content.isInstanceOf[Option[Coin]] == true)
    assert(cell.isEmpty == false)

    //    var failCell = new Cell
    //    assert(cell.insertCoin(null).isInstanceOf[N])

  }

  behavior of "A Cell"

  it should "be empty after reset" in {
    var cell = new Cell()
    var cell2 = new Cell(Option(new Coin(new Player(1, "", Color.red))))

    cell = cell.insertCoin(new Coin(new Player(1, "", Color.red)))
    cell = cell.reset()
    assert(cell.content == None)
    assert(cell.isEmpty() == true)
    assert(cell2.content.isInstanceOf[Option[Coin]] == true)
    assert(cell2.isEmpty() == false)
  }

  it should ""

}