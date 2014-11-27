package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.request.params.RequestParam.StartContainerReqParams
import com.q31.dockerscala.{ContainerId, DockerClientContext}
import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity.entity

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class StartContainer(id: ContainerId, params: StartContainerReqParams) {

  val resourcePath = s"/containers/${id}/start"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .post(entity(params, MediaType.APPLICATION_JSON))
  }

}
object StartContainer extends ((DockerClientContext, ContainerId, StartContainerReqParams) => Unit) {

  def apply(clientContext: DockerClientContext, id: ContainerId, params: StartContainerReqParams): Unit =
    new StartContainer(id, params).execute(clientContext)
}
