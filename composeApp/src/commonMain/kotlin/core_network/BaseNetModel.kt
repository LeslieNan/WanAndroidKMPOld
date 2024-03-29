package core_network

import io.ktor.utils.io.core.Input
import io.ktor.utils.io.core.Output
import kotlinx.serialization.KSerializer
import kotlinx.serialization.PolymorphicSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import kotlinx.serialization.json.JsonPrimitive
import kotlinx.serialization.json.JsonTransformingSerializer
import kotlinx.serialization.json.buildJsonObject
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic

/**
 * Author by haonan, Date on 2020/11/30.
 * Email:278913810@qq.com
 * PS:首页使用的model
 */

//@Serializable
//data class BaseNetModel<T : Any>(
//    var errorCode: Int = 0,
//    var errorMsg: String = "",
//    var data: T? = null,
//)
//
//@Serializable
//data class ListWrapperModel<T>(
//    val curPage: Int = 0,
//    val datas: List<T> = listOf(),
//    val offset: Int = 0,
//    val over: Boolean = false,
//    val pageCount: Int = 0,
//    val size: Int = 0,
//    val total: Int = 0
//)