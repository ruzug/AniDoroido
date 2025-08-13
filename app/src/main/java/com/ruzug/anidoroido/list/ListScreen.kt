package com.ruzug.anidoroido.list

import android.content.res.Configuration
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.compose.AniDoroidoTheme
import com.ruzug.anidoroido.BottomNavigationBar
import com.ruzug.anidoroido.BottomNavigationEvents
import com.ruzug.anidoroido.R
import com.ruzug.anidoroido.datatmp.SampleData
import com.ruzug.anidoroido.datatmp.UserMedia

@Composable
fun ListScreen(
    media: @Composable (Modifier, UserMedia) -> Unit,
    index: Int = 0,
    mediaList: List<UserMedia>,
    bottomNav: BottomNavigationEvents = BottomNavigationEvents(),
) {
    Scaffold(
        containerColor = MaterialTheme.colorScheme.surface,
        bottomBar = {
            BottomNavigationBar(
                index = index,
                bottomNavigation = bottomNav,
            )
        }
    ) { padding ->
        LazyColumn(modifier = Modifier.padding(padding)) {
            item {
                Logo(
                    modifier = Modifier
                        .fillParentMaxWidth()
                        .fillParentMaxHeight(0.3f)
                )
            }
            val categories = SampleData.categories
            categories.forEach { category ->
                val list = mediaList.filter { it.category.name == category.name }
                if (list.isNotEmpty()) {
                    item {
                        ListTitle(
                            title = category.name,
                            modifier = Modifier.padding(vertical = 12.dp)
                        )
                    }
                    items(list.size) { index ->
                        if (index == 0) {
                            Spacer(
                                modifier = Modifier
                                    .height(12.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(9.dp),
                                        shape = RoundedCornerShape(
                                            topStart = 12.dp,
                                            topEnd = 12.dp
                                        )
                                    )
                            )
                        }
                        media(
                            Modifier
                                .padding(horizontal = 16.dp)
                                .background(MaterialTheme.colorScheme.surfaceColorAtElevation(9.dp))
                                .fillMaxWidth(),
                            list[index]
                        )
                        if (index == list.size - 1) {
                            Spacer(
                                modifier = Modifier
                                    .height(12.dp)
                                    .fillMaxWidth()
                                    .padding(horizontal = 16.dp)
                                    .background(
                                        color = MaterialTheme.colorScheme.surfaceColorAtElevation(9.dp),
                                        shape = RoundedCornerShape(
                                            bottomStart = 12.dp,
                                            bottomEnd = 12.dp
                                        )
                                    )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun Logo(modifier: Modifier = Modifier) {
    Box(modifier = modifier) {
        Image(
            contentScale = ContentScale.Crop,
            painter = painterResource(R.drawable.test_header),
            contentDescription = "Logo",
            modifier = Modifier
                .fillMaxSize()
        )
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Brush.verticalGradient(
                listOf(
                    Color.Transparent,
                    Color(0x9E000000)
                ),
            ))
        )
        Row(
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxSize()
        ) {
            Spacer(modifier = Modifier.weight(0.5f))
            Image(
                painter = painterResource(R.drawable.test_avatar),
                contentDescription = "Avatar",
                modifier = Modifier
                    .align(Alignment.Bottom)
            )
            Text(
                text = "yezarou",
                color = Color.White,
                modifier = Modifier
                    .align(Alignment.Bottom)
                    .padding(all = 4.dp)
            )
            Spacer(modifier = Modifier.weight(3f))
        }
    }
}

@Composable
private fun ListTitle(title: String, modifier: Modifier = Modifier) {
    Text(
        text = title,
        style = MaterialTheme.typography.titleLarge,
        textAlign = TextAlign.Center,
        modifier = modifier
            .fillMaxWidth()
    )
}

@Preview(name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true,
)
@Composable
private fun PreviewListScreen() {
    AniDoroidoTheme {
        Surface {
            ListScreen(
                media = { modifier, item ->
                    Text(item.media.name, modifier = modifier)
                },
                index = 2,
                SampleData.userMediaListAnime
            )
        }
    }
}