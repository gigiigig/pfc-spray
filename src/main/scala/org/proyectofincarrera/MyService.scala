package org.proyectofincarrera

import org.proyectofincarrera.model.User
import spray.http.MediaTypes._
import spray.routing.HttpService
import spray.httpx.marshalling.Marshaller
import spray.httpx.SprayJsonSupport._
import spray.json.DefaultJsonProtocol._


/**
 * Created by Gneotux on 15/11/2014.
 */
// this trait defines our service behavior independently from the service actor
trait MyService extends HttpService {

  val myRoute =
    path("") {
      get {
        respondWithMediaType(`text/html`) { // XML is marshalled to `text/xml` by default, so we simply override here
          complete {
            <html>
              <body>
                <h1>Say hello to <i>spray-routing</i> on <i>spray-can</i>!</h1>
              </body>
            </html>
          }
        }
      }
    } ~
    path("users" /) {
      get {
        respondWithMediaType(`application/json`) {
          complete(User(1, "test@mail.com", Option("Giancarlo"), Option("Muñoz"), Option("Reinoso")))
        }
      }
    }

}
