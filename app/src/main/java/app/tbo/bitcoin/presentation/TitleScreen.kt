package app.tbo.bitcoin.presentation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp


@Composable
fun TitleScreen(modifier: Modifier = Modifier, text: String, fontSize: Int) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold
    )
}