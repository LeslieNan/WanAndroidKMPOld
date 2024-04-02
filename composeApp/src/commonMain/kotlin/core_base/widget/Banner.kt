//package core_base.widget
//
//import androidx.compose.animation.animateContentSize
//import androidx.compose.foundation.ExperimentalFoundationApi
//import androidx.compose.foundation.Image
//import androidx.compose.foundation.background
//import androidx.compose.foundation.clickable
//import androidx.compose.foundation.layout.Arrangement
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.Row
//import androidx.compose.foundation.layout.Spacer
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.foundation.layout.fillMaxWidth
//import androidx.compose.foundation.layout.height
//import androidx.compose.foundation.layout.padding
//import androidx.compose.foundation.layout.size
//import androidx.compose.foundation.layout.width
//import androidx.compose.foundation.pager.HorizontalPager
//import androidx.compose.foundation.pager.rememberPagerState
//import androidx.compose.foundation.shape.CircleShape
//import androidx.compose.material.MaterialTheme
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.LaunchedEffect
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Alignment
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.draw.clip
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.input.pointer.PointerEventPass
//import androidx.compose.ui.input.pointer.changedToDownIgnoreConsumed
//import androidx.compose.ui.input.pointer.changedToUpIgnoreConsumed
//import androidx.compose.ui.input.pointer.pointerInput
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.res.painterResource
//import androidx.compose.ui.unit.dp
//import coil.annotation.ExperimentalCoilApi
//import coil.compose.rememberAsyncImagePainter
//import com.example.core_base.R
////import com.google.accompanist.pager.ExperimentalPagerApi
////import com.google.accompanist.pager.HorizontalPager
////import com.google.accompanist.pager.rememberPagerState
//import kotlinx.coroutines.delay
//
///**
// * Author by haolan
// * Email leslienan@qq.com
// * Date on 2023/11/20.
// * PS:
// */
//
//@OptIn(ExperimentalFoundationApi::class)
//@Composable
//fun Banner(
//    list: List<BannerData>?,
//    timeMillis: Long = 3000,
//     loadImage: Int = R.mipmap.ic_launcher,
//    indicatorAlignment: Alignment = Alignment.BottomCenter,
//    onClick: (link: String) -> Unit = {}
//) {
//
//    Box(
//        modifier = Modifier.background(MaterialTheme.colors.background).fillMaxWidth()
//            .height(220.dp)
//    ) {
//
//        if (list == null) {
//            //加载中的图片
//            Image(
//                painterResource(loadImage),
//                modifier = Modifier.fillMaxSize(),
//                contentDescription = null,
//                contentScale = ContentScale.Crop
//            )
//        } else {
//            val pagerState = rememberPagerState(
////                //预加载的个数
////                initialOffscreenLimit = 1,
////                //是否无限循环
////                infiniteLoop = true,
//                //初始页面
//                initialPage = 0
//            ){
//                list.size
//            }
//
//            //监听动画执行
//            var executeChangePage by remember { mutableStateOf(false) }
//            var currentPageIndex = 0
//
//            //自动滚动
//            LaunchedEffect(pagerState.currentPage, executeChangePage) {
//                if (pagerState.pageCount > 0) {
//                    delay(timeMillis)
//                    //这里直接+1就可以循环，前提是infiniteLoop == true
//                    pagerState.animateScrollToPage(pagerState.currentPage + 1)
//                }
//            }
//
//            HorizontalPager(
//                state = pagerState,
//                modifier = Modifier.pointerInput(pagerState.currentPage) {
//                    awaitPointerEventScope {
//                        while (true) {
//                            //PointerEventPass.Initial - 本控件优先处理手势，处理后再交给子组件
//                            val event = awaitPointerEvent(PointerEventPass.Initial)
//                            //获取到第一根按下的手指
//                            val dragEvent = event.changes.firstOrNull()
//                            when {
//                                //当前移动手势是否已被消费
//                                dragEvent!!.isConsumed -> {
//                                    return@awaitPointerEventScope
//                                }
//                                //是否已经按下(忽略按下手势已消费标记)
//                                dragEvent.changedToDownIgnoreConsumed() -> {
//                                    //记录下当前的页面索引值
//                                    currentPageIndex = pagerState.currentPage
//                                }
//                                //是否已经抬起(忽略按下手势已消费标记)
//                                dragEvent.changedToUpIgnoreConsumed() -> {
//                                    //当页面没有任何滚动/动画的时候pagerState.targetPage为null，这个时候是单击事件
//                                    if (pagerState.targetPage == null) return@awaitPointerEventScope
//                                    //当pageCount大于1，且手指抬起时如果页面没有改变，就手动触发动画
//                                    if (currentPageIndex == pagerState.currentPage && pagerState.pageCount > 1) {
//                                        executeChangePage = !executeChangePage
//                                    }
//                                }
//                            }
//                        }
//                    }
//                }
//                    .clickable(onClick = { onClick(list[pagerState.currentPage].linkUrl) })
//                    .fillMaxSize(),
//            ) { page ->
//                Image(
//                    painter = rememberAsyncImagePainter(list[page].imageUrl),
//                    modifier = Modifier.fillMaxSize(),
//                    contentScale = ContentScale.Crop,
//                    contentDescription = null
//                )
//            }
//
//            Box(
//                modifier = Modifier.align(indicatorAlignment)
//                    .padding(bottom = 6.dp, start = 6.dp, end = 6.dp)
//            ) {
//
//                //指示点
//                Row(
//                    horizontalArrangement = Arrangement.Center,
//                    verticalAlignment = Alignment.CenterVertically
//                ) {
//                    for (i in list.indices) {
//                        //大小
//                        var size by remember { mutableStateOf(5.dp) }
//                        size = if (pagerState.currentPage == i) 7.dp else 5.dp
//
//                        //颜色
//                        val color =
//                            if (pagerState.currentPage == i) MaterialTheme.colors.primary else Color.Gray
//
//                        Box(
//                            modifier = Modifier.clip(CircleShape).background(color)
//                                //当size改变的时候以动画的形式改变
//                                .animateContentSize().size(size)
//                        )
//                        //指示点间的间隔
//                        if (i != list.lastIndex) Spacer(
//                            modifier = Modifier.height(0.dp).width(4.dp)
//                        )
//                    }
//                }
//
//            }
//        }
//
//    }
//
//}
//
///**
// * 轮播图数据
// */
//data class BannerData(
//    val imageUrl: String,
//    val linkUrl: String
//)