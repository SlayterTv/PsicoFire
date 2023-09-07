package com.slaytertv.psicofire.di

import android.content.Context
import android.content.SharedPreferences
import com.google.gson.Gson
import com.slaytertv.psicofire.util.SharedPrefConstants
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object AppModule {
    //para usar shared preference
    @Provides
    @Singleton
    fun provideSharedPref(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences(SharedPrefConstants.LOCAL_SHARED_PREF, Context.MODE_PRIVATE)
    }
    //gson
    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson()
    }

}