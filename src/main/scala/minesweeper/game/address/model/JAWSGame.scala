package minesweeper.game.address.model

import minesweeper.game.address.MainApp
import javafx.scene.control.Button
import javafx.animation.{Animation, KeyFrame, Timeline}
import javafx.util.Duration
import scalafx.scene.control.{Alert, Label}
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.{control => sfxsc}

class JAWSGame() {
  // Initialize game components
  var mineCounter: SharkCounter = new SharkCounter()
  var timeCounter: TimeCounter = new TimeCounter()
  var mineField: SharkField = new SharkField(49)

  var gameEnded = false

  private var timeline: Timeline = _
  private var currentTime: Long = 0

  def executeTile(tileButton: Button, tile: Tile): Unit = {
      performTile(tileButton, tile)
  }

  private def performTile(tileButton: Button, tile: Tile): Unit = {
    if (!tile.isClicked) {
      tile.openTile(tileButton)
      checkStatus(mineField.listOfTiles)
    }
  }

  // End the game and display an alert for winning
  private def winGame(): Unit = {
    stopTimeCounter()
    val alert = new Alert(AlertType.Information) {
      initOwner(MainApp.stage)
      title = "PRG2104-JAWS Game-22018519"
      headerText = "CONGRATULATION! YOU WIN!"
      contentText = s"YOU HAVE ESCAPED FROM THE JAWS! \nTime you used: ${timeCounter.displayCounterValue()} seconds"
    }
    alert.showAndWait()
  }

  // End the game and display an alert for losing
  private def loseGame(): Unit = {
    stopTimeCounter()
    val alert = new Alert(AlertType.Information) {
      initOwner(MainApp.stage)
      title = "PRG2104-JAWS Game-22018519"
      headerText = "OH NO! YOU LOSE!!"
      contentText = s"YOU BECAME DINNER FOR THE SHARKS! \nTime you used: ${timeCounter.displayCounterValue()} seconds"
    }
    alert.showAndWait()
  }

  // Reveal hidden tiles at the end of the game
  def revealTiles(tileButtons: List[sfxsc.Button], tiles: Array[Tile]): Unit = {
    var tileButtonCounter = 0
    for (tile <- tiles) {
      if (!tile.isClicked) {
        tile.openTile(tileButtons(tileButtonCounter))
      }
      tileButtonCounter += 1
    }
  }

  // Check game status to determine win or loss
  private def checkStatus(listOfTiles: Array[Tile]): Unit = {
    var mineTileClicks = 0
    var revealedEmptyTiles = 0

    for (tile <- listOfTiles) {
      if (tile.tileType == "mine" && tile.isClicked) {
        mineTileClicks += 1
      } else if ((tile.tileType == "empty" || tile.tileType == "number")&& tile.isClicked) {
        revealedEmptyTiles += 1
      }
    }

    println(s"numberOfTiles: ${mineField.numberOfTiles}, mineTilesCount: ${mineField.mineTilesCount}")
    println(s"revealedEmptyTiles: $revealedEmptyTiles")

    if (mineTileClicks >= 1) {
      gameEnded = true
      stopTimeCounter()
      loseGame()

    } else if (revealedEmptyTiles == (mineField.numberOfTiles - mineField.mineTilesCount)) {
      gameEnded = true
      stopTimeCounter()
      winGame()
    }
  }
  // Start the time counter
  def startTimeCounter(label: Label): Unit = {
    timeline = new Timeline(
      new KeyFrame(Duration.seconds(1), _ => updateTimer(label))
    )
    timeline.setCycleCount(Animation.INDEFINITE)
    timeline.play()
  }
  // Update the timer and handle its state after the game ends
  private def updateTimer(label: Label): Unit = {
    if (!gameEnded) {
      currentTime = currentTime + 1
      label.text = timeCounter.displayCounterValue()
    } else {
      stopTimeCounter()
      label.text = timeCounter.displayCounterValue()
      timeline.stop()
      label.visible = false
    }
  }
  // Stop the time counter
  private def stopTimeCounter(): Unit = {
    if (timeline != null) {
      timeline.stop()
    }
  }
}
