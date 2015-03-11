package org.proyectofincarrera.model

/**
 * Created by gneotux on 10/03/15.
 */
case class Password(
  id: Option[Int],
  userId: Int,
  hashedPassword: Option[String] = None
){

}
