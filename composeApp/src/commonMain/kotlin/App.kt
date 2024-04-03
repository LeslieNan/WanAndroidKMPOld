import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.MaterialTheme
import androidx.compose.material.SnackbarDefaults.backgroundColor
import androidx.compose.material.Surface
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.TabRowDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import feature_article.HomePage
import kotlinx.coroutines.launch
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import org.jetbrains.compose.ui.tooling.preview.Preview
import org.koin.core.context.startKoin

@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview
fun App() {
    startKoin {
        modules(AppModule.createSingle())
        modules(AppModule.createRepository())
        modules(AppModule.createViewModel())
    }
    PreComposeApp {
        val navigator = rememberNavigator()
        MaterialTheme {
            NavHost(navigator, "/home") {
                scene("/home") {
                    Surface(Modifier.fillMaxSize(), color = backgroundColor) {
                        val tabs = listOf("首页", "知识体系", "项目", "成长", "我的")
                        val pagerState = rememberPagerState(0) { tabs.size }
                        val scope = rememberCoroutineScope()
                        Column(Modifier.fillMaxSize()) {
                            HorizontalPager(
                                state = pagerState,
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .weight(1f)
                            ) { page ->
                                when (page) {
                                    0 -> HomePage()
//                                    2 -> ProjectPage()
//                                    3 -> MePage()
                                }
                            }
                            TabRow(
                                selectedTabIndex = pagerState.currentPage,
                                Modifier
                                    .height(50.dp)
                                    .background(color = Color.Black),
                                indicator = {
//                                TabRowDefaults.Indicator(
//                                    modifier = Modifier
////                                        .fillMaxWidth()
//                                        .wrapContentSize(Alignment.BottomStart)
//                                        .width(0.dp)
//                                        .height(0.dp)//修改指示器高度为1dp，默认2dp
//                                )
//                                TabRowDefaults.Indicator(
//                                    Modifier.tabIndicatorOffset(tabPositions[selectedTabIndex])
//                                )
                                    TabRowDefaults.Indicator(height = 0.dp, color = Color.Transparent)
                                }
                            ) {
                                val primaryColor = MaterialTheme.colors.primary
                                val secondaryColor = MaterialTheme.colors.secondary
                                tabs.forEachIndexed { index, tabName ->
                                    val selected = pagerState.currentPage == index
                                    Tab(selected = selected,
                                        modifier = Modifier.fillMaxHeight(),
                                        selectedContentColor = primaryColor,
                                        unselectedContentColor = secondaryColor,
                                        onClick = {
                                            scope.launch {
                                                pagerState.animateScrollToPage(index)
                                            }
                                        }) {
                                        Text(text = tabName, Modifier.fillMaxHeight())
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}