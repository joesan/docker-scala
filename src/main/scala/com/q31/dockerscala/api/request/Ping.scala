package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Ping {

  val resourcePath = "/_ping"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
    .get(classOf[String])
  }
}
object Ping extends (DockerClientContext => String) {

  def apply(clientContext: DockerClientContext): String = new Ping().execute(clientContext)
}
