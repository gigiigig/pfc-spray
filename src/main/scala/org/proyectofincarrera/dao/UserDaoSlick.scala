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

    val id: Rep[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val email: Rep[String] = column[String]("email")
    val name: Rep[String] = column[String]("name")
    val surname: Rep[String] = column[String]("surname")
    val passwordId: Rep[Option[Int]] = column[Option[Int]]("password_id")

    def * : ProvenShape[(Int, String, String, String, Option[Int])] = (id, email, name, surname, passwordId)
  }

  private val users = TableQuery[Users]

  def create = users.schema.create

  def getAll: DBIO[Seq[(Int, String, String, String, Option[Int])]] = users.result


  def get(id: Int): DBIO[User] = users.filter(_.id === id).result

  def get(email: String) =
    for {
      user <- users.filter(_.email === email)
      password <- PasswordDao.passwords.filter(_.id === user.id)
    }yield (user, password)


  def add(user: User)(implicit session: Session) = {
    (users returning users) += user
  }

  def delete(id: Int) = users.filter(_.id === id).delete
}

object UserDao extends UserDaoSlick
