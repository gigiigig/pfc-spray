package org.proyectofincarrera

import com.wordnik.swagger.annotations.{Api, ApiOperation}
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService
import spray.http.MediaTypes._
import spray.httpx.SprayJsonSupport._
import spray.routing.HttpService



/**
 * Created by Gneotux on 15/11/2014.
 */
// this trait defines our service behavior independently from the service actor
@Api(value = "/users", description = "Operations for users.", consumes= "application/json",  produces = "application/json")
trait UserRouter extends HttpService {

  val userService: UserService

  val userOperations = readRoute ~ deleteRoute ~ readAllRoute ~ postRoute


  @ApiOperation(value = "get a user by id", httpMethod = "GET")
  lazy val readRoute =
    path("users" / IntNumber) { userId =>
      get {
        respondWithMediaType(`application/json`) {
          complete {
            userService.get(userId)
          }
        }
      }
    }

  lazy val deleteRoute =
    path("users" / IntNumber) { userId =>
      delete {
        respondWithStatus(200) {
          complete {
            userService.delete(userId)
            userId.toString
          }
        }
      }
    }

  lazy val readAllRoute =
    path("users" / ) {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            userService.getAll
          }
        }
      }
    }

  lazy val postRoute =
    path("users" / ) {
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
