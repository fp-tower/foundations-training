package exercises.fp

object Example8 {

  class User(initialAge: Int) {
    private var age: Int = initialAge

    // is `getAge` an FP function?
    def getAge: Int =
      age

    def setAge(newAge: Int): Unit =
      age = newAge
  }

}
