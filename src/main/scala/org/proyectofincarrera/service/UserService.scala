package org.proyectofincarrera.service

import org.proyectofincarrera.model.User

/**
 * Created by Gneotux on 17/11/2014.
 */
trait UserService {

  def list() : Option[List[User]]

}
