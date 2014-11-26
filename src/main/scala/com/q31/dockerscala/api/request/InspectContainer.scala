package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.response.InspectContainerResponse
import com.q31.dockerscala.{ContainerId, DockerClientContext}
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class InspectContainer(id: ContainerId) {

  val resourcePath = s"/containers/${id}/json"

  def execute(dockerClientContext: DockerClientContext): InspectContainerResponse = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("id", id)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[InspectContainerResponse])
  }
}
object InspectContainer extends ((DockerClientContext, String) => InspectContainerResponse) {

  override def apply(clientContext: DockerClientContext, id: String): InspectContainerResponse =
    new InspectContainer(id).execute(clientContext)
}
