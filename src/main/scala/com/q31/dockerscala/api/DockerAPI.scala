package com.q31.dockerscala.api

import com.q31.dockerscala.DockerClientContext

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
trait DockerAPI {

}
trait DockerAPIRequest[T] {
  def resourcePath: String
  def execute(dockerClientContext: DockerClientContext): T
}
case class DockerClientException(statusCode: Int, errorMesage: String) extends Exception
