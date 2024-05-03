package com.example.dailyexpendituretracker.di

import android.app.Application
import android.content.Context
import androidx.room.Room
import com.example.dailyexpendituretracker.data.db.ExpenseDao
import com.example.dailyexpendituretracker.data.db.ExpenseDatabase
import com.example.dailyexpendituretracker.data.repository.ExpenseRepositoryImpl
import com.example.dailyexpendituretracker.domain.repository.ExpenseRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideContext(application: Application): Context {
        return application.applicationContext
    }

    @Singleton
    @Provides
    fun provideExpenseDatabase(@ApplicationContext context: Context): ExpenseDatabase {
        return Room.databaseBuilder(
            context,
            ExpenseDatabase::class.java,
            "expense.db"
        ).build()
    }

    @Provides
    fun provideExpenseDao(expenseDatabase: ExpenseDatabase) = expenseDatabase.expenseDao

    @Provides
    fun expenseRepository(expenseDao: ExpenseDao): ExpenseRepository {
        return ExpenseRepositoryImpl(expenseDao)
    }
}