package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.{GenericType, MediaType}
import com.q31.dockerscala.api.response.SearchImageResponse

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class SearchImages(searchTerm: String) {

  val resourcePath = "/images/search"

  def execute(dockerClientContext: DockerClientContext) = {
    dockerClientContext.getBaseResource
      .path(resourcePath)
      .queryParam("term", searchTerm)
      .request()
      .accept(MediaType.APPLICATION_JSON)
    .get(new GenericType(classOf[List[SearchImageResponse]]))
  }
}
object SearchImages extends ((DockerClientContext, String) => List[SearchImageResponse]) {

  def apply(clientContext: DockerClientContext, searchTerm: String): List[SearchImageResponse] =
    new SearchImages(searchTerm).execute(clientContext)
}
