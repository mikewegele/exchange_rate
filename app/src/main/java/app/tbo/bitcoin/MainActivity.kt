package app.tbo.bitcoin

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.tbo.bitcoin.network.NetworkChecker
import app.tbo.bitcoin.presentation.screen.ExchangeScreen
import app.tbo.bitcoin.ui.theme.BitcoinTheme
import app.tbo.bitcoin.ui.theme.Black80

class MainActivity : ComponentActivity() {

    private val networkChecker by lazy {
        NetworkChecker(getSystemService(ConnectivityManager::class.java))
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BitcoinTheme {
                Application()
            }
        }
    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun Application() {

    return BitcoinTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Black80)) {
            ExchangeScreen()
        }
    }
}