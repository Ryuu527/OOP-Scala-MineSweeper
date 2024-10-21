package minesweeper.game.address.model

import scala.collection.mutable.ListBuffer
import scala.util.Random

class SharkField(val numberOfTiles: Int) {
  var listOfTiles: Array[Tile] = Array[Tile]()
  var emptyTilesCount: Int = 0
  var numberTilesCount: Int = 0
  var mineTilesCount: Int = 0

  // Generate shark tiles and empty tiles for the shark field randomly
  def generateEmptyAndMineTiles(): ListBuffer[Tile] = {
    val tilesBuffer: ListBuffer[Tile] = ListBuffer[Tile]()

    for (_ <- 1 to numberOfTiles) {
      if (Random.nextDouble() <= 0.1 && mineTilesCount < 5) {
        tilesBuffer += new SharkTile()
        mineTilesCount += 1
      } else {
        tilesBuffer += new VacantTile()
        emptyTilesCount += 1
      }
    }

    tilesBuffer
  }

  // Generate number tiles to indicate sharks for the shark field
  def generateNumberTiles(emptyAndMineTiles: ListBuffer[Tile]): Array[Tile] = {
    var tileIndex = 1

    for (_ <- 1 to numberOfTiles) {
      val currentTile = emptyAndMineTiles(tileIndex - 1)

      if (currentTile.tileType == "mine") {
        val surroundingTilesIndices = obtainSurroundingTilesIndices(tileIndex)

        for (surroundingTileIndex <- surroundingTilesIndices) {
          val surroundingTile = emptyAndMineTiles(surroundingTileIndex - 1)

          if (surroundingTile.tileType == "empty") {
            emptyAndMineTiles(surroundingTileIndex - 1) = new NumberOfTile()
            emptyTilesCount -= 1
            numberTilesCount += 1
          } else if (surroundingTile.tileType == "number") {
            surroundingTile.asInstanceOf[NumberOfTile].incrementNumber()
          }
        }
      }

      tileIndex += 1
    }

    emptyAndMineTiles.toArray
  }

  // Obtain the indices of the surrounding tiles
  def obtainSurroundingTilesIndices(tileIndex: Int): List[Int] = {
    tileIndex match {
      case 1 => List(tileIndex + 1, tileIndex + 7, tileIndex + 8)
      case 7 => List(tileIndex - 1, tileIndex + 6, tileIndex + 7)
      case 43 => List(tileIndex - 7, tileIndex - 6, tileIndex + 1)
      case 49 => List(tileIndex - 8, tileIndex - 7, tileIndex - 1)
      case _ if tileIndex >= 2 && tileIndex <= 6 => // Define the mapping for other indices
        List(tileIndex - 1, tileIndex + 1, tileIndex + 6, tileIndex + 7, tileIndex + 8)
      case _ if tileIndex >= 44 && tileIndex <= 48 => // Define the mapping for other indices
        List(tileIndex - 8, tileIndex - 7, tileIndex - 6, tileIndex - 1, tileIndex + 1)
      case _ if tileIndex % 7 == 1 => // Define the mapping for other indices
        List(tileIndex - 7, tileIndex - 6, tileIndex + 1, tileIndex + 7, tileIndex + 8)
      case _ if tileIndex % 7 == 0 => // Define the mapping for other indices
        List(tileIndex - 8, tileIndex - 7, tileIndex - 1, tileIndex + 6, tileIndex + 7)
      case _ => // Define the mapping for remaining indices
        List(tileIndex - 8, tileIndex - 7, tileIndex - 6, tileIndex - 1, tileIndex + 1,
          tileIndex + 6, tileIndex + 7, tileIndex + 8)
    }
  }
}
