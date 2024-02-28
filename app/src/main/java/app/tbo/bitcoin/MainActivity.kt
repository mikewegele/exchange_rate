package app.tbo.bitcoin

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import app.tbo.bitcoin.data.local.Currency
import app.tbo.bitcoin.data.remote.CurrencyApi
import app.tbo.bitcoin.presentation.ExchangeScreen
import app.tbo.bitcoin.ui.theme.BitcoinTheme

class MainActivity : ComponentActivity() {
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

    val currency = produceState<Currency?>(initialValue = null) {
        CurrencyApi().getExchangeRates(
            onSuccess = {
                value = it
            },
            onError = {
                Log.d("ERROR", it.message.toString())
            }
        )
    }
    
    return BitcoinTheme {
        Greeting("Mike")
        if (currency.value !== null) {
            ExchangeScreen(exchangeRate = currency.value!!)
        }
    }
}