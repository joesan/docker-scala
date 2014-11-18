package com.q31.dockerscala.api.request

import com.q31.dockerscala.api._
import com.q31.dockerscala.api.domain.Container
import com.q31.dockerscala.DockerClientContext

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ListContainers(params: ListContainersParam = ListContainersParam.apply()) extends DockerAPIRequest {

  override def resourcePath = "/containers/json"

  override def execute(dockerClientContext: DockerClientContext): List[Container] = {

  }
}
case class ListContainersParam(all: Int = 1, before: String = "", size: Int = 1)