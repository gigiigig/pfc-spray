package org.proyectofincarrera.service

import org.proyectofincarrera.dao.{PasswordDao, PasswordDaoSlick, UserDao, UserDaoSlick}
import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.router.UserDto
import org.proyectofincarrera.utils.DatabaseConfig._
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
 * Created by Gneotux on 18/11/2014.
 */
trait UserService {

  val userDao: UserDaoSlick
  val passwordDao: PasswordDaoSlick

  def add(user: UserDto): Future[Option[User]] = db.run {
    for {
      passwordId <- passwordDao.add(UserPassword newWithPassword user.password)
      user <- userDao.add(populateUser(user).copy(passwordId = Some(passwordId)))
    } yield Option(user)
  }

  def getAll() = db.run {
    userDao.getAll
  }

  def get(id: Int) = db.run {
    userDao.get(id)
  }

  def get(email: String): Future[Option[(User, UserPassword)]] = db.run {
    userDao.get(email)
  }

  def delete(id: Int) = db.run {
    userDao.delete(id)
  }

  implicit def populateUser: UserDto => User = (userDto: UserDto) => User(0, userDto.email, userDto.name, userDto.surname)
}

object UserService extends UserService {
  override val userDao = UserDao
  override val passwordDao = PasswordDao
}
