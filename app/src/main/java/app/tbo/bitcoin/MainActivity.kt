package app.tbo.bitcoin

import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.api.client.CurrencyApi
import app.tbo.bitcoin.network.NetworkChecker
import app.tbo.bitcoin.presentation.ExchangeScreen
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
                GreetingPreview()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    return Text(text = name, modifier = Modifier.fillMaxSize())
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {

    val exchangeRate = produceState<ExchangeRate?>(initialValue = null) {
        CurrencyApi().getExchangeEuro(
            onSuccess = {
                value = it
            },
            onError = {
                Log.d("ERROR", it.message.toString())
            }
        )
    }

    return BitcoinTheme {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Black80)) {
            if (exchangeRate.value !== null) {
                ExchangeScreen(exchangeRate = exchangeRate.value!!)
            }
        }
    }
}