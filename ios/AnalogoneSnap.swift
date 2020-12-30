import AVFoundation
import UIKit

@objc(AnalogoneSnap)
class AnalogoneSnap: NSObject, UINavigationControllerDelegate, UIImagePickerControllerDelegate {

    /**
     * A function that takes three parameters with different types and returns all three with same types with promise resolve
     * @param a: Float, b: NSString, c: NSDictionary
     * @resolves array of all params
     *
     * */
    @objc(multiParamFunc:withB:withC:withResolver:withRejecter:)
    func multiParamFunc(a: Float, b: NSString, c: NSDictionary, resolve:RCTPromiseResolveBlock,reject:RCTPromiseRejectBlock) -> Void {
        resolve([a,b,c])
    }

    /**
    * Takes a picture from device default camera
    * @param 
    * @resolves promise
    */
    @objc 
    func takePicture( _ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock ) -> Void {
        let vc = UIImagePickerController()
        vc.sourceType = .camera
        vc.allowsEditing = true
        vc.delegate = self
        vc.present(vc, animated: true)
        resolve(true)
    }
    
    func imagePickerController(_ picker: UIImagePickerController, didFinishPickingMediaWithInfo info: [UIImagePickerController.InfoKey : Any]) {
        picker.dismiss(animated: true)
        
        guard let image = info[.editedImage] as? UIImage else {
            print("No image found")
            return
        }
        
        print(image.size)
    }
    
    /**
    * Checks if camera permission is granted. resolves true if granted, fales otherwise. Also requests permission if not granted
    * @param promise: Promise
    *
    */
    @objc 
    func isCameraPermissionGranted( _ resolve: @escaping RCTPromiseResolveBlock, rejecter reject: @escaping RCTPromiseRejectBlock ) -> Void {
        AVCaptureDevice.requestAccess(for: AVMediaType.video) { response in
            if response {
                resolve(true)
            } else {
                resolve(false)
            }
        }
    } 

    /**
     * returns OS name with a callback
     * @param callback: RCTResponseSenderBlock
     *
     * */
    @objc
    func getOS(_ callback: RCTResponseSenderBlock) {
        callback(["iOS"])
    }

    /**
     * Override function for exposing Constants from Native module
     * @param
     * @return constants: AnyHashable:Any
     *
     * */
    @objc
    func constantsToExport() -> [AnyHashable : Any]! {
    return [
          "VERSION": 1,
          "MODULE_NAME": "AnalogOne Snap",
          "ACTIVE": true,
          "SUPPORTED_OS": ["iOS", "Android"]
        ]
  }
}
