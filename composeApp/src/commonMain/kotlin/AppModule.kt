import core_network.NetHttpClient
import data_article.ArticleRepository
import feature_article.HomePageViewModel
import org.koin.dsl.module

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/4/1.
 * PS:
 */
object AppModule {

    fun createNetClient() = module {
        single { NetHttpClient }
    }

    fun createRepository() = module {
        single { ArticleRepository(get()) }
        // 其他需要注入的依赖项
    }

    fun createViewModel() = module {
        factory { HomePageViewModel(get()) }
    }
}