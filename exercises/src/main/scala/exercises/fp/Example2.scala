package exercises.fp

object Example2 {

  // is `fizzBuzz` an FP function?
  def fizzBuzz(x: Int): String =
    if (x % 15 == 0) "FizzBuzz"
    else if (x % 3 == 0) "Fizz"
    else if (x % 5 == 0) "Buzz"
    else x.toString

  // what about `execFizzBuzz`?
  def execFizzBuzz: Unit =
    (1 until 100)
      .map(fizzBuzz)
      .map(println)

}
