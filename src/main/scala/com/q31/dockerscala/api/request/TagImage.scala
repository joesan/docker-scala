package com.q31.dockerscala.api.request

import com.q31.dockerscala.{DockerClientContext, ImageName}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class TagImage(imageName: ImageName) {

  val resourcePath = s"/images/${imageName}/tag"

  def execute(dockerClientContext: DockerClientContext): String = ???
}
object TagImage extends ((DockerClientContext, ImageName) => String) {

  def apply(clientContext: DockerClientContext, imageName: ImageName): String = new TagImage(imageName).execute(clientContext)
}
