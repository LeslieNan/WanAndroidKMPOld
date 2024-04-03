package core_network

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/24.
 * PS:
 */
data class NetworkException(
    val code: Int,
    val apiMessage: String = "",
    val baseResponse: BaseNetModel<*>? = null,
) : Exception()
