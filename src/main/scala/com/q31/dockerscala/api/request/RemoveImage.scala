package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.{Response, MediaType}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class RemoveImage(name: ImageName) {

  val resourcePath = s"/images/$name"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .delete(classOf[String])
  }
}
object RemoveImage extends ((DockerClientContext, ImageName) => String) {

  def apply(clientContext: DockerClientContext, imageName: ImageName): String = new RemoveImage(imageName).execute(clientContext)
}
