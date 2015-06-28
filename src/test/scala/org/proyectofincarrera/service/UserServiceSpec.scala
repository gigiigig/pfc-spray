package org.proyectofincarrera.service

import org.proyectofincarrera.dao.{PasswordDaoSlick, UserDaoSlick}
import org.specs2.mock._
import org.specs2.mutable.Specification

import scala.concurrent.Promise


/**
 * Created by Gneotux on 17/11/2014.
 */
class UserServiceSpec extends Specification with Mockito with UserService {

  override val userDao = mock[UserDaoSlick]
  override val passwordDao = mock[PasswordDaoSlick]

//  "list" should{
//    "be None" in {
//      getAll() must Promise
//    }
//  }

}
