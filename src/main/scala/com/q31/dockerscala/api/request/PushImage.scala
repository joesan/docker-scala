package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.{Response, MediaType}
import javax.ws.rs.client.Entity._
import java.io.InputStream
import com.q31.dockerscala.domain.AuthConfig

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class PushImage(authConfig: AuthConfig) {

  val resourcePath = "/images/"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .header("X-Registry-Auth", authConfig.username)
      .accept(MediaType.APPLICATION_JSON)
    .post(entity(classOf[Response], MediaType.APPLICATION_JSON), classOf[InputStream])
  }
}
object PushImage extends ((DockerClientContext, AuthConfig) => InputStream) {

  def apply(clientContext: DockerClientContext, authConfig: AuthConfig): InputStream =
    new PushImage(authConfig).execute(clientContext)
}
