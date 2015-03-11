package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.Password

/**
 * Created by gneotux on 10/03/15.
 */
trait PasswordDaoSlick { this: DriverSupport =>

  import driver.simple._


  class Passwords(tag: Tag) extends Table[Password](tag, "passwords") {

    def id: Column[Option[Int]] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def userId: Column[Int] = column[Int]("user_id", O.NotNull)
    def hashedPassword: Column[String] = column[String]("hashed_password", O.Nullable)

    def * = (id, userId, hashedPassword.?) <>((Password.apply _).tupled, Password.unapply)

  }

  val passwords = TableQuery[Passwords]

  def create(implicit session: Session) = passwords.ddl.create

  def get(id: Int)(implicit session: Session): Option[Password] = passwords.filter(_.id === id).firstOption

  def add(password: Password)(implicit session: Session) = {
    val newId = (passwords returning passwords.map(_.id)) += password
    password.copy(id = newId)
  }

}