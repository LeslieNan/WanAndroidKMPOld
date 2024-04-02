package data_article

import core_network.ListWrapperModel
import core_network.NetHttpClient


/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class ArticleRepository(private val client: NetHttpClient) {

    /**
     * 获取文章列表
     */
    suspend fun getArticleList(pageNum: Int) =
        client.get<ListWrapperModel<ArticleRepository>>("article/list/${pageNum}/json")

//    /**
//     * 获取首页banner数据
//     */
//    suspend fun getHomeBanner() = articleApi.requestHomeBanner()

//    /**
//     * 获取项目分类列表
//     */
//    suspend fun getProjectItems() = articleApi.requestProjectItems()
//
//    /**
//     * 获取项目列表
//     */
//    suspend fun getProjectList(itemId: Int, pageNum: Int) = articleApi.requestProjects(pageNum, itemId)

}