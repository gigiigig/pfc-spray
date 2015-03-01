package org.proyectofincarrera

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import akka.pattern.ask
import akka.util.Timeout
import org.proyectofincarrera.dao.impl.{DatabaseSupport, DriverSupport, UserDaoSlick}
import org.proyectofincarrera.service.impl.UserServiceImpl
import org.proyectofincarrera.utils.Config._
import spray.can.Http

import scala.concurrent.duration._
import scala.slick.driver.PostgresDriver
import scala.slick.jdbc.JdbcBackend.Database

/**
 * Created by Gneotux on 15/11/2014.
 */
object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem(app.systemName)



  object userService extends UserServiceImpl  with DatabaseSupport with UserDaoSlick with DriverSupport{
    override val dao = this
    override val driver = PostgresDriver
    override val database = Database.forURL(
      url = dbConfig.url ,
      user = dbConfig.user,
      password = dbConfig.password,
      driver = dbConfig.driver
    )
  }

  // create and start our service actor
  val service = system.actorOf(Props(classOf[UserRouterActor],userService), app.userServiceName)

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = app.interface, port = app.port)

}
