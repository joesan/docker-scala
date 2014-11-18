package com.q31.dockerscala

import javax.ws.rs.client.WebTarget

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class DockerClientContext(baseResource: WebTarget) {

  def getBaseResource = baseResource
}
