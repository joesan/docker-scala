package com.q31.dockerscala.api.request.params

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object RequestParam {

  case class ContainerLogReqParam(follow: Boolean = false, stdout: Boolean = false, stderr: Boolean = false,
                                  timestamps: Boolean = false, tail: String = "all")

}
