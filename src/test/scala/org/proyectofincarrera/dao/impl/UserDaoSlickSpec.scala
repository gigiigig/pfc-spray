package org.proyectofincarrera.dao.impl

import org.scalatest._

import scala.slick.driver.MySQLDriver.simple._
import scala.slick.jdbc.meta._
import scala.slick.lifted.TableQuery

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends FunSuite with BeforeAndAfter with UserDaoSlick {

  val users = TableQuery[Users]

  implicit var session: Session = _

  def setupDatabase() = {
    users.ddl.create
  }

  before {
    session = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver").createSession()
  }

  test("Configuration should return correct configuration") {

    setupDatabase()
    val tables = MTable.getTables.list
    val size: Int = tables.size
    assert(size === 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
    //count must beEqualTo("users")

  }

  after {
    users.ddl.drop
    session.close()
  }
}
