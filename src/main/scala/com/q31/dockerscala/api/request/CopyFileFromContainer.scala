package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import java.io.InputStream
import javax.ws.rs.core.{Response, MediaType}
import javax.ws.rs.client.Entity.entity

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class CopyFileFromContainer(id: ContainerId, resource: String) {

  val resourcePath = s"/containers/${id}/copy"

  def execute(dockerClientContext: DockerClientContext): InputStream = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE)
      .post(entity(resource, MediaType.APPLICATION_JSON), classOf[Response])
    .readEntity(classOf[InputStream])
  }
}
object CopyFileFromContainer extends ((DockerClientContext, ContainerId, String) => InputStream) {

  def apply(clientContext: DockerClientContext, id: ContainerId, resource: String): InputStream =
    new CopyFileFromContainer(id, resource).execute(clientContext)
}
