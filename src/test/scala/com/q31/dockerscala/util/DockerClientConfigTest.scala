package com.q31.dockerscala.util

import com.typesafe.config.ConfigFactory
import org.scalatest.{Matchers, FlatSpec}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class DockerClientConfigTest extends FlatSpec with Matchers {

  val configEntries = List("docker.server.uri", "docker.server.version", "docker.server.userName", "docker.server.password", "docker.io.email", "docker.server.serverAddress", "docker.io.dockerCertPath", "docker.io.readTimeOut")

  behavior of "reading default docker client config"

  it should "read all config entries and fail when any requirement is not met" in {
    intercept[IllegalArgumentException] { DockerClientConfig.default() }
  }

  it should "read all config entries from the default config" in {
    val dockerConfig = DockerClientConfig.withConfig(ConfigFactory.load("application-test.conf"))
    println(dockerConfig.uri)
    println(dockerConfig.readTimeOut)
  }
}