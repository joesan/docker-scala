package com.q31.dockerscala.util

import javax.ws.rs.client.{ClientResponseContext, ClientRequestContext, ClientResponseFilter}
import javax.ws.rs.core.MediaType

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class JsonClientFilter extends ClientResponseFilter {

  override def filter(requestContext: ClientRequestContext, responseContext: ClientResponseContext) = {
    if (responseContext.getMediaType() != null && responseContext.getMediaType().isCompatible(MediaType.TEXT_PLAIN_TYPE)) {
      val newContentType = "application/json" + responseContext.getMediaType().toString().substring(10)
      responseContext.getHeaders().putSingle("Content-Type", newContentType)
    }
  }
}
