package com.murzify.effectivetest.core.shared_prefs

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class PrefsModule {

    @Binds
    abstract fun bindSharedPrefsDataStore(
        sharedPreferencesDataStoreImpl: SharedPreferencesDataStoreImpl
    ): SharedPreferencesDataStore

}