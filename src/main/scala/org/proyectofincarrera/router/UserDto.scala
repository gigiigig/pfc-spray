package org.proyectofincarrera.router

import com.wordnik.swagger.annotations.{ApiModel, ApiModelProperty}
import spray.json.DefaultJsonProtocol

import scala.annotation.meta.field

/**
 * Created by gneotux on 14/06/15.
 */
@ApiModel(description = "A User creation entity")
case class UserDto(
  @(ApiModelProperty @field)(value = "email of the user")
  email: String,

  @(ApiModelProperty @field)(value = "name of the user")
  name: Option[String] = None,

  @(ApiModelProperty @field)(value = "surname of the user")
  surname: Option[String] = None,

  @(ApiModelProperty @field)(value = "password of the user")
  password: String )

object UserDto extends DefaultJsonProtocol{
  implicit val userDtoFormat = jsonFormat4(UserDto.apply)
}
