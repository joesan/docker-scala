package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class DockerVersion(@JsonProperty("ApiVersion") apiVersion: String,
                         @JsonProperty("Version")    version: String,
                         @JsonProperty("GitCommit")  gitCommit: String,
                         @JsonProperty("GoVersion")  goVersion: String)
