package com.q31.dockerscala

import org.scalatest.{Matchers, FlatSpec}
import com.q31.dockerscala.domain.Volumes

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
class ThrowAwayTest extends FlatSpec with Matchers {

  "Volume Serialization" should "serialize a Volume to a JSON String" in {
    val volumes = new Volumes("/tmp")
    Volumes.VolumeSerializer.
  }
}
