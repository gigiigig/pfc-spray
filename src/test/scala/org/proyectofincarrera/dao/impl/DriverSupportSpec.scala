package org.proyectofincarrera.dao.impl

import scala.slick.driver.H2Driver

/**
 * Created by gmunoz on 03/12/14.
 */
trait DriverSupportSpec extends DriverSupport {
  override val driver = H2Driver
}
