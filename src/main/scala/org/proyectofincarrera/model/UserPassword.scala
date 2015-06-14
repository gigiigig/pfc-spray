package org.proyectofincarrera.model

import com.github.t3hnar.bcrypt.generateSalt
import com.github.t3hnar.bcrypt.Password

/**
 * Created by gneotux on 10/03/15.
 */
case class UserPassword(
  id: Int,
  hashedPassword: Option[String],
  salt: String = generateSalt
){
  def passwordMatches(password: String): Boolean = this.hashedPassword.contains(password.bcrypt(salt))
}
object UserPassword {
  def newWithPassword(password: String) = {
    val salt = generateSalt
    new UserPassword(0, Some(password.bcrypt(salt)), salt)
  }
}
