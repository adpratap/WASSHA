package com.noreplypratap.wassha.di

import android.app.Application
import android.content.Context
import com.noreplypratap.wassha.data.local.DatabaseJokes
import com.noreplypratap.wassha.data.local.JokesDAO
import com.noreplypratap.wassha.data.remote.ServiceApi
import com.noreplypratap.wassha.utils.ConstantsAPI
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class Modules {

    @Provides
    @Singleton
    fun provideServiceApi(retrofit: Retrofit) : ServiceApi {
        return retrofit.create(ServiceApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, baseURL: String): Retrofit {
        return Retrofit.Builder().baseUrl(baseURL)
            .addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build()
    }

    @Provides
    @Singleton
    fun provideHttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
        return OkHttpClient.Builder().addInterceptor(loggingInterceptor).build()
    }

    @Provides
    @Singleton
    fun provideDAO(databaseJokes: DatabaseJokes) : JokesDAO {
        return databaseJokes.getJokesDAO()
    }

    @Provides
    @Singleton
    fun provideDatabase(context: Application) : DatabaseJokes {
        return DatabaseJokes.createDatabaseINSTANCE(context)
    }

    @Provides
    fun provideContext(@ApplicationContext application: Context): Context {
        return application
    }

    @Provides
    fun provideBaseURL() : String {
        return ConstantsAPI.BaseURL
    }

}