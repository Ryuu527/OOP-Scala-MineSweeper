package minesweeper.game.address.model

// Abstract class to represent different types of counters
abstract class Counter(val counterType: String) {
  var counterValue: Int

  // Increment the counter value
  def incrementCounter(): Unit = {
    counterValue += 1
  }
  // Decrement the counter value
  def decrementCounter(): Unit = {
    counterValue -= 1
  }
  // Get a formatted string to display the counter value
  def displayCounterValue(): String
}