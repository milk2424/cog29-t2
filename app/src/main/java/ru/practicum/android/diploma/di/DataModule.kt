package ru.practicum.android.diploma.di

import androidx.room.Room
import okhttp3.OkHttpClient
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.practicum.android.diploma.BuildConfig
import ru.practicum.android.diploma.R
import ru.practicum.android.diploma.data.core.ExternalNavigatorImpl
import ru.practicum.android.diploma.data.database.AppDatabase
import ru.practicum.android.diploma.data.database.converter.ListStringConverter
import ru.practicum.android.diploma.data.network.ApiService
import ru.practicum.android.diploma.data.network.NetworkCheckerImpl
import ru.practicum.android.diploma.data.network.NetworkClient
import ru.practicum.android.diploma.data.network.RetrofitClient
import ru.practicum.android.diploma.data.repositoryimpl.VacancyRepositoryImpl
import ru.practicum.android.diploma.data.team.impl.TeamRepositoryImpl
import ru.practicum.android.diploma.domain.NetworkChecker
import ru.practicum.android.diploma.domain.api.VacancyRepository
import ru.practicum.android.diploma.domain.core.repository.ExternalNavigator
import ru.practicum.android.diploma.domain.team.model.Developer
import ru.practicum.android.diploma.domain.team.repository.TeamRepository
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://practicum-diploma-8bc38133faba.herokuapp.com/"
private const val NETWORK_TIMEOUT_SEC = 5L

val dataModule = module {
    single<NetworkChecker> { NetworkCheckerImpl(androidContext()) }

    single<ApiService> {
        val okHttpClient =
            OkHttpClient.Builder()
                .addInterceptor { chain ->
                    chain.proceed(
                        chain.request().newBuilder()
                            .addHeader("Authorization", BuildConfig.API_ACCESS_TOKEN)
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

    single<NetworkClient> { RetrofitClient(get(), get()) }

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

    single<VacancyRepository> { VacancyRepositoryImpl(get()) }
}
