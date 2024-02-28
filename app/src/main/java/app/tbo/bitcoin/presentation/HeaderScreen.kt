package app.tbo.bitcoin.presentation

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import app.tbo.bitcoin.data.local.ExchangeRate
import app.tbo.bitcoin.data.local.ExchangeRateUnit

@Composable
fun HeaderScreen(exchangeRate: ExchangeRate) {

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf(exchangeRate.rates["eur"]?.name ?: "Euro") }

    var exchangeRateNames = exchangeRate.rates.values.toList().map { it.name }

    println("HELLO")
    println(exchangeRateNames)

        Row(
            modifier = Modifier
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                fontSize = 28.sp,
                fontWeight = FontWeight.SemiBold,
                text = "Bitcoin",
                modifier = Modifier.weight(1f),
                textAlign = TextAlign.Center
            )
            Box(modifier = Modifier
                .clickable(onClick = { expanded = true })
                .weight(1f))
            {
                Text(
                    text = selectedText,
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    textAlign = TextAlign.Center
                )
            }
            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                exchangeRateNames.forEach { item ->
                    println(item)
                    DropdownMenuItem(
                        text = {
                            Text(
                                text = item,
                            )
                        },
                        onClick = {
                            expanded = false
                            selectedText = item
                        }
                    )
                }
                Text(
                    fontSize = 28.sp,
                    fontWeight = FontWeight.SemiBold,
                    text = "Euro",
                    modifier = Modifier.weight(1f),
                    textAlign = TextAlign.Center
                )
            }
        }
}