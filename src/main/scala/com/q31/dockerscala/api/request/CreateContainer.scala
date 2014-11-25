package com.q31.dockerscala.api.request

import javax.ws.rs.client.Entity.entity
import javax.ws.rs.core.MediaType

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.api.response.CreateContainerResponse

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class CreateContainer {

  val resourcePath = "/containers/create"

  def execute(dockerClientContext: DockerClientContext, params: CreateContainerParams, name: Option[String]): CreateContainerResponse = {
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
object CreateContainer extends ((DockerClientContext, CreateContainerParams, Option[String]) => CreateContainerResponse) {

  override def apply(context: DockerClientContext, params: CreateContainerParams, name: Option[String]): CreateContainerResponse = new CreateContainer().execute(context, params, name)
}
case class CreateContainerParams()
