package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.{Response, MediaType}
import javax.ws.rs.client.Entity._
import com.q31.dockerscala.api.request.params.RequestParam.BuildImageReqParam
import java.io.InputStream
import com.q31.dockerscala.domain.AuthConfig

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
// TODO... Check correctness!
class BuildImage(params: BuildImageReqParam, authConfig: AuthConfig) {

  val resourcePath = "/build"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("t", params.t)
      .queryParam("q", params.q)
      .queryParam("t", params.t)
      .request()
      .header("X-Registry-Auth", authConfig.username)
      .accept("application/tar")
      .post(entity(classOf[Response], MediaType.APPLICATION_JSON), classOf[InputStream])
  }
}
object BuildImage extends ((DockerClientContext, BuildImageReqParam, AuthConfig) => String) {

  def apply(clientContext: DockerClientContext, params: BuildImageReqParam, authConfig: AuthConfig): InputStream =
    new BuildImage(params, authConfig).execute(clientContext)
}
