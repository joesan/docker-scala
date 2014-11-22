package com.q31.dockerscala.api.request

import com.q31.dockerscala.{ContainerId, UnPauseContainer, PauseContainer, DockerClientContext}
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class PauseUnPauseContainer(id: ContainerId, pauseUnPause: com.q31.dockerscala.PauseUnPauseContainer) {

  val pauseResourcePath   = s"/containers/${id}/pause"
  val unPauseResourcePath = s"/containers/${id}/unpause"

  def execute(dockerClientContext: DockerClientContext): String = {
    val webResource = dockerClientContext.getBaseResource
    val pauseUnPause = pauseUnPause match {
        case PauseContainer   => pauseResourcePath
        case UnPauseContainer => unPauseResourcePath
      }
    webResource.path(pauseUnPause)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[String])
  }

}
object PauseUnPauseContainer extends ((DockerClientContext, com.q31.dockerscala.PauseUnPauseContainer, ContainerId) => String) {

  def apply(clientContext: DockerClientContext, pauseUnPause: com.q31.dockerscala.PauseUnPauseContainer, id: ContainerId): String =
    new PauseUnPauseContainer(id, pauseUnPause).execute(clientContext)
}
