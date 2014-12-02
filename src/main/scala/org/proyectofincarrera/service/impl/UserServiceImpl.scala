package org.proyectofincarrera.service.impl

import org.proyectofincarrera.dao.impl.{DatabaseSupport, UserDaoSlick}
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserServiceImpl
  extends UserService
  with DatabaseSupport
{ userDao: UserDaoSlick =>
  import driver.simple._

  val dao = userDao

  def list(): Option[List[User]] = database withSession { implicit session: Session =>
    Option(dao.getAll.map(up => User(up.id, up.email, up.name, up.surname1, up.surname2)))
  }

}
