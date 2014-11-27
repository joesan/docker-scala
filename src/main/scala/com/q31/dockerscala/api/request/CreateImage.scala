package com.q31.dockerscala.api.request

import com.q31.dockerscala.api.request.params.RequestParam.CreateImageReqParam
import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity.entity

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
private class CreateImage(params: CreateImageReqParam) {

  val resourcePath = "/images/create"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("repo", params.fromImage)
      .queryParam("tag", params.tag)
      .queryParam("fromSrc", params.fromSrc)
      .request()
      .accept(MediaType.APPLICATION_OCTET_STREAM_TYPE)
    .post(entity(params.imageStream, MediaType.APPLICATION_OCTET_STREAM), classOf[String])
  }
}
object CreateImage extends ((DockerClientContext, CreateImageReqParam) => String){

  def apply(clientContext: DockerClientContext, params: CreateImageReqParam): String =
    new CreateImage(params).execute(clientContext)
}
