package com.q31.dockerscala.api.request

import com.q31.dockerscala.{ContainerId, DockerClientContext}
import java.io.InputStream
import javax.ws.rs.core.{Response, MediaType}
import javax.ws.rs.client.Entity.entity
import com.q31.dockerscala.api.request.params.RequestParam.AttachToContainerReqParams

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class AttachToContainer(id: ContainerId, params: AttachToContainerReqParams) {

  val resourcePath = s"/containers/$id/attach"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("logs", params.logs)
      .queryParam("stdin", params.stdin)
      .queryParam("stdout", params.stdout)
      .queryParam("stderr", params.stderr)
      .queryParam("stream", params.stream)
      .request()
      .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE)
      .post(entity(null, MediaType.APPLICATION_JSON), classOf[Response]).readEntity(classOf[InputStream])
  }
}
object AttachToContainer extends ((DockerClientContext, ContainerId, AttachToContainerReqParams) => InputStream) {

  def apply(clientContext: DockerClientContext, id: ContainerId, params: AttachToContainerReqParams): InputStream =
    new AttachToContainer(id, params).execute(clientContext)
}
