package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.User
import org.specs2.mutable.Specification

import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends Specification with UserDaoSlick with DatabaseSupportSpec{

  def before() = {
    create

    users ++= Seq(
      User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
      User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
      User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3"))
    )
  }
  step(before())
  "Configuration" should {
    "return correct configuration" in {
      val tables = MTable.getTables.list
      val size: Int = tables.size
      assert(size === 1)
      assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
      tables.head.name.name must beEqualTo("USERS")

    }
  }

  "getAll" should {
    "gimme the list of all users" in {
      val expected = Seq(
        User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
        User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
        User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3"))
      )
      getAll must beEqualTo(expected)
    }
  }
  step(after())

  def after(){
    users.ddl.drop
    session.close()
  }

}
