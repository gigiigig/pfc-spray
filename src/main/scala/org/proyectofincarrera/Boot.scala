package org.proyectofincarrera

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import org.proyectofincarrera.dao.impl.UserDaoSlick
import org.proyectofincarrera.service.impl.UserServiceImpl
import spray.can.Http

import scala.concurrent.duration._
import scala.slick.driver.MySQLDriver
import scala.slick.jdbc.JdbcBackend.Database

/**
 * Created by Gneotux on 15/11/2014.
 */
object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("proyectoFinCarreraSpray")

  object userService extends UserServiceImpl with UserDaoSlick{
    val driver = MySQLDriver
    val database = Database.forURL("jdbc:mysql://localhost:3306/test", driver = "com.mysql.jdbc.Driver")
  }
  // create and start our service actor
  val service = system.actorOf(Props(classOf[UserRouterActor],userService), "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)

}
