package org.proyectofincarrera.model

import com.wordnik.swagger.annotations.{ApiModel, ApiModelProperty}
import spray.json.DefaultJsonProtocol

import scala.annotation.meta.field

/**
 * Created by Gneotux on 16/11/2014.
 */
@ApiModel(description = "A User entity")
case class User(
  @(ApiModelProperty @field)(value = "unique identifier for the user")
  id: Int,

  @(ApiModelProperty @field)(value = "email of the user")
  email: String,

  @(ApiModelProperty @field)(value = "user's name")
  name: Option[String] = None,

  @(ApiModelProperty @field)(value = "user's first surname")
  surname1: Option[String] = None,

  @(ApiModelProperty @field)(value = "users's last surname")
  surname2: Option[String] = None
)
object User extends DefaultJsonProtocol{
  implicit val userFormat = jsonFormat5(User.apply)
}
