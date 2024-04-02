package core_network

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.PagingSource
import androidx.paging.PagingState
import androidx.paging.cachedIn
import kotlinx.coroutines.flow.Flow
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2024/4/2.
 * PS:
 */
fun <T : Any> ViewModel.simplePager(
    config: PagingConfig = PagingConfig(NetConst.pageSize),
    callAction: suspend (page: Int) -> ListWrapperModel<T>
): Flow<PagingData<T>> {
    return pager(config, 0) {
        val currentPage = it.key ?: 0
        return@pager try {
            //请求到的数据
            val listWrapper = callAction.invoke(currentPage)
            val prePage = if (currentPage > 0) currentPage - 1 else null
            val nextPage = if (currentPage < listWrapper.pageCount) currentPage + 1 else null//没有更多数据
            PagingSource.LoadResult.Page(listWrapper.datas, prePage, nextPage)
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}

fun <K : Any, V : Any> ViewModel.pager(
    config: PagingConfig = PagingConfig(NetConst.pageSize),
    initialKey: K? = null,
    loadData: suspend (PagingSource.LoadParams<K>) -> PagingSource.LoadResult<K, V>
): Flow<PagingData<V>> {
    return Pager(config = config, initialKey = initialKey) {
        object : PagingSource<K, V>() {
            override suspend fun load(params: LoadParams<K>): LoadResult<K, V> {
                return loadData.invoke(params)
            }

            override fun getRefreshKey(state: PagingState<K, V>): K? {
                return initialKey
            }

        }
    }.flow.cachedIn(viewModelScope)
}