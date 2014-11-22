package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class StopRestartContainer(stopOrRestart: com.q31.dockerscala.StopRestartContainer, id: ContainerId, timeout: TimeOut) {

  val resourcePathStop    = s"/containers/${id}/stop"
  val resourcePathRestart = s"/containers/${id}/restart"

  def execute(dockerClientContext: DockerClientContext): String = {
    val webResource = dockerClientContext.getBaseResource
    val resourcePath = stopOrRestart match {
      case StopContainer    => resourcePathStop
      case RestartContainer => resourcePathRestart
    }
    webResource.path(resourcePath)
      .queryParam("t", timeout)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[String])
  }

}
object StopRestartContainer extends ((DockerClientContext, com.q31.dockerscala.StopRestartContainer, ContainerId, TimeOut) => String) {

  def apply(clientContext: DockerClientContext, stopOrRestart: com.q31.dockerscala.StopRestartContainer, id: ContainerId, timeout: TimeOut): String =
    new StopRestartContainer(stopOrRestart, id, timeout).execute(clientContext)
}
