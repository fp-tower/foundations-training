package exercises.fp

object Example5 {

  // is `increment` an FP function?
  def increment(array: Array[Int]): Unit =
    for { i <- array.indices } array(i) = array(i) + 1

}
