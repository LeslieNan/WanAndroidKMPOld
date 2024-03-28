import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import app.HomeViewModel
import moe.tlaster.precompose.PreComposeApp
import moe.tlaster.precompose.flow.collectAsStateWithLifecycle
import moe.tlaster.precompose.navigation.NavHost
import moe.tlaster.precompose.navigation.rememberNavigator
import moe.tlaster.precompose.viewmodel.viewModel
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.jetbrains.compose.ui.tooling.preview.Preview

@OptIn(ExperimentalResourceApi::class)
@Composable
@Preview
fun App() {
    PreComposeApp {
        val navigator = rememberNavigator()
        MaterialTheme {
            NavHost(navigator, "/home") {
                scene("/home") {
                    val homeViewModel = viewModel(HomeViewModel::class) { HomeViewModel() }
                    val name by homeViewModel.name.collectAsStateWithLifecycle()
                    Column(
                        modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Text(
                            text = "Greet Me!",
                            style = MaterialTheme.typography.h6
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        TextField(
                            value = name,
                            maxLines = 1,
                            label = { Text(text = "Enter your name") },
                            onValueChange = homeViewModel::setName
                        )
                        Spacer(modifier = Modifier.height(30.dp))
                        Button(
                            onClick = {
                                navigator.navigate(route = "/greeting/$name")
                            }
                        ) {
                            Text(text = "GO!")
                        }
                    }
                }
            }
        }
    }
}