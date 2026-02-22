package ru.practicum.android.diploma.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable

private val LightColorScheme = lightColorScheme(
    background = ColorsRaw.WhiteDay,        // фон экранов
    onBackground = ColorsRaw.BlackDay,      // основной текст
    primary = ColorsRaw.Blue,               // фон сообщений и кнопок
    onPrimary = ColorsRaw.WhiteUniversal,   // текст сообщений и кнопок
    error = ColorsRaw.Red,                  // сбросить, избранное и любые ошибки
    surface = ColorsRaw.LightGray,          // фон строки поиска
    onSurface = ColorsRaw.BlackUniversal,   // текст результата поиска
    onSurfaceVariant = ColorsRaw.Gray,      // второстепенный текст

    // цвета для поля зарплаты
    tertiary  = ColorsRaw.Gray,
    onTertiary  = ColorsRaw.Blue,
    secondary  = ColorsRaw.Gray,
    onSecondary  = ColorsRaw.BlackUniversal
)

private val DarkColorScheme = darkColorScheme(
    background = ColorsRaw.WhiteNight,
    onBackground = ColorsRaw.BlackNight,
    primary = ColorsRaw.Blue,
    onPrimary = ColorsRaw.WhiteUniversal,
    error = ColorsRaw.Red,
    surface = ColorsRaw.Gray,
    onSurface = ColorsRaw.BlackUniversal,
    onSurfaceVariant = ColorsRaw.Gray,
    tertiary = ColorsRaw.WhiteUniversal,
    onTertiary = ColorsRaw.Blue,
    secondary = ColorsRaw.WhiteUniversal,
    onSecondary = ColorsRaw.BlackUniversal
)

@Composable
fun DiplomaTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        content = content
    )
}
