package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 18/11/2014.
 */

trait UserDaoSlick{ this: DriverSupport =>

  import driver.simple._


  class Users(tag: Tag) extends Table[User](tag, "users") {

    def id: Column[Int] = column[Int]("id", O.PrimaryKey, O.AutoInc)
    def email: Column[String] = column[String]("email", O.NotNull)
    def name: Column[String] = column[String]("name", O.Nullable)
    def surname1: Column[String] = column[String]("surname1", O.Nullable)
    def surname2: Column[String] = column[String]("surname2", O.Nullable)

    def * = (id, email, name.?, surname1.?, surname2.?) <>((User.apply _).tupled, User.unapply)

  }

  val users = TableQuery[Users]

  def create(implicit session: Session) = users.ddl.create

  def getAll(implicit session: Session) =  Option(users.list)

  def get(id: Int)(implicit session: Session): Option[User] = users.filter(_.id === id).firstOption

  def add(user: User)(implicit session: Session) = {
    val newId = (users returning users.map(_.id)) += user
    user.copy(id = newId)
  }


  def delete(id: Int)(implicit session: Session) = users.filter(_.id === id).delete

}

