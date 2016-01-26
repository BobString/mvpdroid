package com.robertmm.mvpdroid.utils;

import java.util.List;

/**
 * Created by roberto on 1/26/16.
 */
public interface Callback<T> {
    void success(T element);
    void failure(String string);
}
