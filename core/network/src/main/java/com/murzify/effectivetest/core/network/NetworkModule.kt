package com.murzify.effectivetest.core.network

import com.murzify.effectivetest.core.network.fake.FakeNetworkDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class NetworkModule {

    @Binds
    abstract fun bindNetworkDataSource(
        fakeNetworkDataSource: FakeNetworkDataSource
    ): NetworkDataSource

}