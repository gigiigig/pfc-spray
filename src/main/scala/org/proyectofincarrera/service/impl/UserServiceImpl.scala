package org.proyectofincarrera.service.impl

import org.proyectofincarrera.dao.{PasswordDao, PasswordDaoSlick, UserDao, UserDaoSlick}
import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.router.UserDto
import org.proyectofincarrera.service.UserService
import org.proyectofincarrera.utils.DatabaseConfig._

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserServiceImpl extends UserService{

  val userDao: UserDaoSlick
  val passwordDao: PasswordDaoSlick

  import profile.simple._

  override def add(user: UserDto): User = db withSession{ implicit session: Session =>
    val newPasswordId = passwordDao.add(UserPassword newWithPassword user.password)
    userDao.add(populateUser(user).copy(passwordId = Some(newPasswordId)))
  }

  override def getAll(): Option[List[User]] = db withSession { implicit session: Session =>
    userDao.getAll
  }

  override def get(id: Int): Option[User] = db withSession { implicit session: Session =>
    userDao.get(id)
  }

  override def get(email: String): Option[(User, UserPassword)] = db withSession { implicit session: Session =>
    userDao.get(email)
  }

  override def delete(id: Int) = db withSession{ implicit session: Session =>
    userDao.delete(id)
  }

  implicit def populateUser: UserDto => User = (userDto: UserDto) => User(0, userDto.email, userDto.name, userDto.surname)
}

object UserService extends UserServiceImpl {
  override val userDao = UserDao
  override val passwordDao = PasswordDao
}
