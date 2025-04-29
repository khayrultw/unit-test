package com.khayrul.mob24.core.di

import com.khayrul.mob24.data.repo.TaskRepo
import com.khayrul.mob24.data.repo.TaskRepoImpl
import com.khayrul.mob24.data.repo.UserRepo
import com.khayrul.mob24.data.repo.UserRepoImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideGreetings(): String {
        return "Hello from hilt2"
    }

    @Provides
    @Singleton
    fun provideTasksRepo(): TaskRepo {
        return TaskRepoImpl()
    }

    @Provides
    @Singleton
    fun provideUserRepo(): UserRepo {
        return UserRepoImpl()
    }
}