package core_network

import kotlinx.serialization.Serializable

/**
 * Author by haonan, Date on 2020/11/30.
 * Email:278913810@qq.com
 * PS:首页使用的model
 */

@Serializable
data class BaseNetModel<T>(
    var errorCode: Int = 0,
    var errorMsg: String = "",
    var data: T? = null,
)

@Serializable
data class ListWrapperModel<T>(
    val curPage: Int = 0,
    val datas: List<T> = listOf(),
    val offset: Int = 0,
    val over: Boolean = false,
    val pageCount: Int = 0,
    val size: Int = 0,
    val total: Int = 0
)