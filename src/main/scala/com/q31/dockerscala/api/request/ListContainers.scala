package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.api.domain.Container
import com.q31.dockerscala.api.request.params.RequestParam.ListContainersReqParam
import javax.ws.rs.core.{GenericType, MediaType}
import java.io.InputStream

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ListContainers(params: ListContainersReqParam) {

  val resourcePath = "/containers/json"

  def execute(dockerClientContext: DockerClientContext): List[Container] = {
    val webResource = dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("all", params.all)
      .queryParam("limit", params.limit)
       if(params.since.isDefined) webResource.queryParam("since", params.since)
       if(params.before.isDefined) webResource.queryParam("before", params.before)
       webResource.queryParam("size", params.size)
       .request()
      .accept(MediaType.APPLICATION_JSON)
    .get(new GenericType(classOf[List[Container]]))
  }
}
object ListContainers extends ((DockerClientContext, ListContainersReqParam) => List[Container])  {

  def apply(dockerClientContext: DockerClientContext, params: ListContainersReqParam): List[Container] =
    new ListContainers(params).execute(dockerClientContext)
}