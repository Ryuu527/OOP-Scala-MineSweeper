package minesweeper.game.address.model

import javafx.scene.control.Button
import scalafx.scene.effect.InnerShadow
import scalafx.scene.paint.Color

abstract class Tile(val tileType: String) {
  var isClicked = false
  var symbol: String

  def openTile(tileButton: Button): Unit = {
    // Display the button text to show the symbol
    tileButton.setTextFill(Color.Black)
    // Change the look of the button to indicate clicked
    tileButton.setEffect(new InnerShadow())
    isClicked = true
  }


}

