package com.example.myrecipes.di

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myrecipes.data.database.AppDataBase
import com.example.myrecipes.util.DB_NAME
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Singleton
    @Provides
    fun provideRoom(@ApplicationContext context: Context) =
        Room.databaseBuilder(context, AppDataBase::class.java, DB_NAME).allowMainThreadQueries()
            .setJournalMode(RoomDatabase.JournalMode.TRUNCATE).build()

    @Singleton
    @Provides
    fun provideRecipeDao(db: AppDataBase) = db.recipeDao()

    @Singleton
    @Provides
    fun provideAuthorDao(db: AppDataBase) = db.autorDao()
}