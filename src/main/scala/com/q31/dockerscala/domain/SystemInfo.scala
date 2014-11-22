package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
case class SystemInfo(@JsonProperty("Containers")    containers: Int,
                 @JsonProperty("Images")             images: Int,
                 @JsonProperty("Driver")             driver: String,
                 @JsonProperty("ExecutionDriver")    executionDriver: String,
                 @JsonProperty("KernelVersion")      kernelVersion: String,
                 @JsonProperty("NFd")                nfd: Int,
                 @JsonProperty("NGoroutines")        nGoroutines: Int,
                 @JsonProperty("NEventsListener")    nEventsListener: Int,
                 @JsonProperty("InitPath")           initPath: String,
                 @JsonProperty("IndexServerAddress") indexServerAddress: String,
                 @JsonProperty("MemoryLimit")        memoryLimit: Boolean,
                 @JsonProperty("IPv4Forwarding")     ipv4Forwarding: Boolean,
                 @JsonProperty("SwapLimit")          swapLimit: Boolean)
