package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.JsonProperty

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class Image(@JsonProperty("RepoTags") repoTags: Array[String],
            @JsonProperty("Size") size: Long,
            @JsonProperty("Created") created: Long,
            @JsonProperty("Id") id: String,
            @JsonProperty("ParentId") parentId: String,
            @JsonProperty("VirtualSize") virtualSize: Long,
            @JsonProperty("PublishAllPorts") publishAllPorts: Boolean,
            @JsonProperty("CapAdd") capAdd: Boolean,
            @JsonProperty("CapDrop") capDrop: Boolean)
