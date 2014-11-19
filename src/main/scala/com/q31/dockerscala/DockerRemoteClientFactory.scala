package com.q31.dockerscala

import com.typesafe.config.{ConfigFactory, Config}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
object DockerRemoteClientFactory {

  // TODO... DockerClientContext needs WebTarget
  private lazy val clientContext: Config => DockerClientContext = config => { new DockerClientContext(null) }

  def buildFromConfig(config: Config): DockerRemoteClient = new DockerRemoteClientImpl(clientContext(config))

  def buildDefault(): DockerRemoteClient = new DockerRemoteClientImpl(clientContext(ConfigFactory.load))
}
