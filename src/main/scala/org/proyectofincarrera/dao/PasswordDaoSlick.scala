package org.proyectofincarrera.dao

import org.proyectofincarrera.model.UserPassword
import org.proyectofincarrera.utils.DatabaseConfig._

/**
 * Created by gneotux on 10/03/15.
 */
trait PasswordDaoSlick {
  import profile.simple._

  class Passwords(tag: Tag) extends Table[UserPassword](tag, "passwords") {
    def id: Column[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def hashedPassword: Column[String] = column[String]("hashed_password", O.Nullable)
    def salt: Column[String] = column[String]("salt")

    def * = (id, hashedPassword.?, salt) <>((UserPassword.apply _).tupled, UserPassword.unapply)
  }

  val passwords = TableQuery[Passwords]

  def create(implicit session: Session) = passwords.ddl.create

  def get(id: Int)(implicit session: Session): Option[UserPassword] = passwords.filter(_.id === id).firstOption

  def add(password: UserPassword)(implicit session: Session) = {
    val newId = (passwords returning passwords.map(_.id)) += password
    password.copy(id = newId)
  }
}

object PasswordDao extends PasswordDaoSlick
