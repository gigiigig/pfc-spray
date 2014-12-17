package org.proyectofincarrera.service.impl

import org.proyectofincarrera.dao.impl.{DatabaseSupport, DriverSupport, UserDaoSlick}
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserServiceImpl extends UserService with DatabaseSupport with DriverSupport{

  val dao: UserDaoSlick

  import driver.simple._

  def add(user: User): User = database withSession{ implicit session: Session =>
    dao.add(user)
  }

  def getAll(): Option[List[User]] = database withSession { implicit session: Session =>
    dao.getAll
  }

  def get(id: Int): Option[User] = database withSession { implicit session: Session =>
    dao.get(id)
  }

  def delete(id: Int) = database withDynSession{ implicit session: Session =>
    dao.delete(id)
  }
}
