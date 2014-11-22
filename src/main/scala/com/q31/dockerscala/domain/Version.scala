package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Version(@JsonProperty("ApiVersion") apiVersion: String,
              @JsonProperty("Version")    version: String,
              @JsonProperty("GitCommit")  gitCommit: String,
              @JsonProperty("GoVersion")  goVersion: String)
