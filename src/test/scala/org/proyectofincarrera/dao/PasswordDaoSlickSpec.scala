package org.proyectofincarrera.dao

import org.proyectofincarrera.model.UserPassword

class PasswordDaoSlickSpec extends SpecSupport with PasswordDaoSlick {

  "get" should {
    "return the password passing the id" in this {
      val expected = UserPassword(2, Some("WHATEVER2"), "")

      val actual = get(2).get

      actual.id must equalTo(expected.id)
      actual.hashedPassword must equalTo(expected.hashedPassword)
    }
    "return None when password not found" in this {
      get(288) must beNone
    }
  }

  "add" should {
    "add a new password to the DDBB" in this {
      val newPassword = UserPassword(-1, Some("hashedpassword4"))
      val expected = UserPassword(4, Some("hashedpassword4"))

      val actual = add(newPassword)

      actual.id must equalTo(expected.id)
      actual.hashedPassword must equalTo(expected.hashedPassword)
    }
  }


}
