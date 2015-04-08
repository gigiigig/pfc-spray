package org.proyectofincarrera.service.impl

import org.proyectofincarrera.dao.{UserDao, UserDaoSlick}
import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.service.UserService
import org.proyectofincarrera.utils.DatabaseConfig._

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserServiceImpl extends UserService{

  val dao: UserDaoSlick

  import profile.simple._

  def add(user: User): User = db withSession{ implicit session: Session =>
    dao.add(user)
  }

  def getAll(): Option[List[User]] = db withSession { implicit session: Session =>
    dao.getAll
  }

  def get(id: Int): Option[User] = db withSession { implicit session: Session =>
    dao.get(id)
  }

  def get(email: String): Option[(User, UserPassword)] = db withSession { implicit session: Session =>
    dao.get(email)
  }

  def delete(id: Int) = db withSession{ implicit session: Session =>
    dao.delete(id)
  }
}

object UserService extends UserServiceImpl {
  override val dao = UserDao
}
