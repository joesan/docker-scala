package com.q31.dockerscala.api.response

import com.fasterxml.jackson.annotation.JsonProperty
import com.q31.dockerscala.domain.{HostConfig, Volumes, ContainerConfig}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
case class InspectContainerResponse(@JsonProperty("Id") id: String,
                                    @JsonProperty("Created") created: String,
                                    @JsonProperty("Path") path: String,
                                    @JsonProperty("Args") args: Array[String],
                                    @JsonProperty("Config") config: ContainerConfig,
                                    @JsonProperty("State") state: ContainerState,
                                    @JsonProperty("Image") imageId: String,
                                    @JsonProperty("NetworkSettings") networkSettings: NetworkSettings,
                                    @JsonProperty("SysInitPath") sysInitPath: String,
                                    @JsonProperty("ResolvConfPath") resolveConfPath: String,
                                    @JsonProperty("Volumes") volumes: Volumes,
                                    @JsonProperty("HostConfig") hostConfig: HostConfig,
                                    @JsonProperty("Links") links: Array[String],
                                    @JsonProperty("PublishAllPorts") publishAllPorts: Boolean,
                                    @JsonProperty("CapAdd") capApp: Array[String],
                                    @JsonProperty("CapDrop") capDrop: Array[String])

case class ContainerState()

case class NetworkSettings()
