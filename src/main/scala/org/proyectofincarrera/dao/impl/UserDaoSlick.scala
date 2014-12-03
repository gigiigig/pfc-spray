package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 18/11/2014.
 */

trait UserDaoSlick{ this: DatabaseSupport =>

  import driver.simple._


  class Users(tag: Tag)
    extends Table[User](tag, "USERS") {

    def * = (id, email, name.?, surname1.?, surname2.?) <>((User.apply _).tupled, User.unapply)

    def id: Column[Int] = column[Int]("ID", O.PrimaryKey)

    def email: Column[String] = column[String]("EMAIL", O.NotNull)

    def name: Column[String] = column[String]("NAME", O.Nullable)

    def surname1: Column[String] = column[String]("SURNAMES1", O.Nullable)

    def surname2: Column[String] = column[String]("SURNAMES2", O.Nullable)

  }

  val users = TableQuery[Users]

  def create(implicit session: Session) = users.ddl.create

  def getAll(implicit session: Session) =  users.list

}

