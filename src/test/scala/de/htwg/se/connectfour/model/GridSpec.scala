package de.htwg.se.connectfour.model

import org.scalatest.FlatSpec

import java.awt.Color
import org.scalatest.junit.JUnitRunner
import org.junit.runner.RunWith
import java.io.ByteArrayOutputStream

@RunWith(classOf[JUnitRunner])
class GridSpec extends FlatSpec {

  //width x        //height y
  val height = 6;
  val width = 7;
  val p1 = new Player("Johannes", Color.BLACK);
  val p2 = new Player("Patrick", Color.BLACK);

  val player: Array[Player] = new Array[Player](2)
  player(0) = p1; player(1) = p2;

  behavior of "an empty grid"
  var grid1 = new Grid(height, width);

  it should "have no coin in every Cell" in {
    grid1.cells.foreach { x =>
      assert(x.content.equals(None))
    }
  }

  it should "be possible to insert a coin at cell" in {
    grid1 = grid1.insertCoinAt(0, 0, p2)
    assert(grid1.row(0)(0).content.isInstanceOf[Some[Coin]])
    assert(grid1.col(0)(0).content.isInstanceOf[Some[Coin]])

  }

  it should "throw an exception for integers >hight/widht" in {
    intercept[IllegalArgumentException] {
      grid1 = grid1.insertCoinAt(0, 10, p2)
      grid1 = grid1.insertCoinAt(10, 0, p2)
      grid1 = grid1.insertCoinAt(-10, 0, p2)
      grid1 = grid1.insertCoinAt(0, -10, p2)
    }
  }

  it should "be possible to insert a coin at col" in {
    grid1 = grid1.insertCoinCol(0, p2)
    assert(grid1.row(0)(0).content.isInstanceOf[Some[Coin]])
    assert(grid1.col(0)(0).content.isInstanceOf[Some[Coin]])

  }

  it should "validate the tui input" in {
    assert(grid1.checkInput("2").equals((2, true)))
    assert(grid1.checkInput("     2").equals((2, true)))
    assert(grid1.checkInput("einString").equals((0, false)))
    assert(grid1.checkInput("-2").equals((0, false)))
    assert(grid1.checkInput("20").equals((0, false)))

  }

  it should "print the grid" in {
    
    val grid="N N N N N N N \n"+
    "N N N N N N N \n"+
    "N N N N N N N \n"+
    "N N N N N N N \n"+
    "2 N N N N N N \n"+
    "2 N N N N N N \n"
    assert(grid.equals(grid1.printout()))

  }

  behavior of "winning grid"
  var grid2 = new Grid(height, width);
  var emptygrid = new Grid(height, width)

  it should "have 4 equal coins in any row" in {

    for (y <- (0 until height)) {
      for (x1 <- (0 until width) if (width - 3 > x1)) {
        for (x <- (x1 until x1 + 4) if (x < grid1.width)) {
          grid2 = grid2.insertCoinAt(x, y, p1)
        }
        assert(grid2.hasWon() == true)
        grid2 = emptygrid
      }
    }

  }

  it should "have 4 equal coins in any col" in {

    for (y <- (0 until height) if (height - 3 > y)) {
      for (x1 <- (0 until width)) {
        for (y1 <- (y until y + 4) if (y1 < grid1.height)) {
          grid2 = grid2.insertCoinAt(x1, y1, p1)
        }
        assert(grid2.hasWon() == true)
        grid2 = emptygrid
      }
    }

  }

  it should "have 4 equal coins in any diagonal" in {
    var y3 = 0
    var x3 = 0
    for (y <- (0 until height) if (height - 3 > y)) {
      for (x1 <- (0 until width) if (x1 < width - 3)) {
        y3 = y
        x3 = x1
        for (y1 <- (0 until 4)) {
          grid2 = grid2.insertCoinAt(x3, y3, p1)
          x3 = x3 + 1; y3 = y3 + 1;
        }

        assert(grid2.hasWon() == true)
        grid2 = emptygrid
      }
    }
  }

  behavior of "full grid"

  it should "not be possible to insert a coin at col" in {
    for (y <- (0 to height + 1)) {
      grid2 = grid2.insertCoinCol(0, p2)
    }
    var grid3 = grid2
    grid2 = grid2.insertCoinCol(0, p2)
    assert(grid3.equals(grid2))

  }

}