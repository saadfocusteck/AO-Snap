#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(AnalogoneSnap, NSObject)
RCT_EXTERN_METHOD(multiParamFunc:(float)a withB:(NSString)b withC:(NSDictionary)c withResolver:(RCTPromiseResolveBlock)resolve withRejecter:(RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(takePicture: (RCTPromiseResolveBlock)resolve rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(isCameraPermissionGranted: (RCTPromiseResolveBlock)resolve rejecter: (RCTPromiseRejectBlock)reject)
RCT_EXTERN_METHOD(getOS: (RCTResponseSenderBlock)callback)
@end
