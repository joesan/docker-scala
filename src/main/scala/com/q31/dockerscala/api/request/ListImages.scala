package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.domain.Image

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
private class ListImages(all: Boolean = false, filter: String) {

  val resourcePath = "/images/json"

  def execute(dockerClientContext: DockerClientContext): List[Image] = ???

}
object ListImages extends ((DockerClientContext, Boolean, String) => List[Image]) {

  def apply(clientContext: DockerClientContext, all: Boolean, filter: String): List[Image] = ???
}
