package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class Top(@JsonProperty("Titles") titles: Array[String],
          @JsonProperty("Processes") processes: Array[Array[String]]) {}
