package com.q31.dockerscala.api.request

import com.q31.dockerscala.{ContainerId, DockerClientContext}
import javax.ws.rs.core.{GenericType, MediaType}
import com.q31.dockerscala.domain.ContainerChangeLog

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ContainerDiff(id: ContainerId) {

  val resourcePath = s"/containers/$id/changes"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("id", id)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .get(new GenericType(classOf[List[ContainerChangeLog]]))
  }

}
object ContainerDiff extends ((DockerClientContext, ContainerId) => List[ContainerChangeLog]) {

  def apply(clientContext: DockerClientContext, id: ContainerId): List[ContainerChangeLog] =
    new ContainerDiff(id).execute(clientContext)
}
