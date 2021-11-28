package answers.dataprocessing

import answers.dataprocessing.RecursionAnswers._
import org.scalatest.funsuite.AnyFunSuite
import org.scalatestplus.scalacheck.ScalaCheckDrivenPropertyChecks

class RecursionAnswersTest extends AnyFunSuite with ScalaCheckDrivenPropertyChecks {
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

  test("anonymize") {
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

  test("search") {
    assert(search(JsonObject(Map.empty), "ll", 5) == false)
    assert(search(JsonNumber(5), "ll", 5) == false)
    assert(search(JsonString("Hello"), "ll", 5) == true)
    assert(search(JsonObject(Map("message" -> JsonString("Hello"))), "ll", 5) == true)
    assert(search(JsonObject(Map("message" -> JsonString("Hello"))), "ss", 5) == false)
    assert(search(JsonObject(Map("message" -> JsonString("hi"))), "ll", 5) == false)

    assert(search(JsonObject(Map("user" -> JsonObject(Map("name" -> JsonString("John"))))), "o", 2) == true)
    assert(search(JsonObject(Map("user" -> JsonObject(Map("name" -> JsonString("John"))))), "o", 1) == false)
  }

  test("depth") {
    assert(depth(JsonNumber(1)) == 0)
    assert(depth(JsonObject(Map.empty)) == 0)
    assert(depth(JsonObject(Map("k" -> JsonNumber(1)))) == 1)
    assert(depth(john) == 2)
  }

  val largeSize = 100000

  test("contains") {
    assert(contains(List(1, 5, 2), 5) == true)
    assert(contains(List(1, 5, 2), 3) == false)
    assert(contains(Nil, 3) == false)
    assert(contains(List.fill(largeSize)(0), 1) == false)
  }

  test("unsafeSum is not stack-safe") {
    try {
      unsafeSum(List.fill(largeSize)(0))
      fail("Expected stack overflow")
    } catch {
      case _: StackOverflowError => succeed
      case e: Throwable          => fail(e)
    }
  }

  test("sum") {
    assert(sum(List(1, 5, 2)) == 8)
    assert(sum(Nil) == 0)
    assert(sum(List.fill(largeSize)(0)) == 0)
  }

  test("sum is consistent with std library") {
    forAll { (numbers: List[Int]) =>
      assert(sum(numbers) == numbers.sum)
    }
  }

  test("min is consistent with std library") {
    forAll { (numbers: List[Int]) =>
      assert(min(numbers) == numbers.minOption)
    }
  }

  test("reverse is consistent with std library") {
    forAll { (numbers: List[Int]) =>
      assert(reverse(numbers) == numbers.reverse)
    }
  }

  test("reverse twice is a noop") {
    forAll { (numbers: List[Int]) =>
      assert(reverse(reverse(numbers)) == numbers)
    }
  }

  test("foldLeft is consistent with std library") {
    forAll { (numbers: List[Int], default: Int, combine: (Int, Int) => Int) =>
      assert(foldLeft(numbers, default)(combine) == numbers.foldLeft(default)(combine))
    }
  }

}
