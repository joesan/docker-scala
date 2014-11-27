package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ImageName}
import com.q31.dockerscala.api.response.InspectImageResponse
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class InspectImage(name: ImageName) {

  val resourcePath = s"/images/$name/json"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .request().accept(MediaType.APPLICATION_JSON)
    .get(classOf[InspectImageResponse])
  }
}
object InspectImage extends ((DockerClientContext, ImageName) => InspectImageResponse) {

  def apply(clientContext: DockerClientContext, name: ImageName): InspectImageResponse =
    new InspectImage(name).execute(clientContext)
}
