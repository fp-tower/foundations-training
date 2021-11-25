package exercises.fp

import java.time.LocalDate

object Example7 {

  case class Invoice(id: Long, amount: Double, dueDate: LocalDate) {
    // is`isOverdue` an FP function?
    def isOverdue: Boolean =
      LocalDate.now().isAfter(dueDate)
  }

}
