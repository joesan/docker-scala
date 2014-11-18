package com.q31.dockerscala.api.response

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
case class CreateContainerResponse(@JsonProperty("Id") id: String,
                                   @JsonProperty("Warnings") warnings: Array[String]) {

}
