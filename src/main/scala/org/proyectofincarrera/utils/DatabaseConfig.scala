package org.proyectofincarrera.utils

import org.proyectofincarrera.utils.Config.dbConfig._

import scala.slick.driver.{H2Driver, JdbcProfile, PostgresDriver}
import scala.slick.jdbc.JdbcBackend._

/**
 * Created by gneotux on 08/04/15.
 */
object DatabaseConfig {
  lazy val db: Database = Database.forURL(url, user, password, driver = driver)
  lazy val profile: JdbcProfile = driver match {
    case "org.postgresql.Driver" => PostgresDriver
    case "org.h2.Driver" => H2Driver
  }
}
