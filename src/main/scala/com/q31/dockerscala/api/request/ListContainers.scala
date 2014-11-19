package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import com.q31.dockerscala.api.domain.Container

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ListContainers {

  val resourcePath = "/containers/json"

  def execute(dockerClientContext: DockerClientContext, params: ListContainersParam): List[Container] = ???
}
object ListContainers extends ((DockerClientContext, ListContainersParam) => List[Container])  {

  def apply(dockerClientContext: DockerClientContext, params: ListContainersParam): List[Container] = new ListContainers().execute(dockerClientContext, params)

}
case class ListContainersParam(all: Int = 1, before: String = "", size: Int = 1)