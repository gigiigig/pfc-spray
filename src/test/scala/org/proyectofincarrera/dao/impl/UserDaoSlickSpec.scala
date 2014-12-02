package org.proyectofincarrera.dao.impl

import org.scalatest._

import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.meta.MTable

/**
 * Created by Gneotux on 18/11/2014.
 */
class UserDaoSlickSpec extends FunSuite with BeforeAndAfter with UserDaoSlick with DatabaseSupportSpec {

  def setupDatabase() = {
    users.ddl.create
  }

  test("Configuration should return correct configuration") {

    setupDatabase()
    val tables = MTable.getTables.list
    val size: Int = tables.size
    assert(size === 1)
    assert(tables.count(_.name.name.equalsIgnoreCase("users")) == 1)
//    count must beEqualTo("users")

  }

  after {
    session.close()
  }
}
