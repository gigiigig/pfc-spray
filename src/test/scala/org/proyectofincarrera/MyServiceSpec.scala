package org.proyectofincarrera

import org.proyectofincarrera.model.User
import org.specs2.mutable.Specification
import spray.http.StatusCodes._
import spray.httpx.SprayJsonSupport._
import spray.testkit.Specs2RouteTest

class MyServiceSpec extends Specification with Specs2RouteTest with MyService {
  def actorRefFactory = system
  
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

    "return a user for GET requests to users path" in {
      Get("/users/") ~> myRoute ~> check {
        responseAs[User] === User(1, "test@mail.com", Option("Giancarlo"), Option("Muñoz"), Option("Reinoso"))
      }
    }
  }
}
