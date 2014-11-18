package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ContainerConfig(@JsonProperty("HostName") hostName: String,
                      @JsonProperty("User") created: String,
                      @JsonProperty("Memory") memory: Long,
                      @JsonProperty("MemorySwap") memorySwap: Long,
                      @JsonProperty("AttachStdin") attachStdin: Boolean,
                      @JsonProperty("AttachStdout") attachStdout: Boolean,
                      @JsonProperty("PortSpecs") portSpecs: String,
                      @JsonProperty("Tty") tty: Boolean,
                      @JsonProperty("OpenStdin") openStdin: Boolean,
                      @JsonProperty("StdinOnce") stdinOnce: Boolean,
                      @JsonProperty("Env") env: String,
                      @JsonProperty("Cmd") cmd: Array[String],
                      @JsonProperty("Dns") dns: String,
                      @JsonProperty("Image") image: String,
                      @JsonProperty("Volumes") volumes: Map[String, String],
                      @JsonProperty("VolumesFrom") volumesFrom: String,
                      @JsonProperty("WorkingDir") workingDir: String)
