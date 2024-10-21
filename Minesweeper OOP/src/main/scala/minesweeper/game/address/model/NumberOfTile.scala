package minesweeper.game.address.model

class NumberOfTile(var symbol: String = "1") extends Tile("number") {
  var numberOfMines = 1
  def incrementNumber(): Unit = {
    numberOfMines += 1
    symbol = numberOfMines.toString
  }
}

