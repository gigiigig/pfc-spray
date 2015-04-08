package org.proyectofincarrera.model

import com.github.t3hnar.bcrypt.generateSalt

/**
 * Created by gneotux on 10/03/15.
 */
case class UserPassword(
  id: Int,
  hashedPassword: Option[String],
  salt: String = generateSalt
){
  import com.github.t3hnar.bcrypt.Password
  def withPassword(password: String) = copy(hashedPassword = Some(password.bcrypt(salt)))
  def passwordMatches(password: String): Boolean = this.hashedPassword.contains(password.bcrypt(salt))
}
