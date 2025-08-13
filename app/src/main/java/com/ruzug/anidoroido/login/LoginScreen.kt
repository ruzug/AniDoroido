package com.ruzug.anidoroido.login

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation3.runtime.NavKey
import com.example.compose.AniDoroidoTheme
import com.ruzug.anidoroido.BottomNavigationBar
import com.ruzug.anidoroido.BottomNavigationEvents
import com.ruzug.anidoroido.R
import kotlinx.serialization.Serializable

@Serializable
data object Login: NavKey

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(
    bottomNav: BottomNavigationEvents = BottomNavigationEvents(),
) {
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.surface,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(stringResource(R.string.app_name))
                }
            )
        },
        bottomBar = {
            BottomNavigationBar(
                index = 1,
                bottomNavigation = bottomNav,
            )
        }
    ) { padding ->
        var username by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        Box (Modifier
            .padding(padding)
            .fillMaxSize()
        ) {
            Column(
                modifier = Modifier.padding(all = 16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(top = 12.dp)
                        .fillMaxWidth(),
                    text = stringResource(R.string.login_title),
                    fontWeight = FontWeight.Bold,
                    style = MaterialTheme.typography.displayLarge,
                    textAlign = TextAlign.Center
                )
                Spacer(modifier = Modifier.height(8.dp))
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = username,
                    onValueChange = { username = it },
                    label = { Text("Nombre de usuario") },
                )
                OutlinedTextField(
                    modifier = Modifier.fillMaxWidth(),
                    value = password,
                    onValueChange = { password = it },
                    label = { Text("Contraseña") },
                )
                Spacer(Modifier.weight(1f))
                Button(onClick = { }, modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 64.dp)
                ) {
                    Text("Iniciar Sesión")
                }
            }
        }
    }
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
private fun PreviewListScreen() {
    AniDoroidoTheme {
        Surface {
            LoginScreen()
        }
    }
}