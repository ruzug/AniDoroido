package com.yzr.anidoroido

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.yzr.anidoroido.ui.theme.AniDoroidoTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniDoroidoTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Android",
//                        modifier = Modifier.padding(innerPadding)
//                    )
                    Test(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Composable
fun Test(
    viewModel: MainViewModel = viewModel(),
    modifier: Modifier = Modifier
) {
    val mainUiState: MainUiState by viewModel.uiState.collectAsStateWithLifecycle()
    when (mainUiState) {
        MainUiState.Loading -> Text(text = "Loading", modifier = modifier,)
        is MainUiState.Success ->
            LazyColumn(modifier = modifier) {
                items((mainUiState as MainUiState.Success).animeList) { anime ->
                    Text(
                        text = anime.name,
                    )
                }
            }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AniDoroidoTheme {
        Greeting("Android")
    }
}