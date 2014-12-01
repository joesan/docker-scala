package com.q31.dockerscala

import org.scalatest.{BeforeAndAfterAll, Matchers, FlatSpec}
import com.q31.dockerscala.domain.{DockerVersion, SystemInfo}
import com.typesafe.config.ConfigFactory

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class DockerRemoteClientImplTest extends FlatSpec with Matchers {

  val clientConfig = ConfigFactory.load()

  behavior of "DockerRemoteClient"

  /* Misc API's Tests */
  "DockerRemoteClient#info" should "fetch SystemInfo" in {
    val sysInfo: SystemInfo = DockerRemoteClientFactory.buildFromConfig(clientConfig).info
    sysInfo.containers  should be > 0
    sysInfo.images      should be > 0
    sysInfo.nGoroutines should be > 0
  }

  "DockerRemoteClient#version" should "fetch Version" in {
    val version: DockerVersion = DockerRemoteClientFactory.buildFromConfig(clientConfig).version
    version.apiVersion  should be > "1.15"
  }
}