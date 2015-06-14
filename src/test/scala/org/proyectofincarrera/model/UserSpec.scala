package org.proyectofincarrera.model

import org.specs2.mutable._


/**
 * Created by Gneotux on 16/11/2014.
 */
class UserSpec extends Specification {


  "apply" should {
    "return a User with name, surnames1 and surnames2 None when they are not defined in the instantiation" in {
      val actual = User(1, "test@mail.com")
      actual.name must beNone
      actual.surname must beNone
    }

    "return a User with the fields passed" in {
      val actual = User(1, "test@mail.com", Option("Giancarlo"), Option("Muñoz"))
      actual.id must beEqualTo(1)
      actual.email must beEqualTo("test@mail.com")
      actual.name.get must beEqualTo("Giancarlo")
      actual.surname.get must beEqualTo("Muñoz")
    }

  }



}
