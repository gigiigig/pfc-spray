package org.proyectofincarrera

import akka.actor.{ActorSystem, Props}
import akka.io.IO
import org.proyectofincarrera.model.User
import org.proyectofincarrera.service.UserService
import org.proyectofincarrera.service.impl.UserServiceImpl
import spray.can.Http
import akka.pattern.ask
import akka.util.Timeout
import scala.concurrent.duration._

/**
 * Created by Gneotux on 15/11/2014.
 */
object Boot extends App {

  // we need an ActorSystem to host our application in
  implicit val system = ActorSystem("proyectoFinCarrera Spray")
  val userService = new UserServiceImpl

  // create and start our service actor
  val service = system.actorOf(Props(classOf[UserRouterActor],userService), "demo-service")

  implicit val timeout = Timeout(5.seconds)
  // start a new HTTP server on port 8080 with our service actor as the handler
  IO(Http) ? Http.Bind(service, interface = "localhost", port = 8080)
}
