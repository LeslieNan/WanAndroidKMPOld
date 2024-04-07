package feature_article

import core_base.widget.BannerData
import core_network.ViewModelExt.exceptionHandler
import core_network.ViewModelExt.simplePager
import data_article.ArticleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class HomePageViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

    private val _banner = MutableStateFlow<List<BannerData>>(listOf())
    val banner = _banner.asStateFlow()

    init {
        getBannerData()
    }

    /**
     * 首页文章列表
     */
    val articleList = simplePager { articleRepository.getArticleList(it)!! }

    fun getBannerData() {
        viewModelScope.launch(exceptionHandler) {
            val homeBanner = articleRepository.getHomeBanner()
            println("返回数据="+homeBanner)
            val bannerList = mutableListOf<BannerData>()
            homeBanner?.forEach {
                bannerList.add(BannerData(it.imagePath, it.url))
            }
            _banner.emit(bannerList)
        }
    }

}