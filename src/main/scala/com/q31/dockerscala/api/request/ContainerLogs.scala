package com.q31.dockerscala.api.request

import com.q31.dockerscala.{ContainerId, DockerClientContext}
import java.io.InputStream
import com.q31.dockerscala.api.request.params.RequestParam.ContainerLogReqParam
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ContainerLogs(id: ContainerId, params: ContainerLogReqParam) {

  val resourcePath = s"/containers/${id}/logs"

  def execute(dockerClientContext: DockerClientContext): InputStream = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("timestamps", params.timestamps)
      .queryParam("stdout", params.stdout)
      .queryParam("stderr", params.stderr)
      .queryParam("follow", params.follow)
      .queryParam("tail", params.tail)
      .request()
      .accept(MediaType.APPLICATION_JSON)
    .get(classOf[InputStream])
  }
}
object ContainerLogs extends ((DockerClientContext, ContainerId, ContainerLogReqParam) => InputStream) {

  def apply(clientContext: DockerClientContext, id: ContainerId, params: ContainerLogReqParam): InputStream =
    new ContainerLogs(id, params).execute(clientContext)
}