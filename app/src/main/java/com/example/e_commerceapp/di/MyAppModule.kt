package com.example.e_commerceapp.di

import com.example.e_commerceapp.BuildConfig
import com.example.e_commerceapp.transaction_list.data.remote.TransactionListApi
import com.example.e_commerceapp.transaction_list.data.repository.TransactionListRepositoryImpl
import com.example.e_commerceapp.transaction_list.domain.repository.TransactionListRepository
import com.example.e_commerceapp.transaction_list.domain.usecase.GetTransactionListUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.Locale
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object MyAppModule {

    // provide LoggingInterceptor
    @Provides
    fun provideOkHttpLogging(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        }
        return logging
    }

    //provide OkHttpClient
    @Provides
    fun provideOkHttpClient(logging: HttpLoggingInterceptor): OkHttpClient {
        val client =
            OkHttpClient.Builder()
                .addInterceptor(logging)
                .addInterceptor { chain ->
                    val request =
                        chain.request()
                            .newBuilder()
                            .addHeader("lang", Locale.getDefault().language)
                            .build()
                    chain.proceed(request)
                }
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build()
        return client
    }

    // provide retrofit
    @Provides
    @Singleton
    fun provideRetrofit(client: OkHttpClient): Retrofit {

        val retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        return retrofit
    }

    //provide instance TransactionListRepo
    @Provides
    fun provideInstanceTransactionListRepo(retrofit: Retrofit): TransactionListApi {
        return retrofit.create(TransactionListApi::class.java)
    }

    //provide ApiCall for TransactionList
    @Provides
    fun provideApiTransactionListRepo(apiServices: TransactionListApi): TransactionListRepository {
        return TransactionListRepositoryImpl(apiServices)
    }

    // provide repo for Get All transaction useCase
    @Provides
    fun provideTransactionListRepo(repository: TransactionListRepository): GetTransactionListUseCase {
        return GetTransactionListUseCase(repository)
    }
}