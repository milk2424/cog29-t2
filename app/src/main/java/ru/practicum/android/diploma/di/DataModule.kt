package ru.practicum.android.diploma.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.core.database.AppDatabase
import ru.practicum.android.diploma.core.database.ListStringConverter
import ru.practicum.android.diploma.core.navigation.ExternalNavigator
import ru.practicum.android.diploma.core.navigation.ExternalNavigatorImpl
import ru.practicum.android.diploma.core.network.ApiService
import ru.practicum.android.diploma.core.network.NetworkCaller
import ru.practicum.android.diploma.core.network.NetworkCallerImpl
import ru.practicum.android.diploma.core.network.NetworkChecker
import ru.practicum.android.diploma.core.network.NetworkCheckerImpl
import ru.practicum.android.diploma.data.repository.CountryRepositoryImpl
import ru.practicum.android.diploma.data.repository.FavoritesRepositoryImpl
import ru.practicum.android.diploma.data.repository.TeamRepositoryImpl
import ru.practicum.android.diploma.data.repository.VacancyRepositoryImpl
import ru.practicum.android.diploma.domain.model.Developer
import ru.practicum.android.diploma.domain.repository.CountryRepository
import ru.practicum.android.diploma.domain.repository.FavoritesRepository
import ru.practicum.android.diploma.domain.repository.TeamRepository
import ru.practicum.android.diploma.domain.repository.VacancyRepository
import java.util.concurrent.TimeUnit

private const val BASE_URL = "http://155.212.163.151"
private const val NETWORK_TIMEOUT_SEC = 5L

val dataModule = module {
    single<NetworkChecker> { NetworkCheckerImpl(androidContext()) }

    single<ApiService> {
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("Authorization", "Bearer ${BuildConfig.API_ACCESS_TOKEN}")
                            .addHeader("Content-Type", "application/json")
                            .build()
                    )
                }
                .connectTimeout(NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)
                .readTimeout(NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)
                .writeTimeout(NETWORK_TIMEOUT_SEC, TimeUnit.SECONDS)
                .build()

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .build()

        retrofit.create(ApiService::class.java)
    }

    single { ListStringConverter(get()) }

    single {
        Room.databaseBuilder(androidContext(), AppDatabase::class.java, "database.db")
            .addTypeConverter(get<ListStringConverter>())
            .fallbackToDestructiveMigration(false)
            .build()
    }

    single { get<AppDatabase>().vacancyDao() }

    single<TeamRepository> {
        val developers = listOf(
            Developer(R.string.dev_vinokurov_name, R.string.dev_vinokurov_github),
            Developer(R.string.dev_milko_name, R.string.dev_milko_github),
            Developer(R.string.dev_pchelintsev_name, R.string.dev_pchelintsev_github),
            Developer(R.string.dev_salnikov_name, R.string.dev_salnikov_github),
            Developer(R.string.dev_sergeev_name, R.string.dev_sergeev_github)
        )
        TeamRepositoryImpl(developers)
    }

    single<ExternalNavigator> {
        ExternalNavigatorImpl(androidContext())
    }

    single<NetworkCaller> {
        NetworkCallerImpl(get(), get())
    }

    single<VacancyRepository> { VacancyRepositoryImpl(get(), get()) }

    single<FavoritesRepository> { FavoritesRepositoryImpl(get()) }

    single<CountryRepository> { CountryRepositoryImpl(get(), get()) }
}
