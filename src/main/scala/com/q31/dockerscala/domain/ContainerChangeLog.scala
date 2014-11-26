package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ContainerChangeLog(@JsonProperty("Path") path: String,
                         @JsonProperty("Kind") kind: Int)
