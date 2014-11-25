package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ContainerId}
import javax.ws.rs.core.{Response, MediaType}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class KillContainer(id: ContainerId, signal: Option[String] = "SIGKILL") {

  val resourcePath = s"/containers/${id}/kill"

  def execute(dockerClientContext: DockerClientContext): Unit = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("signal", signal.getOrElse("SIGKILL"))
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(classOf[Unit])
  }
}
object KillContainer extends ((DockerClientContext, ContainerId, Option[String]) => Unit) {

  def apply(clientContext: DockerClientContext, id: ContainerId, signal: Option[String]): Unit =
    new KillContainer(id, signal).execute(clientContext)
}
