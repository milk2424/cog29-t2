package ru.practicum.android.diploma.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

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
    tertiary = Colors.Gray,
    onTertiary = Colors.Blue,
    secondary = Colors.Gray,
    onSecondary = Colors.BlackUniversal
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

    MaterialTheme(
        colorScheme = colorScheme,
        typography = DiplomaTypography,
        content = content
    )
}
