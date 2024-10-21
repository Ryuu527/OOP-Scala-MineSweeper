package minesweeper.game.address.model

class SharkCounter(var counterValue: Int = 0) extends Counter("mine") {
  override def displayCounterValue(): String = {
    val displayedValue = counterValue % 100 // Get the last two digits

    // Format the displayed value with leading zero if needed
    f"${displayedValue}%02d"
  }
}
