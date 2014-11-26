package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ResizeContainer(id: ContainerId, height: Int, width: Int) {

  val resourcePath = s"/containers/$id/resize?h=$height&w=$width"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[Unit])
  }
}
object ResizeContainer extends ((DockerClientContext, ContainerId, Int, Int) => Unit) {

  def apply(clientContext: DockerClientContext, id: ContainerId, height: Int, width: Int): Unit =
    new ResizeContainer(id, height, width).execute(clientContext)
}
