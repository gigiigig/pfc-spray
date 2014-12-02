package org.proyectofincarrera.dao.impl

import scala.slick.driver.JdbcProfile
import scala.slick.jdbc.JdbcBackend._

/**
 * Created by Gneotux on 01/12/2014.
 */
trait DatabaseSupport {
  val driver: JdbcProfile
  val database: Database
}
