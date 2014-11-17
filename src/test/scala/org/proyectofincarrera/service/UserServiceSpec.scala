package org.proyectofincarrera.service

import org.specs2.mutable.Specification
import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 17/11/2014.
 */
class UserServiceSpec extends Specification {

  val service = UserService()

  "list" should{
    "be some list of users" in {

      service.list() must beSome


    }
  }

}
