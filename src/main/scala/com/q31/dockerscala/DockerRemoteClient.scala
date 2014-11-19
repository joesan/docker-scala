package com.q31.dockerscala

import com.q31.dockerscala.api.domain.Container
import com.q31.dockerscala.api.request._
import com.q31.dockerscala.domain.SystemInfo

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
// TODO... Add scala doc!
trait DockerRemoteClient {
  def listContainers(params: ListContainersParam): List[Container]
  def info: SystemInfo
  def createContainer(params: CreateContainerParams, name: Option[String] = None)
}
// TODO... Make this class private, any instantiation should happen via defined methods!
class DockerRemoteClientImpl(val context: DockerClientContext) extends DockerRemoteClient {

  override def listContainers(params: ListContainersParam): List[Container] = ListContainers(context, params)

  override def info: SystemInfo = Info(context)

  override def createContainer(params: CreateContainerParams, name: Option[String]): Unit = CreateContainer(context, params, name)
}
