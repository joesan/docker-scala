package com.q31.dockerscala.util

import javax.ws.rs.client.{ClientResponseContext, ClientRequestContext, ClientResponseFilter}
import com.q31.dockerscala.api.DockerClientException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class DockerClientResponseFilter extends ClientResponseFilter {

  val ERROR_400_MESSAGE = "Bad Request"
  val ERROR_401_MESSAGE = "Unauthorized"
  val ERROR_404_MESSAGE = "Requested Resource Not Found"
  val ERROR_406_MESSAGE = "Request Not Acceptable"
  val ERROR_500_MESSAGE = "Internal Server Error"
  val ERROR_999_MESSAGE = "Unknown Error"

  val mapper = new ObjectMapper()

  // TODO get the exception message from responseContext
  def filter(requestContext: ClientRequestContext, responseContext: ClientResponseContext) = responseContext.getStatus() match {
    case 400 => throw new DockerClientException(400, getMessageFromResponseContext(responseContext).getOrElse(ERROR_400_MESSAGE))
    case 401 => throw new DockerClientException(401, getMessageFromResponseContext(responseContext).getOrElse(ERROR_401_MESSAGE))
    case 404 => throw new DockerClientException(404, getMessageFromResponseContext(responseContext).getOrElse(ERROR_404_MESSAGE))
    case 406 => throw new DockerClientException(406, getMessageFromResponseContext(responseContext).getOrElse(ERROR_406_MESSAGE))
    case 500 => throw new DockerClientException(500, getMessageFromResponseContext(responseContext).getOrElse(ERROR_500_MESSAGE))
    case _   => throw new DockerClientException(999, getMessageFromResponseContext(responseContext).getOrElse(ERROR_999_MESSAGE))
  }

  private def getMessageFromResponseContext(responseContext: ClientResponseContext) = {
    if(responseContext.hasEntity) Some(mapper.readValue(responseContext.getEntityStream(), classOf[ErrorResponse]).message)
    else None
  }
}
case class ErrorResponse(@JsonProperty("Message") message: String)
