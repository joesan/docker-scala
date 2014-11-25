package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity._
import com.q31.dockerscala.api.request.params.RequestParam.TagImageReqParam

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class TagImage(params: TagImageReqParam) {

  val resourcePath = s"/images/${params.imageName}/tag"

  def execute(dockerClientContext: DockerClientContext): Unit = {
    dockerClientContext.getBaseResource.path(resourcePath)
      .queryParam("repo", params.repo)
      .queryParam("tag", params.tag)
      .queryParam("force", params.force)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .post(entity(params, MediaType.APPLICATION_JSON), classOf[Unit])
  }
}
object TagImage extends ((DockerClientContext, TagImageReqParam) => Unit) {

  def apply(clientContext: DockerClientContext, params: TagImageReqParam): Unit = new TagImage(params).execute(clientContext)
}
