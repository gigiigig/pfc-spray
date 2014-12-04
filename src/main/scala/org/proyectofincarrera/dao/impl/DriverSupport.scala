package org.proyectofincarrera.dao.impl

import scala.slick.driver.JdbcProfile

/**
 * Created by gmunoz on 03/12/14.
 */
trait DriverSupport {
  val driver: JdbcProfile
}
