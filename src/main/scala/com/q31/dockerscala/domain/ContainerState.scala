package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ContainerState(@JsonProperty("Running") running: Boolean,
                     @JsonProperty("Paused") paused: Boolean,
                     @JsonProperty("Pid") pid: Int,
                     @JsonProperty("ExitCode") exitCode: Int,
                     @JsonProperty("StartedAt") startedAt: String,
                     @JsonProperty("FinishedAt") finishedAt: String)
