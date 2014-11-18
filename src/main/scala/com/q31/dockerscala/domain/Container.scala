package com.q31.dockerscala.api.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
case class Container(@JsonProperty("Command") command: String,
                     @JsonProperty("Created") created: String,
                     @JsonProperty("Id") id: String,
                     @JsonProperty("Image") image: String,
                     @JsonProperty("Names") names: Array[String],
                     @JsonProperty("Ports") ports: Array[Port],
                     @JsonProperty("SizeRw") sizeRw: Long,
                     @JsonProperty("SizeRootFs") sizeRootFs: Long)

case class Port(@JsonProperty("IP") ip: String,
                @JsonProperty("PrivatePort") privatePort: Int,
                @JsonProperty("PublicPort") publicPort: Int,
                @JsonProperty("Type") portType: String)
