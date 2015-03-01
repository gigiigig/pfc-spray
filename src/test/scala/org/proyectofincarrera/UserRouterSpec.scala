package org.proyectofincarrera

import org.mockito.Mockito._
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService
import org.specs2.mock._
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.testkit.Specs2RouteTest

class UserRouterSpec extends Specification with Specs2RouteTest with UserRouter with Mockito {

  def actorRefFactory = system

  val userService = mock[UserService]

  "MyService" should {

//    "return a greeting for GET requests to the root path" in {
//      Get() ~> userOperations ~> check {
//        responseAs[String] must contain("Api pfc")
//      }
//    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> userOperations ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(userOperations) ~> check {
        status === NotFound
        responseAs[String] === "The requested resource could not be found."
      }
    }
  }


  "MyService#users" should {

    when(userService.getAll()).thenReturn(Some(List(User(1, "giancarlo@mail.com"))))
    "return a list of users for GET requests to users path" in {
      Get("/users/") ~> userOperations ~> check {
        responseAs[List[User]] === List(User(1, "giancarlo@mail.com"))
      }
    }

    when(userService.get(314)).thenReturn(Some(User(2, "giancarlo@mail.com")))
    "return a single user for GET requests to users path" in {
      Get("/users/314") ~> userOperations ~> check {
        responseAs[User] === User(2, "giancarlo@mail.com")
      }
    }

    "return the id for DELETE requests to users path" in {
      Delete("/users/314") ~> userOperations ~> check {
        responseAs[String] === "314"
      }
    }

    when(userService.add(User(-99,"giancarlo3@mail.com"))).thenReturn(User(3, "giancarlo3@mail.com"))
    "return the correct user for POST requests to users path" in {
      Post("/users/", User(-99,"giancarlo3@mail.com")) ~> userOperations ~> check {
        responseAs[User] === User(3, "giancarlo3@mail.com")
      }
    }

  }
}
