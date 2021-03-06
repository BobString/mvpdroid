package com.robertmm.mvpdroid.presenters;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by roberto on 1/26/16.
 */

public class PresenterHolder {
    static volatile PresenterHolder singleton = null;

    private Map<Class, Presenter> presenterMap;


    public static PresenterHolder getInstance() {
        if (singleton == null) {
            synchronized (PresenterHolder.class) {
                if (singleton == null) {
                    singleton = new PresenterHolder();
                }
            }
        }
        return singleton;
    }

    private PresenterHolder() {
        this.presenterMap = new HashMap<>();
    }

    public void putPresenter(Class c, Presenter p) {
        presenterMap.put(c, p);
    }

    public <T extends Presenter> T getPresenter(Class<T> c) {
        return (T) presenterMap.get(c);
    }

    public void remove(Class c) {
        presenterMap.remove(c);
    }
}