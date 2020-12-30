package com.reactnativeanalogonesnap

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.provider.MediaStore
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.startActivityForResult
import androidx.core.content.ContextCompat
import com.facebook.react.bridge.*
import java.util.*

/**
 * @author Saad Asad
 * AnalogoneSnapModule
 * A subclass of ReactContextBaseJavaModule that implements the functionality required by the JavaScript
 *
 */
class AnalogoneSnapModule(reactContext: ReactApplicationContext) : ReactContextBaseJavaModule(reactContext) {

    /**
     * Required override function to return the module name which represents this class in JavaScript
     * @param
     * @return name:String
     * */
    override fun getName(): String {
        return "AnalogoneSnap"
    }

    /**
     * A function that takes three parameters with different types and returns all three with same types with promise resolve
     * @param a:Int b:String c:Any
     * @resolves array of all params
     *
     * */
    // To expose a method to JavaScript a Java method must be annotated using @ReactMethod
    @ReactMethod
    fun multiParamFunc(a: Int, b: String, c: Any, promise: Promise) {
      promise.resolve(arrayOf(a, b, c))

    }

    /**
     * returns OS name with a callback
     * @param callback: Callback
     *
     * */
    @ReactMethod
    fun getOS(callback: Callback) {
      callback.invoke(arrayOf("android"))
    }

    /**
     * Override function for exposing Constants from Native module
     * @param
     * @return constants: MutableMap<String, Any>
     *
     * */
    override fun getConstants(): MutableMap<String, Any> {
      val constants: MutableMap<String, Any> = HashMap()
      constants["VERSION"] = 1
      constants["MODULE_NAME"] = "AnalogOne Snap"
      constants["ACTIVE"] = true
      constants["SUPPORTED_OS"] = arrayOf("iOS", "Android")

      return constants
    }


  /**
   * Checks if camera permission is granted. resolves true if granted, fales otherwise. Also requests permission if not granted
   * @param promise: Promise
   *
   */
  @ReactMethod
    fun isCameraPermissionGranted(promise: Promise) {
      val camera = this.currentActivity?.applicationContext?.let {
        ContextCompat.checkSelfPermission(it,Manifest.permission.CAMERA)
      }
      val listPermissionsNeeded: MutableList<String> = ArrayList()
      if (camera != PackageManager.PERMISSION_GRANTED) {
        listPermissionsNeeded.add(Manifest.permission.CAMERA)
      }
      if (!listPermissionsNeeded.isEmpty()) {
        this.currentActivity?.let { ActivityCompat.requestPermissions(it, listPermissionsNeeded.toTypedArray(), 10) }
        promise.resolve(false)
      }
      promise.resolve(true)
    }


  /**
   * Takes a picture from device default camera using Intents
   * @param promise: Promise
   * @resolves promise
   */
  val REQUEST_CODE = 200
    @ReactMethod
    fun takePicture(promise: Promise) {
      val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
      this.currentActivity?.startActivityForResult(cameraIntent, REQUEST_CODE)
      promise.resolve(true)
    }



}
