package com.q31.dockerscala

import com.q31.dockerscala.api.request.ListContainers

trait DockerClient {

  def listContainers: ListContainers

}
