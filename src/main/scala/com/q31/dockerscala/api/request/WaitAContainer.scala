package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity.entity
import com.fasterxml.jackson.databind.node.ObjectNode

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class WaitAContainer(id: ContainerId) {

  val resourcePath = s"/containers/$id/wait"

  def execute(dockerClientContext: DockerClientContext): Int = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .post(entity(null, MediaType.TEXT_PLAIN), classOf[ObjectNode])
    .get("StatusCode").asInt()
  }

}
object WaitAContainer extends ((DockerClientContext, ContainerId) => Int) {

  def apply(clientContext: DockerClientContext, id: ContainerId): Int =
    new WaitAContainer(id).execute(clientContext)
}
