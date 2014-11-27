package com.q31.dockerscala.api.response

import com.fasterxml.jackson.annotation.{JsonProperty, JsonIgnoreProperties}
import com.q31.dockerscala.domain.ContainerConfig

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class InspectImageResponse(@JsonProperty("Architecture") arch: String,
                           @JsonProperty("Author") author: String,
                           @JsonProperty("Comment") comment: String,
                           @JsonProperty("Config") config: ContainerConfig,
                           @JsonProperty("Container") container: String,
                           @JsonProperty("Created") created: String,
                           @JsonProperty("DockerVersion") dockerVersion: String,
                           @JsonProperty("Id") id: String,
                           @JsonProperty("Os") os: String,
                           @JsonProperty("Parent") parent: String,
                           @JsonProperty("Size") size: String)

