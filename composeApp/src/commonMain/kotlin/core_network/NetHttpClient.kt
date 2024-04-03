package core_network

import httpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.cookies.AcceptAllCookiesStorage
import io.ktor.client.plugins.cookies.HttpCookies
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.get
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import io.ktor.util.appendIfNameAbsent
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObjectBuilder
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.buildJsonObject

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/3/28.
 * PS:
 */
object NetHttpClient {


    val client = httpClient {
        defaultRequest {
            url(NetConst.baseUrl)
            headers.appendIfNameAbsent(HttpHeaders.ContentType, ContentType.Application.Json.toString())
        }
        install(Logging) {
            level = LogLevel.ALL
            logger = Logger.DEFAULT
//            logger = object :Logger{
//                override fun log(message: String) {
//                    println("网络日志="+message)
//                }
//            }
        }
        install(HttpCookies) {
            storage = AcceptAllCookiesStorage()
        }
        install(ContentNegotiation) {
            json(Json {
                prettyPrint = true
                isLenient = true
            })
        }
        expectSuccess = true
    }

    suspend inline fun <reified T> get(url: String): T? {
        val body = client.get(url).body<BaseNetModel<T>>()
        return body.data
    }

    suspend inline fun <reified T> post(url: String, params: JsonObjectBuilder.() -> Unit): T {
        val response: HttpResponse = client.post(url) {
            contentType(ContentType.Application.Json)
            val jsonBody = buildJsonObject {
                put("key", JsonPrimitive(false))
                params.invoke(this)
            }
            setBody(jsonBody)
        }
        return response.body()
    }
}