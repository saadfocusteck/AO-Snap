package com.reactnativeanalogonesnap

import com.facebook.react.ReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.uimanager.ViewManager

/**
 * @author Saad Asad
 * This class provides a list of functionalities and UI components for usage in React Native.
 */
class AnalogoneSnapPackage : ReactPackage {

    /**
     * returns a list of instances of NativeModule class, which defines functions that can be called from JavaScript
     * @param reactContext: ReactApplicationContext
     * @return List<NativeModule>
     */
    override fun createNativeModules(reactContext: ReactApplicationContext): List<NativeModule> {
        return listOf(AnalogoneSnapModule(reactContext))
    }

    /**
     * this function returns a list of Native UI components, instances of ViewManager.
     */
    override fun createViewManagers(reactContext: ReactApplicationContext): List<ViewManager<*, *>> {
        return emptyList()
    }
}
