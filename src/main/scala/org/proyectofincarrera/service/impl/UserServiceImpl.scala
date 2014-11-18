package org.proyectofincarrera.service.impl

import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserServiceImpl extends UserService {

  override def list() : Option[List[User]] = Option(List(User(1, "giancarlo@mail.com")))

}
