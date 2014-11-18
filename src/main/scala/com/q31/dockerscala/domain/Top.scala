package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Top(@JsonProperty("Titles") titles: Array[String],
          @JsonProperty("Processes") processes: Array[Array[String]]) {}
