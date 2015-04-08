package org.proyectofincarrera.utils

import com.typesafe.config.ConfigFactory


/**
 * Created by gneotux on 01/03/15.
 */
object Config {
  private val config =  ConfigFactory.load()

  object app {
    private val appConf = config.getConfig("app")
    lazy val systemName = appConf.getString("systemName")
    lazy val interface = appConf.getString("interface")
    lazy val port = appConf.getInt("port")
    lazy val userServiceName = appConf.getString("userServiceName")

  }


  object dbConfig {
    private val dbConfig = config.getConfig("db")
    lazy val url = dbConfig.getString("url")
    lazy val user = dbConfig.getString("user")
    lazy val password = dbConfig.getString("password")
    lazy val driver = dbConfig.getString("driver")
  }
}
