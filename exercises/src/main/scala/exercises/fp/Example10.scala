package exercises.fp

object Example10 {

  sealed trait Direction
  case object Forward  extends Direction
  case object Backward extends Direction

  case class Train(position: Int, direction: Direction, speed: Int) {
    // is `reverse` an FP function?
    def reverse: Train =
      if (speed != 0)
        throw new Exception("The train must be stopped before turning")
      else
        direction match {
          case Forward  => copy(direction = Backward)
          case Backward => copy(direction = Forward)
        }
  }

}
