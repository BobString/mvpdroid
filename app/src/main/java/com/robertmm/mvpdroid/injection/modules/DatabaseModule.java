package com.robertmm.mvpdroid.injection.modules;

import android.app.Application;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.realm.Realm;

@Module
public class DatabaseModule {

    // Constructor needs one parameter to instantiate.
    public DatabaseModule() {
    }

    // Dagger will only look for methods annotated with @Provides
    @Provides
    @Singleton
    SharedPreferences providesSharedPreferences(Application application) {
        return PreferenceManager.getDefaultSharedPreferences(application);
    }

    @Provides
    @Singleton
    Realm provideRealm() {
        return Realm.getDefaultInstance();
    }

}
