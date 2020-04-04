package cz.levinzonr.spotistats.injection

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import cz.levinzonr.spotistats.data.BuildConfig
import cz.levinzonr.spotistats.domain.AppConfig
import cz.levinzonr.spotistats.network.Api
import cz.levinzonr.spotistats.util.DateDeserializer
import cz.levinzonr.spotistats.util.ItemTypeAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
internal class RestModule {
    @Provides
    fun provideTypeFactory(): ItemTypeAdapterFactory {
        return ItemTypeAdapterFactory()
    }

    @Provides
    fun provideDateDeserializer(): DateDeserializer {
        return DateDeserializer()
    }

    @Provides
    @Singleton
    fun provideGson(typeFactory: ItemTypeAdapterFactory, dateDeserializer: DateDeserializer): Gson {
        return GsonBuilder()
                .registerTypeAdapterFactory(typeFactory)
                .registerTypeAdapter(Date::class.java, dateDeserializer)
                .setDateFormat(DateDeserializer.DATE_FORMATS[0])
                .create()
    }



    @Provides
    @Singleton
    fun provideGsonConverter(gson: Gson): Converter.Factory {
        return GsonConverterFactory.create(gson)
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
                .connectTimeout(45, TimeUnit.SECONDS)
                .readTimeout(60, TimeUnit.SECONDS)
                .writeTimeout(60, TimeUnit.SECONDS)

        if (BuildConfig.DEBUG) {
            val logging = okhttp3.logging.HttpLoggingInterceptor()
            logging.level = okhttp3.logging.HttpLoggingInterceptor.Level.BODY
            clientBuilder.addInterceptor(logging)
        }

        return clientBuilder.build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(
            client: OkHttpClient,
            converter: Converter.Factory,
            appConfig: AppConfig
    ): Retrofit {
        return Retrofit.Builder()
                .client(client)
                .baseUrl(appConfig.apiUrl)
                .addConverterFactory(converter)
                .build()
    }

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api {
        return retrofit.create(Api::class.java)
    }
}