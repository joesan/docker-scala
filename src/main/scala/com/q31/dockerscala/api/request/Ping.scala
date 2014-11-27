package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Ping {

  val resourcePath = ""

  def execute(dockerClientContext: DockerClientContext) = ???

}
object Ping extends (DockerClientContext => Unit) {

  def apply(clientContext: DockerClientContext): Unit = ???
}
