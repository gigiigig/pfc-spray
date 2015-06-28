package org.proyectofincarrera.dao

import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.utils.DatabaseConfig._
import org.proyectofincarrera.utils.DatabaseConfig.profile.api._
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach
import slick.dbio
import slick.dbio.Effect.{Write, Schema}
import scala.concurrent.duration.Duration

import scala.concurrent.{Future, Await}

object DatabaseSupportSpec {

  lazy val passwords = Seq(
    UserPassword(1, Some("$2a$10$mCw3TrY5mZ0GL9gTDwIDDu17db9acUwwWJrYF4t8qQTinmqItbdk2"), "$2a$10$mCw3TrY5mZ0GL9gTDwIDDu"),
    UserPassword(2, Some("WHATEVER2"), ""),
    UserPassword(3, Some("WHATEVER3"), "")
  )
  lazy val users = Seq(
    User(1, "gneotux@gmail.com", Some("name1"), Some("surname1"), Some(1)),
    User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some(2)),
    User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some(3))
  )
}

trait SpecSupport extends Specification with BeforeAfterEach {

  def createSchema = {
    val results = for {
      _ <- db.run(UserDao.create)
      _ <- db.run(PasswordDao.create)
      p <- db.run(PasswordDao.passwords ++= DatabaseSupportSpec.passwords)
      r <- db.run(UserDao.users ++= DatabaseSupportSpec.users)
    } yield r

//    Await.result(results, Duration.Inf)
  }

  override def before(): Unit={
    createSchema
  }

  override def after(): Unit = {
    db.close()
  }

}

