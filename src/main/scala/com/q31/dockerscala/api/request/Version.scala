package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.domain.DockerVersion

import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Version {

  val resourcePath: String = "/version"

  def execute(dockerClientContext: DockerClientContext): DockerVersion = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[DockerVersion])
  }
}
object Version extends (DockerClientContext => DockerVersion) {

  def apply(clientContext: DockerClientContext): DockerVersion = new Version().execute(clientContext)
}

