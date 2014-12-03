package org.proyectofincarrera

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

    "return a greeting for GET requests to the root path" in {
      Get() ~> myRoute ~> check {
        responseAs[String] must contain("Say hello")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        responseAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }

    "return a list of users for GET requests to users path" in {
      userService.list() returns Some(List(User(1, "giancarlo@mail.com")))
      Get("/users/") ~> myRoute ~> check {
        responseAs[List[User]] === List(User(1, "giancarlo@mail.com"))
      }
    }
  }
}
