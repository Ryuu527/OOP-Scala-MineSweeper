package minesweeper.game.address.model

import javafx.scene.image.{Image, ImageView}
import javafx.scene.control.Button

class SharkTile(var symbol: String = "") extends Tile("mine") {
  val imageView = new ImageView()

  def setImage(imagePath: String, button: Button): Unit = {
    val image = new Image(imagePath)
    imageView.setImage(image)
    imageView.setFitHeight(button.getHeight)
    imageView.setFitWidth(button.getWidth)
    imageView.setPreserveRatio(true)
    button.setGraphic(imageView)
  }
}