package org.proyectofincarrera.router

import org.mockito.Mockito._
import org.proyectofincarrera.dao.SpecSupport
import org.proyectofincarrera.service.UserService
import org.proyectofincarrera.{AuthenticatorHelper, Authenticator}
import org.proyectofincarrera.model.User
import org.specs2.mock._
import org.specs2.mutable.Specification
import spray.http.{HttpRequest, Rendering, HttpHeader, BasicHttpCredentials}
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.testkit.Specs2RouteTest
import scala.concurrent.Future


class UserRouterSpec extends Specification with Specs2RouteTest with UserRouter with SpecSupport with Authenticator with Mockito {

  override def actorRefFactory = system

  override val userService = UserService

  val user = BasicHttpCredentials( "gneotux@gmail.com" , "giancarlo86" )

  "Users Endpoint" should {

//    "return a greeting for GET requests to the root path" in {
//      Get() ~> userOperations ~> check {
//        responseAs[String] must contain("Api pfc")
//      }
//    }

    "leave GET requests to other paths unhandled" in this {
      Get("/kermit") ~> addCredentials(user) ~> userOperations ~>  check  {
        handled must beFalse
      }
    }

//    "return a MethodNotAllowed error for PUT requests to the root path" in {
//      Put() ~> userOperations ~> check {
//        status === NotFound
//        responseAs[String] === "The requested resource could not be found."
//      }
//    }
  }


  "Users Endpoint#users" should {

    "return a list of users for GET requests to users path" in this {
      Get("/users") ~> addCredentials(user) ~> userOperations ~> check {
        println(" XxXXXXXXXXxxxXXXXXX" + response)
        responseAs[Seq[User]] === List(User(1, "giancarlo@mail.com"))
      }
    }

    "return a single user for GET requests to users path" in this{
      Get("/users/314") ~> addCredentials(user) ~> userOperations ~> check {
        responseAs[User] === User(2, "giancarlo@mail.com")
      }
    }

    "return the id for DELETE requests to users path" in this{
      Delete("/users/314") ~> addCredentials(user) ~> userOperations ~> check {
        responseAs[String] === "314"
      }
    }

    "return the correct user for POST requests to users path" in this{
      Post("/users", UserDto("giancarlo3@mail.com", Some("Giancarlo"), None, "p44sw0rd")) ~> addCredentials(user) ~> userOperations ~> check {
        responseAs[User] === User(3, "giancarlo3@mail.com")
      }
    }

  }
}
