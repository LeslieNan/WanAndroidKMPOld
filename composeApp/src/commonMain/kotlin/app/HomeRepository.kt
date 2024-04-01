package app

import core_network.ListWrapperModel
import core_network.NetHttpClient

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/3/29.
 * PS:
 */
class HomeRepository {

    suspend fun getArticles(): ListWrapperModel<ArticleModel>? {
        return NetHttpClient.get<ListWrapperModel<ArticleModel>>("article/list/1/json")
    }
}