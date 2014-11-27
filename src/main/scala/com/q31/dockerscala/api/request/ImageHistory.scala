package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ImageName}
import com.q31.dockerscala.domain.Image

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ImageHistory(name: ImageName) {

  val resourcePath = s"/images/$name/history"

  def execute(dockerClientContext: DockerClientContext) = ???

}
object ImageHistory extends ((DockerClientContext, ImageName) => Image) {

  def apply(clientContext: DockerClientContext, name: ImageName): Image = ???
}
