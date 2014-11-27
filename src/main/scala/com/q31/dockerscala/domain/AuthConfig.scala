package com.q31.dockerscala.domain

import com.fasterxml.jackson.annotation.{JsonProperty, JsonIgnoreProperties}

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
@JsonIgnoreProperties(ignoreUnknown = true)
class AuthConfig(@JsonProperty("Username") val username: String,
                 @JsonProperty("Password") val password: String,
                 @JsonProperty("EMail") val email: String,
                 @JsonProperty("ServerAddress") val serverAddress: String = "https://index.docker.io/v1/")
