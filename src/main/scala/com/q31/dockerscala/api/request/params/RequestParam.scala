package com.q31.dockerscala.api.request.params

import com.q31.dockerscala.ImageName
import com.q31.dockerscala.domain.{Bind, Link}
import com.q31.dockerscala.api.domain.Port

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object RequestParam {

  case class ContainerLogReqParam(follow: Boolean = false, stdout: Boolean = false, stderr: Boolean = false,
                                  timestamps: Boolean = false, tail: String = "all")

  case class TagImageReqParam(imageName: ImageName, repo: String, tag: String, force: Boolean = false)

  case class AuthReqParam(username: String, password: String, email: String, serverAddress: String)

  case class StartContainerParams(binds: List[Bind], links: List[Link], ports: Port)
}
