package androidx.lifecycle

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import moe.tlaster.precompose.viewmodel.ViewModel

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/2/27.
 * PS:
 */

private const val TOAST_KEY = "androidx.lifecycle.ViewModelNetExt.TOAST_KEY"
private const val LOADING_KEY = "androidx.lifecycle.ViewModelNetExt.LOADING_KEY"
private const val LOGIN_KEY = "androidx.lifecycle.ViewModelNetExt.LOGIN_KEY"

public val ViewModel.toast: MutableSharedFlow<String>
    get() {
        val target: MutableSharedFlow<String>? = this.getTag(TOAST_KEY)
        if (target != null) {
            return target
        }
        return setTagIfAbsent(TOAST_KEY, MutableSharedFlow())
    }

public val ViewModel.loading: MutableStateFlow<Boolean>
    get() {
        val target: MutableStateFlow<Boolean>? = this.getTag(LOADING_KEY)
        if (target != null) {
            return target
        }
        return setTagIfAbsent(LOADING_KEY, MutableStateFlow(false))
    }

public val ViewModel.login: MutableSharedFlow<Unit>
    get() {
        val target: MutableSharedFlow<Unit>? = this.getTag(LOGIN_KEY)
        if (target != null) {
            return target
        }
        return setTagIfAbsent(LOGIN_KEY, MutableSharedFlow())
    }