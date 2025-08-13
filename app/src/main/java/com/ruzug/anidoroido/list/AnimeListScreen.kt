package com.ruzug.anidoroido.list

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalInspectionMode
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import coil3.compose.AsyncImage
import com.example.compose.AniDoroidoTheme
import com.ruzug.anidoroido.BottomNavigationEvents
import com.ruzug.anidoroido.R
import com.ruzug.anidoroido.datatmp.SampleData
import com.ruzug.anidoroido.datatmp.Anime
import com.ruzug.anidoroido.datatmp.UserMedia
import com.ruzug.anidoroido.placeHolder
import kotlinx.serialization.Serializable
import kotlin.math.ceil

@Serializable
data object AnimeList: NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AnimeListScreen(
    bottomNav: BottomNavigationEvents = BottomNavigationEvents(),
) {
    ListScreen(
        media = { modifier, item ->
            Anime(
                userMedia = item,
                modifier = modifier
            ) },
        index = 2,
        mediaList = SampleData.userMediaListAnime,
        bottomNav = bottomNav
    )
}

@Composable
private fun Anime(
    userMedia: UserMedia,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .padding(
                horizontal = 16.dp,
                vertical = 12.dp
            ).fillMaxWidth()
    ) {
        AsyncImage(
            contentScale = ContentScale.Crop,
            model = userMedia.media.imageUrl,
            error = placeHolder(R.drawable.test_anime),
            contentDescription = "",
            modifier = Modifier
                .width(70.dp)
                .height(70.dp)
                .clip(
                    RoundedCornerShape(8.dp)
                )
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column(modifier.align(Alignment.CenterVertically)) {
            Text(
                text = userMedia.media.name,
                style = MaterialTheme.typography.bodyLarge,
                modifier = Modifier
            )
            Spacer(modifier = Modifier.height(8.dp))
            Row {
                userMedia.score?.let { score ->
                    Text(
                        text = "%s".format(ceil(score.toDouble() / 2).toInt()),
                        style = MaterialTheme.typography.bodyMedium,
                        textAlign = TextAlign.Start,
                        modifier = Modifier.align(Alignment.CenterVertically)
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = "Score / 5",
                        tint = MaterialTheme.colorScheme.tertiary

                    )
                }
                Spacer(modifier.weight(1f))
                Text(
                    text = "%s / %s".format(userMedia.progress, (userMedia.media as Anime).episodes),
                    style = MaterialTheme.typography.bodySmall,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.align(Alignment.CenterVertically)
                )
            }
        }
    }
}

@Composable
private fun Modifier.previewBackground(
    color: Color,
    shape: Shape,
): Modifier {
    if (LocalInspectionMode.current) {
        this.background(color, shape)
    }

    return this
}

@Preview(name = "Light Mode",
    uiMode = Configuration.UI_MODE_NIGHT_NO,
    showSystemUi = true
)
@Preview(
    name = "Dark Mode",
    showBackground = true,
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showSystemUi = true
)
@Composable
fun PreviewAnimeListScreen() {
    AniDoroidoTheme {
        Surface {
            AnimeListScreen()
        }
    }
}