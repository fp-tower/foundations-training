package exercises.fp

object Example3 {

  case class Order(clientId: Long, orderId: Long, product: String, price: Double)
  private var orders: Map[Long, Order] = Map.empty

  // are `getOrder` and `insertOrder` two FP functions?
  def getOrder(clientId: Long, orderId: Long): Option[Order] =
    orders
      .get(orderId)
      .filter(_.clientId == clientId)

  def insertOrder(order: Order): Unit =
    orders = orders.updated(order.orderId, order)

}
