package org.proyectofincarrera

import akka.actor.Actor
import com.gettyimages.spray.swagger.SwaggerHttpService
import com.wordnik.swagger.model.ApiInfo
import org.proyectofincarrera.service.UserService

import scala.reflect.runtime.universe._


/**
 * Created by Gneotux on 15/11/2014.
 */
// we don't implement our route structure directly in the service actor because
// we want to be able to test it independently, without having to spin up an actor
class RouterActor(service: UserService) extends Actor with UserRouter {

  override val userService = service

  val swaggerService = new SwaggerHttpService {
    override def apiTypes = Seq(typeOf[UserRouter])
    override def apiVersion = "2.0"
    override def baseUrl = "/" // let swagger-ui determine the host and port
    override def docsPath = "api-docs"
    override def actorRefFactory = context
    override def apiInfo = Some(new ApiInfo("Api pfc", "", "", "", "", ""))

    //authorizations, not used
  }

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(userOperations ~ swaggerService.routes ~
    get {
      pathPrefix("") { pathEndOrSingleSlash {
        getFromResource("swagger-ui/index.html")
      }
      } ~
        getFromResourceDirectory("swagger-ui")
    })
}