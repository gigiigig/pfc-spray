package org.proyectofincarrera.dao.impl

import scala.slick.driver.H2Driver
import scala.slick.driver.H2Driver.simple._
import scala.slick.jdbc.JdbcBackend.Database

/**
 * Created by Gneotux on 01/12/2014.
 */
trait DatabaseSupportSpec extends DatabaseSupport{

  override val driver = H2Driver

  override val database: Database = Database.forURL("jdbc:h2:mem:hello", driver = "org.h2.Driver")

  implicit val session: Session = database.createSession()

}
