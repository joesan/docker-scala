package com.q31.dockerscala.util

import java.net.URI

import com.typesafe.config.{Config, ConfigFactory}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
case class DockerClientConfig(uri: URI, version: String, userName: String, password: String, email: String, serverAddress: String, dockerCertPath: String, readTimeOut: Int) {
  require(uri != null,     "docker.server.uri is not provided!")
  require(readTimeOut > 0, "docker.io.readTimeOut is 0, should be a reasonable value")
}
object DockerClientConfig {

  lazy val DEFAULT_SERVER_URI = ""
  lazy val DEFAULT_SERVER_VERSION = ""
  lazy val DEFAULT_SERVER_USERNAME = ""
  lazy val DEFAULT_SERVER_PASSWORD = ""
  lazy val DEFAULT_IO_EMAIL = ""
  lazy val DEFAULT_SERVER_SERVER_ADDRESS = ""
  lazy val DEFAULT_IO_CERT_PATH = ""
  lazy val DEFAULT_READ_TIMEOUT = 10

  private def getDockerClientConfig(config: Config) = {
    DockerClientConfig(URI.create(Option(config.getString("docker.server.uri")).getOrElse(DEFAULT_SERVER_URI)),
                       Option(config.getString("docker.server.version"))       .getOrElse(DEFAULT_SERVER_VERSION),
                       Option(config.getString("docker.server.userName"))      .getOrElse(DEFAULT_SERVER_USERNAME),
                       Option(config.getString("docker.server.password"))      .getOrElse(DEFAULT_SERVER_PASSWORD),
                       Option(config.getString("docker.io.email"))             .getOrElse(DEFAULT_IO_EMAIL),
                       Option(config.getString("docker.server.serverAddress")) .getOrElse(DEFAULT_SERVER_SERVER_ADDRESS),
                       Option(config.getString("docker.io.dockerCertPath"))    .getOrElse(DEFAULT_IO_CERT_PATH),
                       Option(config.getInt("docker.io.readTimeOut"))          .getOrElse(DEFAULT_READ_TIMEOUT))
  }

  def default() = getDockerClientConfig(ConfigFactory.load)

  def withConfig(config: Config) = getDockerClientConfig(config)
}
