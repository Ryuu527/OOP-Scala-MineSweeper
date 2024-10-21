package minesweeper.game.address.view

import minesweeper.game.address.MainApp
import scalafx.application.Platform
import scalafx.scene.control.Alert.AlertType
import scalafx.scene.control.Alert
import scalafxml.core.macros.sfxml

@sfxml
class mainMenuController() {
  // Start the JAWS Game
  def handlePlay(): Unit = {
    MainApp.showGame()
  }

  // Show information about JAWS Game
  def handleAbout(): Unit = {
    new Alert(AlertType.Information) {
      initOwner(MainApp.stage)
      title = "About JAWS Game"
      headerText = "PRG2104-JAWS Game-22018519"
      contentText =
        "Embark on an exciting plunge into the world of JAWSSweeper, where the classic minesweeper game takes an underwater twist. " +
          "In this oceanic adventure, you'll explore a grid filled with hidden sharks, adding a new level of excitement to the game. " +
          "Your goal is to reveal safe tiles while avoiding the lurking predators. Get ready for a thrilling journey " +
          "as you strategically uncover tiles and work to outsmart these ocean creatures." +
          "\n\n**How to Play:**\n\n1. Objective: Your mission is to uncover all the safe tiles on the grid without revealing a shark." +
          " Uncover all the safe tiles to win!\n\n2. Revealing Tiles: Click on any tile to uncover it. If you come across a shark tile, " +
          "the game ends. Be cautious and choose your tiles wisely!\n\n3. Hints: As you reveal tiles, hints will show up on the " +
          "opened tiles to tell you how many sharks are nearby. Each number represents the count of shark tiles within the 9-block area" +
          " surrounding the hint tile.\n\n4. Using Strategy: To succeed, use logic and the hints to figure out where the shark tiles " +
          "might be hiding. Let the numbers guide you in making the best choices for your next move.\n\n5.Victory: If you manage to" +
          " uncover all the safe tiles, you'll emerge as the champion of the deep seas!"
    }.showAndWait()
  }

  // Quit the application
  def handleExit(): Unit = {
    Platform.exit()
  }
}
