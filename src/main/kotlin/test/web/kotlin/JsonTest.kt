package test.web.kotlin

import com.fasterxml.jackson.annotation.JsonAlias
import com.fasterxml.jackson.annotation.JsonAutoDetect
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

class JsonTest {
    companion object {
        @JvmStatic
        fun main(args: Array<String>) {

            val mapper = jacksonObjectMapper()
            val req = mapper.readValue<BidRequest>(sampleRequest)

            println(req)

            try {
                mapper.readValue<BidRequest>(errorRequest)
            } catch (e: Throwable) {
                e.printStackTrace()
            }
        }
    }
}


@JsonAutoDetect
data class Device(
        val ua: String?,
        val type: String
)

@JsonAutoDetect
data class Medium(
        val domain: String?
)


@JsonAutoDetect
data class BidRequest(
        val id: String,
        val medium: Medium?,
        val device: Device
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


// no id
val errorRequest = """
{
    "medium": {
        "domain": "google.com"
    },
    "device": {
        "type": "dooh"
    }
}    
""".trimIndent()

interface Hoge {
    val id: String
    val name: String
}

data class Fuga(override val id: String, override val name: String): Hoge {

}

