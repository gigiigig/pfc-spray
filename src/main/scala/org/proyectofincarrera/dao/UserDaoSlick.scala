package org.proyectofincarrera.dao

import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.utils.DatabaseConfig._

/**
 * Created by gneotux on 08/04/15.
 */
trait UserDaoSlick{
  import profile.simple._

  class Users(tag: Tag) extends Table[User](tag, "users") {

    val id: Column[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    val email: Column[String] = column[String]("email", O.NotNull)
    val name: Column[String] = column[String]("name", O.Nullable)
    val surname: Column[String] = column[String]("surname", O.Nullable)
    val passwordId: Column[Option[Int]] = column[Option[Int]]("password_id")

    def * = (id, email, name.?, surname.?, passwordId) <>((User.apply _).tupled, User.unapply)
  }

  val users = TableQuery[Users]

  def create(implicit session: Session) = users.ddl.create

  def getAll(implicit session: Session) =  Option(users.list)

  def get(id: Int)(implicit session: Session): Option[User] = users.filter(_.id === id).firstOption

  def get(email: String)(implicit session: Session): Option[(User, UserPassword)] =
    (for {
      user <- users.filter(_.email === email)
      password <- PasswordDao.passwords.filter(_.id === user.id)
    }yield (user, password)).firstOption


  def add(user: User)(implicit session: Session) = {
    val newId = (users returning users.map(_.id)) += user
    user.copy(id = newId)
  }

  def delete(id: Int)(implicit session: Session) = users.filter(_.id === id).delete
}

object UserDao extends UserDaoSlick
