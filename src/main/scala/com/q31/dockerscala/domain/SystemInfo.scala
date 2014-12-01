package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.{JsonIgnoreProperties, JsonProperty}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
case class SystemInfo(@JsonProperty("Containers")    containers: Int,
                 @JsonProperty("Images")             images: Int,
                 @JsonProperty("Driver")             driver: String,
                 @JsonProperty("ExecutionDriver")    executionDriver: String,
                 @JsonProperty("KernelVersion")      kernelVersion: String,
                 @JsonProperty("Debug")              debug: Boolean,
                 @JsonProperty("NFd")                nfd: Int,
                 @JsonProperty("NGoroutines")        nGoroutines: Int,
                 @JsonProperty("NEventsListener")    nEventsListener: Int,
                 @JsonProperty("InitPath")           initPath: String,
                 @JsonProperty("IndexServerAddress") indexServerAddress: String,
                 @JsonProperty("MemoryLimit")        memoryLimit: Boolean,
                 @JsonProperty("IPv4Forwarding")     ipv4Forwarding: Boolean,
                 @JsonProperty("SwapLimit")          swapLimit: Boolean)
