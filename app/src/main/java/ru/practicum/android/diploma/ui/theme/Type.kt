package ru.practicum.android.diploma.ui.theme

import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import ru.practicum.android.diploma.R
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.sp

object AppTypography {
    val YsDisplay = FontFamily(
        Font(R.font.ys_display_regular, FontWeight.Normal),
        Font(R.font.ys_display_medium, FontWeight.Medium),
        Font(R.font.ys_display_bold, FontWeight.Bold)
    )

    val bold32 = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Bold,
        fontSize = 32.sp
    )

    val medium22 = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 22.sp
    )

    val medium16 = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Medium,
        fontSize = 16.sp
    )

    val regular16 = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    )

    val regular12 = TextStyle(
        fontFamily = YsDisplay,
        fontWeight = FontWeight.Normal,
        fontSize = 12.sp
    )
}
