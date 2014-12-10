package org.proyectofincarrera.dao.impl

import org.proyectofincarrera.model.User
import org.specs2.mutable.Specification
import org.specs2.specification.BeforeAfterEach

import scala.slick.jdbc.meta.MTable

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends Specification with UserDaoSlick with DriverSupportSpec with BeforeAfterEach{

  import driver.simple._

  implicit var session: Session = _

  def createSchema()={
    users.ddl.create
    users ++= Seq(
      User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
      User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
      User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3"))
    )
  }

  override def before()={
    session = Database.forURL("jdbc:h2:mem:test1;MODE=MySQL;", driver = "org.h2.Driver").createSession()
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
        tables.head.name.name must beEqualTo("users")
      }


  }

  "getAll" should {
    "return the list of all users" in this {
        createSchema()
        val expected = Seq(
          User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
          User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
          User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3"))
        )
        getAll must beEqualTo(Some(expected))
      }

  }

  "get" should {
    "return the user passing the id" in this {
      createSchema()
      val expected = Some(User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")))

      get(2) must beEqualTo(expected)

    }
    "return None when user not found" in this {
      createSchema()
      get(288) must beNone
    }
  }

  "add" should {
    "add a new user to the DDBB" in this {
      createSchema()
      val newUser = User(4, "user4@mail.com", Some("name4"), Some("surname4"), Some("surname4"))
      val expected = Seq(
        User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
        User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2")),
        User(3, "user3@mail.com", Some("name3"), Some("surname3"), Some("surname3")),
        User(4, "user4@mail.com", Some("name4"), Some("surname4"), Some("surname4"))
      )
      add(newUser)

      getAll must beEqualTo(Some(expected))

    }
  }

  "delete" should {
    "remove the user" in this {
      createSchema()

      val expected = Seq(
        User(1, "user1@mail.com", Some("name1"), Some("surname1"), Some("surname1")),
        User(2, "user2@mail.com", Some("name2"), Some("surname2"), Some("surname2"))
      )

      delete(3)

      getAll must beEqualTo(Some(expected))

    }
  }

}
