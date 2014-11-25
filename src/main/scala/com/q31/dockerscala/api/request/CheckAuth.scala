package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerClientContext
import javax.ws.rs.core.MediaType
import javax.ws.rs.client.Entity._
import com.q31.dockerscala.api.request.params.RequestParam.AuthReqParam

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class CheckAuth(params: AuthReqParam) {

  val resourcePath = "/auth"

  def execute(dockerClientContext: DockerClientContext): AuthStatus = {
    dockerClientContext.getBaseResource.path(resourcePath)
      .request()
      .accept(MediaType.APPLICATION_JSON)
      .post(entity(params, MediaType.APPLICATION_JSON)).getStatus()
  }

}
object CheckAuth extends ((DockerClientContext, AuthReqParam) => AuthStatus) {

  def apply(clientContext: DockerClientContext, params: AuthReqParam): Unit = new CheckAuth(params).execute(clientContext)
}
