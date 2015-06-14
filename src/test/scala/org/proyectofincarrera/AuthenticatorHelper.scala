package org.proyectofincarrera

import org.proyectofincarrera.model.{User, AuthInfo}
import spray.routing.authentication.{UserPass, BasicAuth}
import spray.routing.directives.AuthMagnet

import scala.concurrent.{Future, ExecutionContext}

/**
 * Created by gneotux on 14/06/15.
 */
trait AuthenticatorHelper extends Authenticator {

  val testUser = User(1, "test@test.com")

  override def basicUserAuthenticator(implicit ec: ExecutionContext): AuthMagnet[AuthInfo] =
    BasicAuth( (_ : Option[UserPass]) => Future {Some(AuthInfo(testUser))}, realm = "Private API")

}
