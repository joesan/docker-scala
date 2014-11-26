package com.q31.dockerscala

import com.q31.dockerscala.api.domain.Container
import com.q31.dockerscala.api.request._
import com.q31.dockerscala.domain.{ContainerChangeLog, Top, DockerVersion, SystemInfo}
import com.q31.dockerscala.api.request.params.RequestParam.{StartContainerParams, ContainerLogReqParam}
import java.io.InputStream
import com.q31.dockerscala.api.response.InspectContainerResponse

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
// TODO... Add scala doc!
trait DockerRemoteClient {
  /* Container API's */
  def listContainers(params: ListContainersParam): List[Container]
  def createContainer(params: CreateContainerParams, name: Option[String] = None)
  def inspectContainer(id: ContainerId): InspectContainerResponse
  def runningProcesses(id: ContainerId, ps_args: String): Top
  def containerLogs(id: ContainerId, params: ContainerLogReqParam): InputStream
  def containerDiff(id: ContainerId): List[ContainerChangeLog]
  def exportContainer(id: ContainerId): InputStream
  def resizeContainer(id: ContainerId, height: Int, width: Int)
  def startContainer(params: StartContainerParams): Unit
  def stopContainer(id: ContainerId, timeout: TimeOut): String
  def restartContainer(id: ContainerId, timeout: TimeOut): String
  def killContainer(id: ContainerId, signal: Option[String]): Unit
  def pauseContainer(id: ContainerId): String
  def unPauseContainer(id: ContainerId): String
  def attachToContainer
  def waitAContainer
  def removeContainer(id: ContainerId, removeVolumes: Boolean = false)
  def copyContainerFiles

  /* Image API's */
  def listImages
  def createImage
  def inspectImage

  /* Misc API's */
  def buildImage
  def info: SystemInfo
  def ping
  def commit
  def events
  def pullImagesForRepo(name: String)
  def allImages
  def loadImages
  def execCreate
  def execStart
  def execResize

  def version: DockerVersion

}
// TODO... Make this class private, any instantiation should happen via factory methods!
class DockerRemoteClientImpl(val context: DockerClientContext) extends DockerRemoteClient {

  override def listContainers(params: ListContainersParam): List[Container] = ListContainers(context, params)

  override def createContainer(params: CreateContainerParams, name: Option[String]): Unit = CreateContainer(context, params, name)

  override def inspectContainer(id: ContainerId): InspectContainerResponse = InspectContainer(context, id)

  override def runningProcesses(id: ContainerId, ps_args: String) = TopProcesses(context, id, ps_args)

  override def containerLogs(id: ContainerId, params: ContainerLogReqParam): InputStream = ContainerLogs(context, id, params)

  override def containerDiff(id: ContainerId): List[ContainerChangeLog] = ContainerDiff(context, id)

  override def exportContainer(id: ContainerId): InputStream = ExportContainer(context, id)

  override def resizeContainer(id: ContainerId, height: Int, width: Int): Unit = ResizeContainer(context, id, height, width)

  override def startContainer(params: StartContainerParams): Unit = ???

  override def stopContainer(id: ContainerId, timeout: TimeOut): String = StopRestartContainer(context, StopContainer, id, timeout)

  override def restartContainer(id: ContainerId, timeout: TimeOut): String = StopRestartContainer(context, RestartContainer, id, timeout)

  override def killContainer(id: ContainerId, signal: Option[String]) = ???

  override def pauseContainer(id: ContainerId): String = PauseUnPauseContainer(context, PauseContainer, id)

  override def unPauseContainer(id: ContainerId): String = PauseUnPauseContainer(context, UnPauseContainer, id)

  override def attachToContainer: Unit = ???

  override def waitAContainer: Unit = ???

  override def removeContainer(id: ContainerId, removeVolumes: Boolean): Unit = RemoveContainer(context, id, removeVolumes)

  override def copyContainerFiles: Unit = ???

  // Images API

  override def listImages = ???

  override def createImage = ???

  override def inspectImage = ???

  // Misc API

  override def info: SystemInfo = Info(context)

  override def version: DockerVersion = Version(context)

  /* Misc API's */
  def buildImage: Unit = ???

  def ping: Unit = ???

  def commit: Unit = ???

  def events: Unit = ???

  def pullImagesForRepo(name: String): Unit = ???

  def allImages: Unit = ???

  def loadImages: Unit = ???

  def execCreate: Unit = ???

  def execStart: Unit = ???

  def execResize: Unit = ???
}