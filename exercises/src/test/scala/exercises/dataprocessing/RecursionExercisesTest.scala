package exercises.dataprocessing

import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks
import exercises.dataprocessing.RecursionExercises._

class RecursionExercisesTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {

  /////////////////////////////////////////////////////
  // Exercise 1: Json
  /////////////////////////////////////////////////////

  val john: Json = JsonObject(
    Map(
      "name" -> JsonString(" John Doe "),
      "age"  -> JsonNumber(25),
      "address" -> JsonObject(
        Map(
          "street-number" -> JsonNumber(25),
          "street-name"   -> JsonString("  Cody Road")
        )
      )
    )
  )

  test("trimAll") {
    assert(
      trimAll(john) == JsonObject(
        Map(
          "name" -> JsonString("John Doe"),
          "age"  -> JsonNumber(25),
          "address" -> JsonObject(
            Map(
              "street-number" -> JsonNumber(25),
              "street-name"   -> JsonString("Cody Road")
            )
          )
        )
      )
    )
  }

  ignore("anonymize") {
    assert(
      anonymize(john) == JsonObject(
        Map(
          "name" -> JsonString("***"),
          "age"  -> JsonNumber(0),
          "address" -> JsonObject(
            Map(
              "street-number" -> JsonNumber(0),
              "street-name"   -> JsonString("***")
            )
          )
        )
      )
    )
  }

  ignore("search") {
    assert(search(JsonObject(Map.empty), "ll") == false)
    assert(search(JsonNumber(5), "ll") == false)
    assert(search(JsonString("Hello"), "ll") == true)
    assert(search(JsonObject(Map("message" -> JsonString("Hello"))), "ll") == true)
    assert(search(JsonObject(Map("message" -> JsonString("Hello"))), "ss") == false)
    assert(search(JsonObject(Map("message" -> JsonString("hi"))), "ll") == false)
  }

  ignore("depth") {
    assert(depth(JsonNumber(1)) == 0)
    assert(depth(JsonObject(Map.empty)) == 0)
    assert(depth(JsonObject(Map("k" -> JsonNumber(1)))) == 1)
    assert(depth(john) == 2)
  }

  /////////////////////////////////////////////////////
  // Exercise 2: Stack safety
  /////////////////////////////////////////////////////

  ignore("unsafeSum is not correct") {}

  ignore("sum") {
    assert(sum(List(1, 5, 2)) == 8)
    assert(sum(Nil) == 0)
  }

  ignore("min") {
    assert(min(List(2, 5, 1, 8)) == Some(1))
    assert(min(Nil) == None)
  }

  ignore("reverse") {
    assert(reverse(List(2, 5, 1, 8)) == List(8, 1, 5, 2))
    assert(reverse(Nil) == Nil)
  }

  ignore("foldLeft") {}

}
