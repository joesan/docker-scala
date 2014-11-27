package com.q31.dockerscala.api.response

import com.fasterxml.jackson.annotation.{JsonProperty, JsonIgnoreProperties}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class SearchImageResponse(@JsonProperty("star_count") startCount: Int,
                          @JsonProperty("is_official") isOfficial: Boolean,
                          @JsonProperty("is_trusted") isTrusted: Boolean,
                          @JsonProperty("name") name: String,
                          @JsonProperty("description") description: String)
