package com.q31.dockerscala.api.request

import javax.ws.rs.core.MediaType

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.domain.SystemInfo

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Info {

  val resourcePath: String = "/info"

  def execute(dockerClientContext: DockerClientContext): SystemInfo = {
    dockerClientContext.getBaseResource.path(resourcePath).request().accept(MediaType.APPLICATION_JSON).get(classOf[SystemInfo])
  }
}
object Info extends (DockerClientContext => SystemInfo) {

  override def apply(clientContext: DockerClientContext): SystemInfo = new Info().execute(clientContext)
}
