package org.proyectofincarrera.model

/**
 * Created by Gneotux on 16/11/2014.
 */
case class User (
  id: Int,
  email: String,
  val name: Option[String] = None,
  surname1: Option[String] = None,
  surname2: Option[String] = None
){

}
