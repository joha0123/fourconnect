package de.htwg.se.connectfour.model

import org.scalatest.FlatSpec
import java.awt.Color

class GridSpec extends FlatSpec {
    //width x        //height y
                    val height=6;    
    val width=7;
    val p1=new Player("Johannes",Color.BLACK);
    val p2=new Player("Patrick",Color.BLACK);
    var grid1=new Grid(height,width);
    val player:Array[Player]=new Array[Player](2)
    player(0)=p1;      player(1)=p2;
    
    behavior of "an empty grid"
    
    it should "have no coin in every Cell" in{
      grid1.cells.foreach { x =>  
        assert(x.content.equals(None))
      }
    }
    
    it should "be possible to insert a coin" in{
      grid1=grid1.insertCoinAt(0, 0, p1)
      grid1=grid1.insertCoinAt(1, 1, p1)
      grid1=grid1.insertCoinAt(5, 6, p1)
      grid1.printout()
    }
  
  
//  
//  behavior of "winning grid"
//  
//  it should "have 4 equal coins in any row" in {
//   
//    
//    for(y<-(0 until height)){
//      for(x1<-(0 until width)){        
//        val arr:Array[Array[Cell]]=grid1.toTwoDimArray()
//        for(p<-(0 to 1)){
//        for(x<-(x1 until x1+5) if(x<grid1.width)){
//          
//            arr(x)(y)=new Cell(Option(new Coin(player(p))));
//          }
//
//        }
//        grid1=grid1.arrToVector(arr);
//        println("Grid:\n")
//        grid1.printout()
//        assert(grid1.hasWon()==true)
//        println("\n")
//      }
//    }
//    
//  }
  
  //  behavior of "An empty Cell"
//
//  it should "have no coin" in {
//    var cell = new Cell
//    assert(cell.coin == null)
//    assert(cell.isEmpty() == true)
//  }
//
//  it should "throw a Nullpointer Exception if the cells coin is accessed" in {
//    var cell = new Cell
//    intercept[NullPointerException] {
//      cell.coin.color
//    }
//  }
//
//  it should "be possible to insert a coin" in {
//    var cell = new Cell
//    assert(cell.insertCoin(new Coin(Color.red)) == true)
//    assert(cell.coin.isInstanceOf[Coin] == true)
//
//    var failCell = new Cell
//    assert(cell.insertCoin(null) == false)
//
//  }
//  
//
//  behavior of "A Cell"
//
//  it should "be empty after reset" in {
//    var cell = new Cell()
//    cell.insertCoin(new Coin(Color.red))
//    cell.reset()
//    assert(cell.coin == null)
//  }

  
}