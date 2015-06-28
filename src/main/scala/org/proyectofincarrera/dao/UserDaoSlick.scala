package org.proyectofincarrera.dao

import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.utils.DatabaseConfig._
import slick.lifted
import slick.lifted.ProvenShape


/**
 * Created by gneotux on 08/04/15.
 */
trait UserDaoSlick{
  import profile.api._

  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def email: Rep[String] = column[String]("email")
    def name: Rep[String] = column[String]("name")
    def surname: Rep[String] = column[String]("surname")
    def passwordId: Rep[Option[Int]] = column[Option[Int]]("password_id")

    def * = (id, email, name.?, surname.?, passwordId) <>((User.apply _).tupled, User.unapply)
  }

  val users = TableQuery[Users]

  def create = users.schema.create

  def getAll: DBIO[Seq[User]] = users.result


  def get(id: Int): DBIO[Option[User]] = users.filter(_.id === id).result.headOption

  def get(email: String): DBIO[Option[(User, UserPassword)]] =
    (for {
      user <- users.filter(_.email === email)
      password <- PasswordDao.passwords.filter(_.id === user.id)
    }yield (user, password)).result.headOption


  def add(user: User): DBIO[User] = {
    (users returning users) += user
  }

  def delete(id: Int) = users.filter(_.id === id).delete
}

object UserDao extends UserDaoSlick
