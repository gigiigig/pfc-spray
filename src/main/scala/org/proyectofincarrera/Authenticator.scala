package org.proyectofincarrera

import org.proyectofincarrera.model.AuthInfo
import org.proyectofincarrera.service.UserService
import spray.routing.authentication.{BasicAuth, UserPass}
import spray.routing.directives.AuthMagnet

import scala.concurrent.{ExecutionContext, Future}

/**
 * Created by gneotux on 20/03/15.
 */
trait Authenticator {

  val userService: UserService

  def basicUserAuthenticator(implicit ec: ExecutionContext): AuthMagnet[AuthInfo] = {
    def validateUser(userPass: Option[UserPass]): Option[AuthInfo] = {
      for {
        p <- userPass
        (user, password) <- userService.get(p.user)
        if password.passwordMatches(p.pass)
      } yield AuthInfo(user)
    }

    def authenticator(userPass: Option[UserPass]): Future[Option[AuthInfo]] = Future { validateUser(userPass) }

    BasicAuth(authenticator _, realm = "Private API")
  }
}