package feature_article

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.cash.paging.compose.collectAsLazyPagingItems
import core_base.widget.PagingListUI
import data_article.model.ArticleModel
import moe.tlaster.precompose.koin.koinViewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi

/**
 * Author by haolan
 * Email leslienan@qq.com
 * Date on 2023/11/15.
 * PS:
 */
@OptIn(ExperimentalResourceApi::class)
@Composable
fun HomePage(
    modifier: Modifier = Modifier
) {
    val homeViewModel = koinViewModel(HomePageViewModel::class)
    homeViewModel.articleList
//    val bannerList by homeViewModel.banner.collectAsState()
    val refreshing by remember { mutableStateOf(false) }
    val articleList = homeViewModel.articleList.collectAsLazyPagingItems()
    val result by rememberUpdatedState(homeViewModel.articleList.collectAsLazyPagingItems())
//    val pullRefreshState = rememberPullRefreshState(refreshing, {
//        articleList.refresh()
//    })
    Box() {

        PagingListUI(data = result, content = { HomeArticleCard(it) })

        LazyColumn() {
//            if (bannerList.isNotEmpty()) {
//                item {
//                    Banner(bannerList)
//                }
//            }
        }
//        PullRefreshIndicator(refreshing, pullRefreshState, Modifier.align(Alignment.TopCenter))
    }
}

@ExperimentalResourceApi
@Composable
fun HomeArticleCard(item: ArticleModel) {
    Spacer(modifier = Modifier.height(10.dp))
    Column(
        Modifier
            .padding(16.dp, 10.dp)
            .background(Color.White)
    ) {
        Row {
            Text(
                item.author, Modifier
                    .wrapContentHeight()
                    .weight(1f), fontSize = 12.sp
            )
            Text(item.publishTime.toString(), fontSize = 12.sp)
        }
        Text(item.title, fontSize = 16.sp, fontWeight = FontWeight.Bold)
    }
}