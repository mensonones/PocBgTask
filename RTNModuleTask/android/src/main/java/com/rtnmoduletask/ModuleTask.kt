package com.rtnmoduletask;

import com.facebook.react.bridge.Promise;
import com.facebook.react.bridge.ReactApplicationContext;
import com.rtnmoduletask.NativeModuleTaskSpec;
import android.os.Build;
import android.content.Intent;
import android.util.Log;

class ModuleTask(private val reactContext: ReactApplicationContext) : NativeModuleTaskSpec(reactContext) {

  override fun getName() = NAME

  override fun getAndroidVersion(promise: Promise) {
    Log.d("ModuleTask", "getAndroidVersion: " + Build.VERSION.RELEASE)
    promise.resolve(Build.VERSION.RELEASE)
  }

  override fun startTask(promise: Promise) {
    val serviceIntent = Intent(getReactApplicationContext(), ExampleService::class.java)

    try {
      if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        Log.d("ModuleTask", "Starting service in foreground")
        getReactApplicationContext().startForegroundService(serviceIntent)
      } else {
        Log.d("ModuleTask", "Starting service in background")
        getReactApplicationContext().startService(serviceIntent)
      }
      Log.d("ModuleTask", "Service started")
      promise.resolve("Service started")
    } catch (e: Exception) {
      promise.reject("E_SERVICE_ERROR", e)
    }
  }
  
  override fun stopTask(promise: Promise) {
    val serviceIntent = Intent(getReactApplicationContext(), ExampleService::class.java)
    try {
      getReactApplicationContext().stopService(serviceIntent)
      Log.d("ModuleTask", "Service stopped")
      promise.resolve("Service stopped")
    } catch (e: Exception) {
      promise.reject("E_SERVICE_ERROR", e)
    }
  }

  companion object {
    const val NAME = "RTNModuleTask"
  }
}