package test.web.kotlin

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class JsonTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val mapper = jacksonObjectMapper()
            val req = mapper.readValue<BidRequest>(sampleRequest)

            println("hogehoge")
            println(req)
        }
    }
}


data class Device(
        @JsonProperty("ua") val ua: String?,
        @JsonProperty("type")val type: String
)

data class Medium(
        @JsonProperty("domain") val domain: String?
)

data class BidRequest(
        @JsonProperty("id") val id: String,
        @JsonProperty("medium") val medium: Medium?,
        @JsonProperty("device") val device: Device
)


val sampleRequest = """
{
    "id": "hogefuga",
    "medium": {
        "domain": "google.com"
    },
    "device": {
        "type": "dooh"
    }
}
""".trimIndent()

