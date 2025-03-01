import type {TurboModule} from 'react-native';
import {TurboModuleRegistry} from 'react-native';
export interface Spec extends TurboModule {
  // 디바이스 모델명을 반환합니다.
  getDeviceModel(): Promise<string>;
  // 디바이스 IP 주소를 반환합니다.
  getDeviceIpAddress(): Promise<string>;
  // 디바이스 가동 시간을 반환합니다.
  getDeviceUptime(): Promise<string>;
  // 배터리 상태를 반환합니다.
  getBatteryStatus(): Promise<string>;
  // 배터리 잔량을 반환합니다.
  getBatteryLevel(): Promise<string>;
  // 안드로이드 버전을 반환합니다.
  getAndroidVersion(): Promise<string>;
}
export default TurboModuleRegistry.getEnforcing<Spec>('NativeGetDeviceInfo');
