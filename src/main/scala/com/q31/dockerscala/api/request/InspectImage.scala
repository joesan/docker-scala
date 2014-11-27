package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ImageName}
import com.q31.dockerscala.domain.Image

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class InspectImage(name: ImageName) {

  val resourcePath = s"/images/$name/json"

  def execute(dockerClientContext: DockerClientContext) = ???

}
object InspectImage extends ((DockerClientContext, ImageName) => Image) {

  def apply(clientContext: DockerClientContext, name: ImageName): Image = ???
}
