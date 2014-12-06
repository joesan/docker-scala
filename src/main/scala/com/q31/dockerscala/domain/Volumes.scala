package com.q31.dockerscala.domain

import com.fasterxml.jackson.databind.annotation.{JsonDeserialize, JsonSerialize}
import com.fasterxml.jackson.databind._
import com.fasterxml.jackson.core.{JsonParser, JsonGenerator}
import com.fasterxml.jackson.databind.node.NullNode

/**
 * @author Joe San (codeintheopen@gmail.com)
 */
// TODO... Work on this class as this seems to be confusing!!
// TODO... Use Jacksons ObjectMapper to get a Map of key values!
@JsonDeserialize(using = classOf[Volumes.VolumeDeserializer])
@JsonSerialize(using = classOf[Volumes.VolumeSerializer])
class Volumes {

  // TODO.. Avoid state mutation!!!
  var isReadWrite: Boolean = false
  var path: String = ""

  def this(path: String) {
    this()
    this.path = path
  }

  def this(path: String, isReadWrite: Boolean) {
    this()
    this.path = path
    this.isReadWrite = isReadWrite
  }
}
object Volumes {

  def empty = new Volumes()

  def parse(serialized: String) = new Volumes(serialized)

  class VolumeSerializer extends JsonSerializer[Volumes] {

    override def serialize(volume: Volumes, jsonGen: JsonGenerator, serProvider: SerializerProvider) = {
      jsonGen.writeStartObject()
      jsonGen.writeFieldName(volume.path)
      jsonGen.writeString(volume.isReadWrite.toString)
      jsonGen.writeEndObject()
    }
  }
  class VolumeDeserializer extends JsonDeserializer[Volumes] {

    override def deserialize(jsonParser: JsonParser, dsc: DeserializationContext): Volumes = {
      val oc = jsonParser.getCodec()
      val node: JsonNode = oc.readTree(jsonParser)
      if (!node.equals(NullNode.getInstance())) {
        val field = node.fields().next()
        Volumes.parse(field.getKey())
      } else {
        return Volumes.empty
      }
    }
  }
}
