package com.q31.dockerscala.api.request

import com.q31.dockerscala._
import javax.ws.rs.core.{Response, MediaType}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class RemoveImage(name: ImageName, force: Boolean, noPrune: Boolean) {

  val resourcePath = s"/images/$name"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("force", force)
      .queryParam("noprune", noPrune)
      .request()
      .accept(MediaType.APPLICATION_JSON)
    .delete(classOf[Response])
  }
}
object RemoveImage extends ((DockerClientContext, ImageName, Boolean, Boolean) => String) {

  def apply(clientContext: DockerClientContext, imageName: ImageName, force: Boolean = false, noPrune: Boolean = false): Unit =
    new RemoveImage(imageName, force, noPrune).execute(clientContext)
}
