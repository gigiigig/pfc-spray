package org.proyectofincarrera.dao

import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.utils.DatabaseConfig._
import org.proyectofincarrera.utils.DatabaseConfig.profile.simple._
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach

object DatabaseSupportSpec {
  lazy val passwords = Seq(
    UserPassword(1, Some("WHATEVER1"), ""),
    UserPassword(2, Some("WHATEVER2"), ""),
    UserPassword(3, Some("WHATEVER3"), "")
  )
  lazy val users = Seq(
    User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
    User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
    User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3"))
  )
}

trait SpecSupport extends Specification with BeforeAfterEach {

  implicit var session: Session = _
  def createSchema(implicit session: Session) = {
    UserDao.create
    PasswordDao.create
    PasswordDao.passwords ++= DatabaseSupportSpec.passwords
    UserDao.users ++= DatabaseSupportSpec.users
  }

  override def before(): Unit={
    session = db.createSession()
    createSchema
  }

  override def after(): Unit = {
    session.close()
  }

}

