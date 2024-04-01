import io.ktor.client.HttpClient
import io.ktor.client.HttpClientConfig

interface Platform {
    val name: String
}

/**
 * 获取平台名称
 */
expect fun getPlatform(): Platform

/**
 * 获取各个平台的ktor client引擎
 */
expect fun httpClient(config: HttpClientConfig<*>.() -> Unit = {}): HttpClient