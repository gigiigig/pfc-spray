package org.proyectofincarrera.dao

import org.proyectofincarrera.model.UserPassword
import org.proyectofincarrera.utils.DatabaseConfig._

/**
 * Created by gneotux on 10/03/15.
 */
trait PasswordDaoSlick {
  import profile.api._

  class Passwords(tag: Tag) extends Table[UserPassword](tag, "passwords") {
    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def hashedPassword: Rep[String] = column[String]("hashed_password")
    def salt: Rep[String] = column[String]("salt")

    def * = (id, hashedPassword.?, salt) <>((UserPassword.apply _).tupled, UserPassword.unapply)
  }

  val passwords = TableQuery[Passwords]

  def create = passwords.schema.create

  def get(id: Int): DBIO[UserPassword] = passwords.filter(_.id === id).result

  def add(password: UserPassword): DBIO[Int] = (passwords returning passwords.map(p => Option(p.id))) += password

}

object PasswordDao extends PasswordDaoSlick
