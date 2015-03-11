package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.Password
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach

import scala.slick.jdbc.meta.MTable


class PasswordDaoSlickSpec extends Specification with PasswordDaoSlick with DriverSupportSpec with BeforeAfterEach{

  import driver.simple._

  implicit var session: Session = _

  def createSchema()={
    passwords.ddl.create
    passwords ++= Seq(
      Password(Some(1), 1, Some("hashedpassword2")),
      Password(Some(2), 2, Some("hashedpassword2"))
    )
  }

  override def before()={
    session = Database.forURL("jdbc:h2:mem:test1;MODE=MySQL;mv_store=false;", driver = "org.h2.Driver").createSession()
  }

  override def after()={
    session.close()
  }

  "Configuration" should {
    "return correct configuration" in this {
      createSchema()
      val tables = MTable.getTables.list
      val size: Int = tables.size
      assert(size === 1)
      tables.head.name.name must beEqualTo("passwords")
    }


  }


  "get" should {
    "return the password passing the id" in this {
      createSchema()
      val expected = Some(Password(Some(2), 2, Some("hashedpassword2")))

      get(2) must beEqualTo(expected)

    }
    "return None when password not found" in this {
      createSchema()
      get(288) must beNone
    }
  }

  "add" should {
    "add a new password to the DDBB" in this {
      createSchema()
      val newPassword = Password(None, 4, Some("hashedpassword4"))
      val expected = Password(Some(3), 4, Some("hashedpassword4"))

      val actual = add(newPassword)

      actual must beEqualTo(expected)

    }
  }


}
