package org.proyectofincarrera

import com.wordnik.swagger.annotations._
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

  val userOperations = postRoute ~ readRoute ~ readAllRoute ~ deleteRoute


  @ApiOperation(value = "Get a user by id", httpMethod = "GET", response = classOf[User])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "userId", value="ID of the user that needs to retrieved", required = true, dataType = "integer", paramType = "path" )
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "User not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def readRoute =
    path("users" / IntNumber) { userId =>
      get {
        respondWithMediaType(`application/json`) {
          complete {
            userService.get(userId)
          }
        }
      }
    }

  @ApiOperation(value = "Get all the users", httpMethod = "GET", response = classOf[List[User]])
  def readAllRoute =
    path("users") {
      get {
        respondWithMediaType(`application/json`) {
          complete {
            userService.getAll
          }
        }
      }
    }

  @ApiOperation(value = "Delete a user by id", httpMethod = "DELETE", response = classOf[Int])
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "userId", value="ID of the user that needs to be deleted", required = true, dataType = "integer", paramType = "path" )
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 404, message = "User not found"),
    new ApiResponse(code = 400, message = "Invalid ID supplied")
  ))
  def deleteRoute =
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


  @ApiOperation(value = "Add a new user to the system", httpMethod = "POST", consumes="application/json")
  @ApiImplicitParams(Array(
    new ApiImplicitParam(name = "body", value="User object that need to be added", required = true, dataType = "User", paramType = "body" )
  ))
  @ApiResponses(Array(
    new ApiResponse(code = 405, message = "Invalid user")
  ))
  def postRoute =
    path("users") {
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
