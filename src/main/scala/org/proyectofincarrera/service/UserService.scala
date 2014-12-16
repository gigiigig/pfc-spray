package org.proyectofincarrera.service

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 17/11/2014.
 */
trait UserService {

  def add(user: User): User

  def delete(id: Int)

  def get(id: Int): Option[User]

  def getAll() : Option[List[User]]

}
