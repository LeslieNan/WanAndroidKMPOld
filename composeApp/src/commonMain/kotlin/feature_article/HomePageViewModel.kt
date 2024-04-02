package feature_article

import core_network.simplePager
import data_article.ArticleRepository
import moe.tlaster.precompose.viewmodel.ViewModel

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
class HomePageViewModel(
    private val articleRepository: ArticleRepository
) : ViewModel() {

//    private val _banner = MutableStateFlow<List<BannerData>>(listOf())
//    val banner = _banner.asStateFlow()

//    init {
//        getBannerData()
//    }

    /**
     * 首页文章列表
     */
    val articleList = simplePager { articleRepository.getArticleList(it)!! }


//    fun getBannerData() {
//        viewModelScope.launch(exceptionHandler) {
//            val homeBanner = articleRepository.getHomeBanner()
//            val bannerList = mutableListOf<BannerData>()
//            homeBanner.forEach {
//                bannerList.add(BannerData(it.imagePath, it.url))
//            }
//            _banner.emit(bannerList)
//        }
//    }

}