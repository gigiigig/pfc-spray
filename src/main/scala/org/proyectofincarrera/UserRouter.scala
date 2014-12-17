package org.proyectofincarrera

import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService
import spray.http.MediaTypes._
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._
import spray.routing.HttpService


/**
 * Created by Gneotux on 15/11/2014.
 */
// this trait defines our service behavior independently from the service actor
trait UserRouter extends HttpService {

  val userService: UserService


  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("users" / IntNumber) { userId =>
      get {
        respondWithMediaType(`application/json`) {
          complete{
            userService.get(userId)
          }
        }
      } ~
      delete {
        respondWithStatus(200) {
          complete {
            userService.delete(userId)
            userId.toString
          }
        }
      }
    } ~
    path("users" / ) {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            userService.getAll
          }
        }
      } ~
      post {
        entity(as[User]) { user =>
          respondWithMediaType(`application/json`) {
            complete {
              userService.add(user)
            }
          }
        }
      }
    }

}
