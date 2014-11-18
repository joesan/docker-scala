package com.q31.dockerscala

import javax.ws.rs.client.WebTarget

/**
 *
 * User: Apache Software Foundation :: Apache Camel
 * Date: 11/18/14
 * Time: 8:45 PM
 * Year: 2014
 * Project: docker-scala
 */
class DockerClientContext(baseResource: WebTarget) {

  def getBaseResource = baseResource
}
