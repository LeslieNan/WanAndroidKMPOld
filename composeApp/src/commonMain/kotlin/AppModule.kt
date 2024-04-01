import app.HomeRepository
import app.HomeViewModel
import org.koin.dsl.module

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/4/1.
 * PS:
 */
object AppModule {

    fun createRepository() = module {
        single { HomeRepository() }
        // 其他需要注入的依赖项
    }

    fun createViewModel() = module {
        factory { HomeViewModel(get()) }

    }
}