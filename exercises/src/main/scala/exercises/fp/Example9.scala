package exercises.fp

object Example9 {

  sealed trait Direction
  case object Forward  extends Direction
  case object Backward extends Direction

  case class Train(position: Int, direction: Direction, speed: Int) {
    // is `slowDown` an FP function?
    def slowDown: Train =
      copy(speed = (speed - 1) max 0)
  }

}
