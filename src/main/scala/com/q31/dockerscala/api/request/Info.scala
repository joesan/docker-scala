package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.DockerAPIRequest
import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.domain.SystemInfo
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Info extends DockerAPIRequest {

  def resourcePath: String = "/info"

  def execute(dockerClientContext: DockerClientContext): SystemInfo = {
    dockerClientContext.getBaseResource.path(resourcePath).request().accept(MediaType.APPLICATION_JSON).get(classOf[SystemInfo])
  }
}
