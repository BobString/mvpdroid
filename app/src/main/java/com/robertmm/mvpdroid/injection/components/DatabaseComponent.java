package com.robertmm.mvpdroid.injection.components;

import android.app.Application;
import android.content.SharedPreferences;

import com.robertmm.mvpdroid.injection.modules.AppModule;
import com.robertmm.mvpdroid.injection.modules.DatabaseModule;
import com.robertmm.mvpdroid.injection.modules.NetModule;
import com.robertmm.mvpdroid.ui.activities.AlbumListActivity;

import javax.inject.Singleton;

import dagger.Component;
import io.realm.Realm;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = {DatabaseModule.class, AppModule.class})
public interface DatabaseComponent {
    // Exported for child-components.

    SharedPreferences sharedPreferences();

    Realm Realm();

    Application application();
}
