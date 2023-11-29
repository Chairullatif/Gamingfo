package com.chairullatif.gamingfo.core.di

import androidx.room.Room
import com.chairullatif.gamingfo.core.BuildConfig
import com.chairullatif.gamingfo.core.data.source.local.LocalDataSource
import com.chairullatif.gamingfo.core.data.source.local.room.GameDatabase
import com.chairullatif.gamingfo.core.data.source.remote.RemoteDataSource
import com.chairullatif.gamingfo.core.data.source.remote.network.ApiService
import com.chairullatif.gamingfo.core.domain.repository.IGameRepository
import com.chairullatif.gamingfo.core.utils.AppExecutors
import net.sqlcipher.database.SQLiteDatabase
import net.sqlcipher.database.SupportFactory
import okhttp3.CertificatePinner
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<GameDatabase>().gameDao() }
    single {
        val passphrase: ByteArray = SQLiteDatabase.getBytes("gamingfo".toCharArray())
        val factory = SupportFactory(passphrase)
        Room.databaseBuilder(
            androidContext(),
            GameDatabase::class.java, "Game"
        ).fallbackToDestructiveMigration()
            .openHelperFactory(factory)
            .build()
    }
}
val loggingInterceptor = if (BuildConfig.DEBUG) {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
} else {
    HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.NONE)
}

val networkModule = module {
    single {
        val hostname = "rawg.io"
        val certificatePinner = CertificatePinner.Builder()
            .add(hostname, "sha256/o/TPHqfOxqUVvhHmuaef0sC3tHur5b1L3XU/fDFwHJQ=")
            .build()
        OkHttpClient.Builder()
            .addInterceptor(loggingInterceptor)
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .certificatePinner(certificatePinner)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    factory { AppExecutors() }
    single<IGameRepository> {
        com.chairullatif.gamingfo.core.data.GameRepository(
            get(),
            get(),
            get()
        )
    }
}
