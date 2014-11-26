package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.MediaType
import com.q31.dockerscala.api.response.InspectContainerResponse
import java.io.InputStream

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ExportContainer(id: ContainerId) {

  val resourcePath = s"/containers/$id/export"

  def execute(dockerClientContext: DockerClientContext): InputStream= {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("id", id)
      .request()
      .accept(MediaType.APPLICATION_OCTET_STREAM)
      .get(classOf[InputStream])
  }
}
object ExportContainer extends ((DockerClientContext, ContainerId) => InputStream) {

  def apply(clientContext: DockerClientContext, id: ContainerId): InputStream =
    new ExportContainer(id).execute(clientContext)
}
