package de.htwg.se.connectfour.model

class Cell() {

  var coin: Coin = null;

  def isEmpty(): Boolean = if (coin == null) true else false;

  def insertCoin(newCoin: Coin): Boolean =
    if (coin == null) {
      coin = newCoin;
      return true;
    } 
    else {
      return false;
    }

  def reset(): Unit = this.coin = null;

}