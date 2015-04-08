package org.proyectofincarrera.model

/**
 * Created by gneotux on 20/03/15.
 */
case class AuthInfo(user: User) {
  def hasPermissions(permission: String) = true
}
