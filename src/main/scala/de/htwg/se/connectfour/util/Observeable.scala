package de.htwg.se.connectfour.util

abstract class Observeable {
  var observerList: Vector[IObserver] = Vector()
  def add(s: IObserver) = observerList = observerList.:+(s)
  def remove(s: IObserver) = observerList = observerList.filterNot { o => o == s }
  def notifyObservers() = observerList.foreach { o => o.update() }

}