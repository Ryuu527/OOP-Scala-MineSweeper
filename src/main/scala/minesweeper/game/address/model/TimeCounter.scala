package minesweeper.game.address.model

import scalafx.application.Platform
import scalafx.scene.control.Label
import java.util.{Timer, TimerTask}

class TimeCounter(var counterValue: Int = 0) extends Counter("time") {
  private var timeCounterTimer: Timer = _

  // Display positive digits using two places format (nn)
  override def displayCounterValue(): String = {
    f"${counterValue % 100}%02ds"
  }

  // Start updating the counter each second
  def startTimeCounter(counterLabel: Label): Unit = {
    val updateCounterRunnable = new Runnable() {
      def run(): Unit = {
        incrementCounter()
        Platform.runLater(() => counterLabel.text = displayCounterValue())
      }
    }
    timeCounterTimer = new Timer()
    val updateCounterTask = new TimerTask {
      override def run(): Unit = {
        updateCounterRunnable.run()
      }
    }
    timeCounterTimer.schedule(updateCounterTask, 1000, 1000)
  }

  // Stop updating the counter
  def stopTimeCounter(): Unit = {
    if (timeCounterTimer != null) {
      timeCounterTimer.cancel()
      timeCounterTimer.purge()
    }
  }

  // Hide the time counter label
  def hideTimeCounter(counterLabel: Label): Unit = {
    Platform.runLater(() => counterLabel.visible = false)
  }
}
