package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ContainerId}
import com.q31.dockerscala.domain.Top
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class TopProcesses(id: ContainerId, ps_args: String) {
  val resourcePath: String = s"/containers/${id}/top"

  def execute(dockerClientContext: DockerClientContext): Top = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("ps_args", ps_args)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[Top])
  }
}
object TopProcesses extends ((DockerClientContext, ContainerId, String) => Top) {

  def apply(clientContext: DockerClientContext, id: ContainerId, ps_args: String): Top = {
    new TopProcesses(id, ps_args).execute(clientContext)
  }
}
