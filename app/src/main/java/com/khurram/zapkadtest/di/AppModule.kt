package com.khurram.zapkadtest.di


import android.content.Context
import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.khurram.zapkadtest.data.db.UserDatabase
import com.khurram.zapkadtest.data.network.APIsInterface
import com.khurram.zapkadtest.data.repository.database.DatabaseRepository
import com.khurram.zapkadtest.data.repository.database.DatabaseRepositoryImpl
import com.khurram.zapkadtest.data.repository.network.NetworkRepository
import com.khurram.zapkadtest.data.repository.network.NetworkRepositoryImpl
import com.khurram.zapkadtest.util.NetworkStatusTracker
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object AppModule {


    private const val BASE_URL= "https://api.github.com/"

    private val interceptor: HttpLoggingInterceptor = HttpLoggingInterceptor().setLevel(
        HttpLoggingInterceptor.Level.BODY
    )

    private val okHttpClient = OkHttpClient.Builder()
        .readTimeout(160, TimeUnit.SECONDS)
        .connectTimeout(160, TimeUnit.SECONDS)
        .addInterceptor(interceptor)
        .build()

    @Provides
    @Singleton
    fun providesGsonBuilder(): Gson {
        return GsonBuilder().create()
    }

    @Provides
    @Singleton
    fun providesRetrofit(gson: Gson): Retrofit.Builder {
            return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
    }

    @Provides
    @Singleton
    fun providesNetworkAPI(retrofit: Retrofit.Builder): APIsInterface {
        return retrofit.build().create(APIsInterface::class.java)
    }

    @Provides
    @Singleton
    fun providesNetworkRepository(apIsInterface: APIsInterface,database: UserDatabase): NetworkRepository {
        return NetworkRepositoryImpl(apIsInterface,database)
    }

    @Provides
    @Singleton
    fun providesDatabaseRepository(database: UserDatabase): DatabaseRepository {
        return DatabaseRepositoryImpl(database)
    }

    @Provides
    @Singleton
    fun providesNetworkStatusTracker(@ApplicationContext context: Context): NetworkStatusTracker {
        return NetworkStatusTracker(context)
    }

    @Provides
    @Singleton
    fun provideUserDatabase(@ApplicationContext context: Context) = Room.databaseBuilder(
        context.applicationContext,
        UserDatabase::class.java,
        "users_db")
        .fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideCoroutineDispatcher() = Dispatchers.IO

//    @Provides
//    fun provideStringResources(@ApplicationContext context: Context) = StringResources(context)

}