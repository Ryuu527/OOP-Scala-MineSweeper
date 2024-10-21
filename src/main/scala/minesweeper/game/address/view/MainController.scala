package minesweeper.game.address.view

import javafx.fxml.FXML
import minesweeper.game.address.model.JAWSGame
import javafx.{scene => jfxs}
import minesweeper.game.address.MainApp
import scalafx.event.ActionEvent
import scalafx.scene.control.{Button, Label}
import scalafx.scene.image.{Image, ImageView}
import scalafx.scene.layout.TilePane
import scalafx.scene.paint.Color
import scalafxml.core.macros.sfxml

@sfxml
class MainController(
                      @FXML private val mineCounter: Label,
                      @FXML private val timeCounter: Label,
                      @FXML private val mineField: TilePane,
                      @FXML private val tile1: Button, @FXML private val tile2: Button, @FXML private val tile3: Button,
                      @FXML private val tile4: Button, @FXML private val tile5: Button, @FXML private val tile6: Button,
                      @FXML private val tile7: Button, @FXML private val tile8: Button, @FXML private val tile9: Button,
                      @FXML private val tile10: Button, @FXML private val tile11: Button, @FXML private val tile12: Button,
                      @FXML private val tile13: Button, @FXML private val tile14: Button, @FXML private val tile15: Button,
                      @FXML private val tile16: Button, @FXML private val tile17: Button, @FXML private val tile18: Button,
                      @FXML private val tile19: Button, @FXML private val tile20: Button, @FXML private val tile21: Button,
                      @FXML private val tile22: Button, @FXML private val tile23: Button, @FXML private val tile24: Button,
                      @FXML private val tile25: Button, @FXML private val tile26: Button, @FXML private val tile27: Button,
                      @FXML private val tile28: Button, @FXML private val tile29: Button, @FXML private val tile30: Button,
                      @FXML private val tile31: Button, @FXML private val tile32: Button, @FXML private val tile33: Button,
                      @FXML private val tile34: Button, @FXML private val tile35: Button, @FXML private val tile36: Button,
                      @FXML private val tile37: Button, @FXML private val tile38: Button, @FXML private val tile39: Button,
                      @FXML private val tile40: Button, @FXML private val tile41: Button, @FXML private val tile42: Button,
                      @FXML private val tile43: Button, @FXML private val tile44: Button, @FXML private val tile45: Button,
                      @FXML private val tile46: Button, @FXML private val tile47: Button, @FXML private val tile48: Button,
                      @FXML private val tile49: Button
                    ) {

  // Create a new game instance
  val gameInstance = new JAWSGame()

  // Prepare tile buttons for easier access
  val tileButtons: List[Button] = List(
    tile1, tile2, tile3, tile4, tile5, tile6, tile7, tile8, tile9, tile10,
    tile11, tile12, tile13, tile14, tile15, tile16, tile17, tile18, tile19, tile20,
    tile21, tile22, tile23, tile24, tile25, tile26, tile27, tile28, tile29, tile30,
    tile31, tile32, tile33, tile34, tile35, tile36, tile37, tile38, tile39, tile40,
    tile41, tile42, tile43, tile44, tile45, tile46, tile47, tile48, tile49
  )

  // Generate tiles for the minefield
  gameInstance.mineField.listOfTiles = gameInstance.mineField.generateNumberTiles(gameInstance.mineField.generateEmptyAndMineTiles())

  // Initialize the time counter text
  timeCounter.text = gameInstance.timeCounter.displayCounterValue()

  // Initialize mine counter text
  gameInstance.mineCounter.counterValue = gameInstance.mineField.mineTilesCount
  mineCounter.text = gameInstance.mineCounter.displayCounterValue()

  // Initialize tile button
  for ((tileButton, tile) <- tileButtons.zip(gameInstance.mineField.listOfTiles)) {
    tileButton.text = tile.symbol
    tileButton.textFill = Color.Transparent
  }

  // Handle tile button click
  def handleClick(event: ActionEvent): Unit = {
    val tileButton = event.source.asInstanceOf[jfxs.control.Button]
    val tileNo = tileButton.getId.substring(4).toInt
    val tile = gameInstance.mineField.listOfTiles(tileNo - 1)
    gameInstance.executeTile(tileButton, tile)

    // Update mine counter value
    mineCounter.text = gameInstance.mineCounter.displayCounterValue()

    if (gameInstance.gameEnded) {
      // Reveal all tiles
      gameInstance.revealTiles(tileButtons, gameInstance.mineField.listOfTiles)

      // Stop the time counter
      gameInstance.timeCounter.stopTimeCounter()
      // Show the final time in the time counter label
      timeCounter.text = s"${gameInstance.timeCounter.displayCounterValue()}"

      // Update appearance of mine tiles with shark image
      for ((tileButton, tile) <- tileButtons.zip(gameInstance.mineField.listOfTiles)) {
        if (tile.tileType == "mine") {
          val image = new Image(getClass.getResource("/minesweeper.game.address/images/shark.png").toExternalForm)
          val imageView = new ImageView(image)
          imageView.setPreserveRatio(true)
          val size = Math.min(tileButton.getWidth, tileButton.getHeight)
          imageView.setFitHeight(size)
          imageView.setFitWidth(size)
          tileButton.graphic = imageView
        }
      }
    }
  }

  // Start the time counter
  gameInstance.timeCounter.startTimeCounter(timeCounter)

  def handleNew(): Unit = {
    MainApp.showGame()
  }
}
