package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.DockerAPIRequest
import com.q31.dockerscala.api.response.InspectContainerResponse
import com.q31.dockerscala.DockerClientContext

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class InspectContainer(id: String) extends DockerAPIRequest {

  override def resourcePath = s"/containers/${id}/json"

  override def execute(dockerClientContext: DockerClientContext): InspectContainerResponse = ???
}
