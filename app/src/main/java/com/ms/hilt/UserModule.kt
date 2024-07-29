package com.ms.hilt

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
class UserModule() {

    @Provides
    fun getUserRepo(): UserRepo {
        return UserRepo()
    }
}
