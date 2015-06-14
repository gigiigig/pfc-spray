package org.proyectofincarrera.service

import org.proyectofincarrera.model.{User, UserPassword}
import org.proyectofincarrera.router.UserDto

/**
 * Created by Gneotux on 17/11/2014.
 */
trait UserService {

  def add(user: UserDto): User

  def delete(id: Int)

  def get(id: Int): Option[User]

  def get(email: String): Option[(User, UserPassword)]

  def getAll() : Option[List[User]]

}
