package org.proyectofincarrera.service

import org.proyectofincarrera.dao.impl.{DatabaseSupportSpec, UserDaoSlick}
import org.proyectofincarrera.service.impl.UserServiceImpl
import org.specs2.mock._
import org.specs2.mutable.Specification


/**
 * Created by Gneotux on 17/11/2014.
 */
class UserServiceSpec extends Specification with Mockito with DatabaseSupportSpec with UserServiceImpl {

  val dao = mock[UserDaoSlick]

  "list" should{
    "be None" in {
      list() must beNone
    }
  }

}
