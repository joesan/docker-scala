package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class HostConfig(@JsonProperty("Binds") binds: String,
                 @JsonProperty("ContainerIDFile") containerIdFile: String,
                 @JsonProperty("LxcConf") lxcConf: Array[LxcConf],
                 @JsonProperty("Privileged") privileged: Boolean,
                 @JsonProperty("PortBindings") portBindings: String,
                 @JsonProperty("Links") links: Array[String],
                 @JsonProperty("PublishAllPorts") publishAllPorts: Boolean,
                 @JsonProperty("CapAdd") capAdd: Boolean,
                 @JsonProperty("CapDrop") capDrop: Boolean)
