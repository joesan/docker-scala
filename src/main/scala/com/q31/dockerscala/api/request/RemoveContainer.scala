package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ContainerId}
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class RemoveContainer(id: ContainerId, removeVolumes: Boolean) {

  val resourcePath = s"/containers/${id}"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[String])
  }

}
object RemoveContainer extends ((DockerClientContext, ContainerId, Boolean) => String) {

  def apply(clientContext: DockerClientContext, id: ContainerId, removeVolumes: Boolean): String =
    new RemoveContainer(id, removeVolumes).execute(clientContext)
}
