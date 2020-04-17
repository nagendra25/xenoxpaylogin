package com.xenox.loginpage;

import androidx.annotation.NonNull;

public interface RevealCourtPlaceCallbacks {

    void onSuccess(@NonNull String value);

    void onError(@NonNull Throwable throwable);
}
