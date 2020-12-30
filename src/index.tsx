import { NativeModules } from 'react-native';

type AnalogoneSnapType = {
  multiParamFunc(a: number, b: string, c: object): Promise<Array<any>>;

  takePicture(): Promise<boolean>;
  checkCameraPermission(): Promise<boolean>;
  isCameraPermissionGranted(): Promise<boolean>;
  getOS(callback: Function): void;
  getConstants(): object;
};

const { AnalogoneSnap } = NativeModules;

export default AnalogoneSnap as AnalogoneSnapType;
