/*
 *     Copyright (c) 2015-present Victor Hidalgo Lorenzo
 *
 *     Licensed under the Apache License, Version 2.0 (the "License");
 *     you may not use this file except in compliance with the License.
 *     You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *     Unless required by applicable law or agreed to in writing, software
 *     distributed under the License is distributed on an "AS IS" BASIS,
 *     WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *     See the License for the specific language governing permissions and
 *     limitations under the License.
 */

package com.example.app.di.modules;

import android.os.Build;
import com.example.app.BuildConfig;
import com.example.data.entity.AutoValueTypeAdapterFactory;
import com.example.data.repository.GithubDataRepository;
import com.example.domain.repository.GithubRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import dagger.Module;
import dagger.Provides;
import javax.inject.Named;
import javax.inject.Singleton;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * A dagger module that provides all other stuff to be used in {@link ApiModule} or {@link DataModule}
 */
@Module(includes = {
    ApiModule.class, DatabaseModule.class,
})
public class DataModule {

  @Provides
  @Singleton
  GithubRepository provideGithubRepository(GithubDataRepository githubDataRepository) {
    return githubDataRepository;
  }

  @Provides
  @Singleton
  @Named("apiUrl")
  String provideApiUrl() {
    return BuildConfig.API_URL;
  }

  @Provides
  @Singleton
  @Named("userAgent")
  String provideUserAgentHeader() {
    return String.format(
        "Android;%s;%s;%d;%s;%s;%d;",
        Build.BRAND,
        Build.MODEL,
        Build.VERSION.SDK_INT,
        BuildConfig.APPLICATION_ID,
        BuildConfig.VERSION_NAME,
        BuildConfig.VERSION_CODE
    );
  }

  @Provides
  @Singleton
  HttpLoggingInterceptor provideApiLogLevel() {
    HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
    if (BuildConfig.DEBUG) {
      interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
    } else {
      interceptor.setLevel(HttpLoggingInterceptor.Level.NONE);
    }
    return interceptor;
  }

  @Provides
  @Singleton
  Gson provideGson() {
    GsonBuilder gsonBuilder = new GsonBuilder();
    gsonBuilder.registerTypeAdapterFactory(new AutoValueTypeAdapterFactory());
    return gsonBuilder.create();
  }
}
