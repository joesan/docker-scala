package com.q31.dockerscala.api.request

import javax.ws.rs.client.Entity.entity
import javax.ws.rs.core.MediaType

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.api.response.CreateContainerResponse
import com.q31.dockerscala.api.request.params.RequestParam.CreateContainerReqParam

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class CreateContainer(params: CreateContainerReqParam, name: Option[String] = None) {

  val resourcePath = "/containers/create"

  def execute(dockerClientContext: DockerClientContext): CreateContainerResponse = {
    val webResource = dockerClientContext.getBaseResource.path(resourcePath)
    name match {
      case Some(value) => webResource.queryParam("name", value)
      case None        => println("no name specified for container")
    }
    webResource.request()
      .accept(MediaType.APPLICATION_JSON)
      .post(entity(params, MediaType.APPLICATION_JSON), classOf[CreateContainerResponse])
  }
}
object CreateContainer extends ((DockerClientContext, CreateContainerReqParam, Option[String]) => CreateContainerResponse) {

  override def apply(clientContext: DockerClientContext, params: CreateContainerReqParam, name: Option[String]): CreateContainerResponse =
    new CreateContainer(params, name).execute(clientContext)
}