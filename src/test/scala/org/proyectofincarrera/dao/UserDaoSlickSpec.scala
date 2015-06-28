package org.proyectofincarrera.dao

import org.proyectofincarrera.model.{UserPassword, User}
import org.proyectofincarrera.utils.DatabaseConfig._

import scala.concurrent.Await
import scala.concurrent.duration.Duration

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends SpecSupport with UserDaoSlick {

  "getAll" should {
    "return the list of all users" in this {
        val expected = DatabaseSupportSpec.users
        val actual: Seq[User] = Await.result(db.run(getAll), Duration.Inf)
        actual must beEqualTo(expected)
      }

  }

  "get" should {
    "return the user passing the id" in this {
      val expected = Some(User(2, "user2@mail.com", Some("name2"), Some("surname2")))

      val actual = Await.result(db.run(get(2)), Duration.Inf)

      actual must beEqualTo(expected)

    }
    "return None when user not found" in this {
      val actual: Option[User] = Await.result(db.run(get(288)), Duration.Inf)
      actual must beNone
    }
  }

  "add" should {
    "add a new user to the DDBB" in this {
      val newUser = User(0, "user4@mail.com", Some("name4"), Some("surname4"))
      val expected = User(4, "user4@mail.com", Some("name4"), Some("surname4"))

      val actual = add(newUser)

      actual must beEqualTo(expected)

    }
  }

  "delete" should {
    "remove the user" in this {

      val expected = Seq(
        User(1, "user1@mail.com", Some("name1"), Some("surname1")),
        User(2, "user2@mail.com", Some("name2"), Some("surname2"))
      )

      delete(3)

      getAll must beEqualTo(Some(expected))

    }
  }

}
