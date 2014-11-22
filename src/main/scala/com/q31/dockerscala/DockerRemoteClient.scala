package com.q31.dockerscala

import com.q31.dockerscala.api.domain.Container
import com.q31.dockerscala.api.request._
import com.q31.dockerscala.domain.{DockerVersion, SystemInfo}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
// TODO... Add scala doc!
trait DockerRemoteClient {
  /* Container API's */
  def listContainers(params: ListContainersParam): List[Container]
  def createContainer(params: CreateContainerParams, name: Option[String] = None)
  def runningProcesses
  def containerLogs
  def exportContainer
  def resizeContainer
  def startContainer
  def stopContainer(id: ContainerId, timeout: TimeOut): String
  def restartContainer: String
  def killContainer
  def pauseContainer(id: ContainerId): String
  def unPauseContainer(id: ContainerId): String
  def attachToContainer
  def waitAContainer
  def removeContainer
  def copyContainerFiles

  /* Image API's */
  def listImages
  def createImage
  def inspectImage

  /* Misc API's */
  def info: SystemInfo
  def version: DockerVersion

}
// TODO... Make this class private, any instantiation should happen via defined methods!
class DockerRemoteClientImpl(val context: DockerClientContext) extends DockerRemoteClient {

  override def listContainers(params: ListContainersParam): List[Container] = ListContainers(context, params)

  override def createContainer(params: CreateContainerParams, name: Option[String]): Unit = CreateContainer(context, params, name)

  override def runningProcesses = ???

  override def killContainer = ???

  override def containerLogs: Unit = ???

  override def resizeContainer: Unit = ???

  override def pauseContainer(id: ContainerId): String = PauseUnPauseContainer(context, PauseContainer, id)

  override def unPauseContainer(id: ContainerId): String = PauseUnPauseContainer(context, UnPauseContainer, id)

  override def waitAContainer: Unit = ???

  override def startContainer: Unit = ???

  override def attachToContainer: Unit = ???

  override def stopContainer(id: ContainerId, timeout: TimeOut): String = StopRestartContainer(context, StopContainer, id, timeout)

  override def restartContainer(id: ContainerId, timeout: TimeOut): String = StopRestartContainer(context, RestartContainer, id, timeout)

  override def removeContainer: Unit = ???

  override def copyContainerFiles: Unit = ???

  override def exportContainer: Unit = ???

  override def listImages = ???

  override def createImage = ???

  override def inspectImage = ???

  override def info: SystemInfo = Info(context)

  override def version: DockerVersion = Version(context)
}