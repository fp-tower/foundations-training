package exercises.fp

object Example1 {

  sealed trait Role

  case class Reader(accountId: Long, premiumUser: Boolean) extends Role
  case class Editor(accountId: Long, favoriteFont: String) extends Role

  // is `findEditor` an FP function?
  def findEditor(accountId: Long, roles: List[Role]): Option[Editor] =
    roles match {
      case Nil => None
      case (_: Reader) :: otherRoles =>
        findEditor(accountId, otherRoles)
      case (editor: Editor) :: otherRoles if editor.accountId == accountId =>
        Some(editor)
    }

}
