//package org.proyectofincarrera.dao
//
//import org.proyectofincarrera.model.UserPassword
//import org.proyectofincarrera.utils.DatabaseConfig._
//
//import scala.concurrent.{Await, Future }
//import scala.concurrent.duration.Duration
//
//
//class PasswordDaoSlickSpec extends SpecSupport with PasswordDaoSlick {
//
//  "get" should {
//    "return the password passing the id" in this {
//      val expected = UserPassword(2, Some("WHATEVER2"), "")
//
//      val actual: Option[UserPassword] = Await.result(db.run(get(2)), Duration.Inf)
//
//      actual.get.id must equalTo(expected.id)
//      actual.get.hashedPassword must equalTo(expected.hashedPassword)
//    }
//    "return None when password not found" in this {
//      val actual: Option[UserPassword] = Await.result(db.run(get(288)), Duration.Inf)
//      actual must beNone
//    }
//  }
//
//  "add" should {
//    "add a new password to the DDBB" in this {
//      val newPassword = UserPassword(0, Some("hashedpassword4"))
//      val expected = UserPassword(4, Some("hashedpassword4"))
//
//      val actual = add(newPassword)
//
//      actual must equalTo(expected.id)
//    }
//  }
//
//
//}
