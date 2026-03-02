package ru.practicum.android.diploma.core.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private const val COLOR_BLACK_DAY = 0xFF1A1B22
private const val COLOR_BLACK_NIGHT = 0xFFFDFDFD
private const val COLOR_BLACK_UNIVERSAL = 0xFF1A1B22
private const val COLOR_WHITE_DAY = 0xFFFDFDFD
private const val COLOR_WHITE_NIGHT = 0xFF1A1B22
private const val COLOR_WHITE_UNIVERSAL = 0xFFFDFDFD
private const val COLOR_BLUE = 0xFF3772E7
private const val COLOR_RED = 0xFFF56B6C
private const val COLOR_GRAY = 0xFFAEAFB4
private const val COLOR_LIGHT_GRAY = 0xFFE6E8EB
private const val COLOR_BACKGROUND = 0x801A1B22

private object Colors {
    val BlackDay = Color(COLOR_BLACK_DAY)
    val BlackNight = Color(COLOR_BLACK_NIGHT)
    val BlackUniversal = Color(COLOR_BLACK_UNIVERSAL)
    val WhiteDay = Color(COLOR_WHITE_DAY)
    val WhiteNight = Color(COLOR_WHITE_NIGHT)
    val WhiteUniversal = Color(COLOR_WHITE_UNIVERSAL)
    val Blue = Color(COLOR_BLUE)
    val Red = Color(COLOR_RED)
    val Gray = Color(COLOR_GRAY)
    val LightGray = Color(COLOR_LIGHT_GRAY)
    val Background = Color(COLOR_BACKGROUND)
}

private val LightColorScheme = lightColorScheme(
    background = Colors.WhiteDay, // фон экранов
    onBackground = Colors.BlackDay, // основной текст
    primary = Colors.Blue, // фон сообщений и кнопок
    onPrimary = Colors.WhiteUniversal, // текст сообщений и кнопок
    error = Colors.Red, // сбросить, избранное и любые ошибки
    surface = Colors.LightGray, // фон строки поиска
    onSurface = Colors.BlackUniversal, // текст результата поиска
    onSurfaceVariant = Colors.Gray, // второстепенный текст
    outline = Colors.LightGray,

    // цвета для поля зарплаты
    tertiary = Colors.Gray, onTertiary = Colors.Blue, secondary = Colors.Gray, onSecondary = Colors.BlackUniversal
)

private val DarkColorScheme = darkColorScheme(
    background = Colors.WhiteNight,
    onBackground = Colors.BlackNight,
    primary = Colors.Blue,
    onPrimary = Colors.WhiteUniversal,
    error = Colors.Red,
    surface = Colors.Gray,
    onSurface = Colors.BlackUniversal,
    onSurfaceVariant = Colors.Gray,
    tertiary = Colors.WhiteUniversal,
    onTertiary = Colors.Blue,
    secondary = Colors.WhiteUniversal,
    onSecondary = Colors.BlackUniversal,
    outline = Colors.LightGray
)

@Composable
fun DiplomaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(colorScheme = colorScheme, typography = DiplomaTypography, content = content)
}
