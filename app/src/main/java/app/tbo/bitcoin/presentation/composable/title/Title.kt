package app.tbo.bitcoin.presentation.composable.title

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * Composable function to display a title with specified text and font size.
 *
 * @param text the text to display
 * @param fontSize the font size for the title
 */
@Composable
fun Title(text: String, fontSize: Int) {
    Text(
        modifier = Modifier.padding(16.dp),
        text = text,
        fontSize = fontSize.sp,
        fontWeight = FontWeight.Bold
    )
}