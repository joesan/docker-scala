package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.DockerAPIRequest
import com.q31.dockerscala.api.response.InspectContainerResponse
import com.q31.dockerscala.DockerClientContext

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class InspectContainer {

  val resourcePath = s"/containers/${id}/json"

  final def execute(dockerClientContext: DockerClientContext): InspectContainerResponse = ???
}
object InspectContainer extends ((DockerClientContext, String) => InspectContainerResponse) {

  override def apply(clientContext: DockerClientContext, id: String): InspectContainerResponse = new InspectContainer().execute(clientContext, id)
}
