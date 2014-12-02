package com.q31.dockerscala.api.request

import com.q31.dockerscala.DockerRemoteClientFactory
import com.q31.dockerscala.api.request.params.RequestParam.ListContainersReqParam
import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import com.typesafe.config.ConfigFactory

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ListContainersTest extends FlatSpec with Matchers with BeforeAndAfterAll {

  override def beforeAll() = {
    // Connect to the docker daemon
    // Create a couple of containers, check these containers in the tests below!
  }

  override def afterAll() = {
    // Connect to the docker daemon
    // Remove / clean-up all the containers that was created in the scope of this test!
  }

  lazy val testClientConfig = ConfigFactory.load()

  behavior of "ListContainers"

  "ListContainers" should "list all the containers available in a docker running instance" in {
    val params = ListContainersReqParam(all = true)
    val containers = DockerRemoteClientFactory.buildFromConfig(testClientConfig).listContainers(params)
    containers foreach { container =>
      container.id should not be null
      // TODO... Check other ones!
    }
  }
}
