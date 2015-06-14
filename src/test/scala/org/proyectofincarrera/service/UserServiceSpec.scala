package org.proyectofincarrera.service

import org.proyectofincarrera.dao.{PasswordDaoSlick, UserDaoSlick}
import org.proyectofincarrera.service.impl.UserServiceImpl
import org.specs2.mock._
import org.specs2.mutable.Specification


/**
 * Created by Gneotux on 17/11/2014.
 */
class UserServiceSpec extends Specification with Mockito  with UserServiceImpl {

  override val userDao = mock[UserDaoSlick]
  override val passwordDao = mock[PasswordDaoSlick]

  "list" should{
    "be None" in {
      getAll() must beNull
    }
  }

}
