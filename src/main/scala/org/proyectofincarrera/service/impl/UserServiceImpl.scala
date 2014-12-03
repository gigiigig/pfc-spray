package org.proyectofincarrera.service.impl

import org.proyectofincarrera.dao.impl.{DatabaseSupport, UserDaoSlick}
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserServiceImpl extends UserService with DatabaseSupport{

  val dao: UserDaoSlick

  import driver.simple._

  def list(): Option[List[User]] = database withSession { implicit session: Session =>
    Option(dao.getAll)
  }

}
