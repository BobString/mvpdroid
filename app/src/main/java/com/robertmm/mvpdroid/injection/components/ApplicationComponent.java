package com.robertmm.mvpdroid.injection.components;

import android.app.Application;

import com.robertmm.mvpdroid.ApplicationClass;
import com.robertmm.mvpdroid.injection.modules.AppModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = AppModule.class)
public interface ApplicationComponent {
    // Field injections of any dependencies of the DemoApplication
    void inject(ApplicationClass application);

    // Exported for child-components.
    Application application();
}
