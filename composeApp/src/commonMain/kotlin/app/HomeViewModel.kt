package app

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import moe.tlaster.precompose.viewmodel.ViewModel

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/3/28.
 * PS:
 */
class HomeViewModel:ViewModel() {

    val name = MutableStateFlow("")
    fun setName(value: String) {
        name.update { value }
    }
}