package org.proyectofincarrera.model

import spray.json.DefaultJsonProtocol

/**
 * Created by Gneotux on 16/11/2014.
 */
case class User(
  id: Int = -1,
  email: String,
  name: Option[String] = None,
  surname1: Option[String] = None,
  surname2: Option[String] = None
)
object User extends DefaultJsonProtocol{
  implicit val userFormat = jsonFormat5(User.apply)
}
