package core_network

import io.ktor.client.HttpClient
import io.ktor.client.plugins.logging.Logging

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/3/28.
 * PS:
 */
object NetHttpClient {

    val client = HttpClient() {
        expectSuccess = true
        install(Logging)
    }
}