package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class ContainerChangeLog(@JsonProperty("Path") path: String,
                         @JsonProperty("Kind") kind: Int)
