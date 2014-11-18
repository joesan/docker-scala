package com.q31.dockerscala

import com.q31.dockerscala.api.request.ListContainers

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
trait DockerClient {

  def listContainers: ListContainers

}
