package com.slaytertv.psicofire.di

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object FireabaseModul {
    //para usar firestore
    @Provides
    @Singleton
    fun provideFireStoreinstance(): FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }
    //para usar auth se usa junto con repositoeymodule
    @Provides
    @Singleton
    fun provideFirebaseAuthInstance(): FirebaseAuth {
        return FirebaseAuth.getInstance()
    }
    //para usar realtimedatabase desde repository modle
    @Provides
    @Singleton
    fun provideFirebaseDatabaseInstance(): FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }
}