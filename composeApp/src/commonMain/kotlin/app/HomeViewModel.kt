package app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/3/28.
 * PS:
 */
class HomeViewModel : ViewModel() {

    val homeRepository = HomeRepository()

    val name = MutableStateFlow("")
    fun setName(value: String) {
        name.update { value }
    }

    init {
        getArticles()
    }

    fun getArticles() {
        viewModelScope.launch {
            val articles = homeRepository.getArticles()
            println("返回值=$articles")
        }
    }
}