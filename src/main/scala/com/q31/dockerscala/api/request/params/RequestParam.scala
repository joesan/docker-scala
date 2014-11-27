package com.q31.dockerscala.api.request.params

import com.q31.dockerscala.ImageName
import com.q31.dockerscala.domain.{Bind, Link}
import com.q31.dockerscala.api.domain.Port
import java.io.InputStream

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object RequestParam {

  case class CreateContainerReqParam()

  case class BuildImageReqParam(t: String, q: Boolean, nocache: Boolean, rm: String, forcerm: Boolean)

  case class ContainerLogReqParam(follow: Boolean = false, stdout: Boolean = false, stderr: Boolean = false,
                                  timestamps: Boolean = false, tail: String = "all")

  case class TagImageReqParam(imageName: ImageName, repo: String, tag: String, force: Boolean = false)

  case class AuthReqParam(username: String, password: String, email: String, serverAddress: String)

  case class StartContainerReqParam(binds: List[Bind], links: List[Link], ports: Port)

  case class AttachToContainerReqParam(logs: Boolean = false, stream: Boolean = false, stdin: Boolean = false,
                                     stdout: Boolean = false, stderr: Boolean = false)

  case class CreateImageReqParam(fromImage: String, fromSrc: String, repo: String, tag: String, imageStream: InputStream)
}
