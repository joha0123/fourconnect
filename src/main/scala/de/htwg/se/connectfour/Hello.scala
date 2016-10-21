package de.htwg.se.connectfour

import de.htwg.se.connectfour.model.Student

object Hello {
  def main(args: Array[String]): Unit = {
    val student = Student("Your Name")
    println("Hello, " + student.name)
  }
}
