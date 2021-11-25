package exercises.fp

object Example6 {

  // is the constructor of `Item` (Item.apply) an FP function?
  case class Item(id: Long, name: String, quantity: Int, price: Double) {
    require(quantity > 0, "quantity must be positive")
    require(price > 0, "price must be positive")
  }

}
