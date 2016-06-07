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

package com.example.app;

import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.StrictMode;
import android.util.Log;
import cat.ereza.customactivityoncrash.CustomActivityOnCrash;
import com.example.app.di.components.AppComponent;
import com.example.app.di.components.DaggerAppComponent;
import com.example.app.di.modules.AppModule;
import com.facebook.stetho.Stetho;
import com.squareup.leakcanary.LeakCanary;
import timber.log.Timber;

/**
 * Android Main Application
 */
public class AndroidApp extends Application {

  private AppComponent appComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    initializeInjector();
    initCrashActivity();
    installLeakCanary();

    if (BuildConfig.DEBUG) {

      initTimber(new Timber.DebugTree());
      enabledStrictMode();
      initStetho();
    } else {

      initTimber(new CrashReportingTree());
      initCrashReports();
    }
  }

  private void initializeInjector() {
    appComponent = DaggerAppComponent.builder().appModule(new AppModule(this)).build();
  }

  public static AndroidApp get(Context context) {
    return (AndroidApp) context.getApplicationContext();
  }

  public AppComponent getComponent() {
    return appComponent;
  }

  private void initCrashActivity() {
    CustomActivityOnCrash.setShowErrorDetails(false);
    CustomActivityOnCrash.install(this);
  }

  private void enabledStrictMode() {
    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
      StrictMode.setVmPolicy(
          new StrictMode.VmPolicy.Builder().detectAll().penaltyLog().build()
      );
      StrictMode.setThreadPolicy(
          new StrictMode.ThreadPolicy.Builder().detectAll().penaltyLog().penaltyDeathOnNetwork().build()
      );
    }
  }

  private void initCrashReports() {
/*    final Fabric fabric = new Fabric.Builder(this)
        .kits(new Crashlytics())
        .debuggable(BuildConfig.DEBUG)
        .build();
    Fabric.with(fabric);*/
  }

  private void installLeakCanary() {
    LeakCanary.install(this);
  }

  private void initStetho() {
    Stetho.initialize(
        Stetho.newInitializerBuilder(this)
            .enableDumpapp(Stetho.defaultDumperPluginsProvider(this))
            .enableWebKitInspector(Stetho.defaultInspectorModulesProvider(this))
            .build()
    );
  }

  private void initTimber(Timber.Tree tree) {
    Timber.plant(tree);
  }

  /** A tree which logs important information for crash reporting. */
  private static class CrashReportingTree extends Timber.Tree {
    @Override
    protected void log(int priority, String tag, String message, Throwable t) {
      if (priority == Log.VERBOSE || priority == Log.DEBUG) {
        return;
      }

      //FakeCrashLibrary.log(priority, tag, message);

      if (t != null) {
        if (priority == Log.ERROR) {
          //FakeCrashLibrary.logError(t);
        } else if (priority == Log.WARN) {
          //FakeCrashLibrary.logWarning(t);
        }
      }
    }
  }
}