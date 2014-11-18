package com.q31.dockerscala.api.request

import com.q31.dockerscala.api._
import com.q31.dockerscala.api.response.CreateContainerResponse
import com.q31.dockerscala.DockerClientContext

import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity.entity

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class CreateContainer(name: Option[String] = None, params: CreateContainerParams) extends DockerAPIRequest {

  override def resourcePath = "/containers/create"

  def execute(dockerClientContext: DockerClientContext): CreateContainerResponse = {
    val webResource = dockerClientContext.getBaseResource.path(resourcePath)
    name match {
      case Some(value) => webResource.queryParam("name", value)
      case None        => _
    }
    webResource.request().accept(MediaType.APPLICATION_JSON).post(entity(params, MediaType.APPLICATION_JSON), classOf[CreateContainerResponse])
  }
}
case class CreateContainerParams()
