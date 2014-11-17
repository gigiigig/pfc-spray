package org.proyectofincarrera.service

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 17/11/2014.
 */
case class UserService() {

  def list() : Option[List[User]] = Option(List(User(1, "giancarlo@mail.com")))

}
