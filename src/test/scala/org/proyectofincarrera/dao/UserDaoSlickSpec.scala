package org.proyectofincarrera.dao

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends SpecSupport with UserDaoSlick {

  "getAll" should {
    "return the list of all users" in this {
        val expected = DatabaseSupportSpec.users
        getAll must beEqualTo(Some(expected))
      }

  }

  "get" should {
    "return the user passing the id" in this {
      val expected = Some(User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")))

      get(2) must beEqualTo(expected)

    }
    "return None when user not found" in this {
      get(288) must beNone
    }
  }

  "add" should {
    "add a new user to the DDBB" in this {
      val newUser = User(-1, "user4@mail.com", Some("name4"), Some("surname4"), Some("surname4"))
      val expected = User(4, "user4@mail.com", Some("name4"), Some("surname4"), Some("surname4"))

      val actual = add(newUser)

      actual must beEqualTo(expected)

    }
  }

  "delete" should {
    "remove the user" in this {

      val expected = Seq(
        User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
        User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2"))
      )

      delete(3)

      getAll must beEqualTo(Some(expected))

    }
  }

}
