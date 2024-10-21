package minesweeper.game.address

import minesweeper.game.address.view.MainController
import javafx.scene.control.SplitPane
import javafx.scene.layout.{AnchorPane, BorderPane}
import scalafx.Includes._
import scalafx.application.JFXApp
import scalafx.application.JFXApp.PrimaryStage
import scalafx.scene.Scene
import scalafx.scene.image.Image
import scalafxml.core.{FXMLLoader, NoDependencyResolver}

object MainApp extends JFXApp {
  val rootResource = getClass.getResourceAsStream("/minesweeper.game.address/view/Bar.fxml")
  val loader = new FXMLLoader(null, NoDependencyResolver)

  loader.load(rootResource)
  val roots = loader.getRoot[BorderPane]

  var gameStarted = false
  var gameControllerOption: Option[MainController#Controller] = None

  // Initialise stage
  stage = new PrimaryStage {
    title = "PRG2104-JAWS Game-22018519"
    scene = new Scene {
      root = roots
    }

    icons += new Image(getClass.getResourceAsStream("/minesweeper.game.address/images/jaws.jpg"))
  }

  // Display the menu
  def showMenu(): Unit = {
    val resource = getClass.getResourceAsStream("/minesweeper.game.address/view/MainMenu.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[AnchorPane]
    this.roots.setCenter(roots)

    gameStarted = false
  }

  // Display the game
  def showGame(): Unit = {
    val resource = getClass.getResourceAsStream("/minesweeper.game.address/view/Main.fxml")
    val loader = new FXMLLoader(null, NoDependencyResolver)
    loader.load(resource);
    val roots = loader.getRoot[SplitPane]
    this.roots.setCenter(roots)

    gameControllerOption = Option(loader.getController[MainController#Controller])
    gameStarted = true
  }

  // Display the menu when the app starts
  showMenu()

}
