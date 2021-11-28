package answers.dataprocessing

import scala.annotation.tailrec

object RecursionAnswers {

  sealed trait Json
  case class JsonNumber(number: Double)         extends Json
  case class JsonBoolean(bool: Boolean)         extends Json
  case class JsonString(text: String)           extends Json
  case class JsonObject(obj: Map[String, Json]) extends Json
  case class JsonArray(array: List[Json])       extends Json
  case object JsonNull                          extends Json

  def trimAll(json: Json): Json =
    json match {
      case _: JsonNumber | _: JsonBoolean => json
      case JsonString(str)                => JsonString(str.trim)
      case JsonObject(obj) =>
        JsonObject(obj.map { case (key, value) =>
          key -> trimAll(value)
        })
      case JsonArray(array) => JsonArray(array.map(trimAll))
      case JsonNull         => JsonNull
    }

  def anonymize(json: Json): Json =
    json match {
      case _: JsonNumber  => JsonNumber(0)
      case _: JsonBoolean => json
      case _: JsonString  => JsonString("***")
      case JsonObject(obj) =>
        JsonObject(obj.map { case (key, value) =>
          key -> anonymize(value)
        })
      case JsonArray(array) => JsonArray(array.map(anonymize))
      case JsonNull         => JsonNull
    }

  def search(json: Json, searchText: String, maxDepth: Int): Boolean =
    if (maxDepth < 0) false
    else
      json match {
        case _: JsonNumber | _: JsonBoolean | JsonNull => false
        case JsonString(text)                          => text.contains(searchText)
        case JsonObject(obj)                           => obj.values.exists(search(_, searchText, maxDepth - 1))
        case JsonArray(array)                          => array.exists(search(_, searchText, maxDepth - 1))
      }

  def depth(json: Json): Int =
    json match {
      case _: JsonNumber | _: JsonString | _: JsonBoolean | JsonNull =>
        0
      case JsonObject(obj) =>
        obj.values.map(depth).maxOption.fold(0)(_ + 1)
      case JsonArray(array) =>
        array.map(depth).maxOption.fold(0)(_ + 1)
    }

  @tailrec
  def contains[A](list: List[A], value: A): Boolean =
    list match {
      case Nil          => false
      case head :: tail => if (head == value) true else contains(tail, value)
    }

  def unsafeSum(numbers: List[Int]): Int =
    numbers match {
      case Nil          => 0
      case head :: tail => head + unsafeSum(tail)
    }

  def sum(numbers: List[Int]): Int = {
    @tailrec
    def go(numbers: List[Int], accumulator: Int): Int =
      numbers match {
        case Nil          => accumulator
        case head :: tail => go(tail, accumulator + head)
      }
    go(numbers, 0)
  }

  def min(numbers: List[Int]): Option[Int] = {
    @tailrec
    def go(numbers: List[Int], accumulator: Option[Int]): Option[Int] =
      numbers match {
        case Nil => accumulator
        case head :: tail =>
          val newState = accumulator match {
            case None          => Some(head)
            case Some(current) => Some(current min head)
          }
          go(tail, newState)
      }
    go(numbers, None)
  }

  def size[A](items: List[A]): Int = {
    @tailrec
    def go(items: List[A], accumulator: Int): Int =
      items match {
        case Nil       => accumulator
        case _ :: tail => go(tail, accumulator + 1)
      }
    go(items, 0)
  }

  def reverse[A](items: List[A]): List[A] = {
    @tailrec
    def go(items: List[A], accumulator: List[A]): List[A] =
      items match {
        case Nil          => accumulator
        case head :: tail => go(tail, head :: accumulator)
      }
    go(items, Nil)
  }

  @tailrec
  def foldLeft[From, To](items: List[From], default: To)(combine: (To, From) => To): To =
    items match {
      case Nil => default
      case head :: tail =>
        val newDefault = combine(default, head)
        foldLeft(tail, newDefault)(combine)
    }
}
