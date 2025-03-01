// NativeGetDeviceInfoPackage 클래스는 NativeGetDeviceInfoModule을 Turbo 네이티브 모듈로 통합하기 위한 커스텀 React Native 패키지를 정의

package com.nativegetdeviceinfo

import com.facebook.react.TurboReactPackage
import com.facebook.react.bridge.NativeModule
import com.facebook.react.bridge.ReactApplicationContext
import com.facebook.react.module.model.ReactModuleInfo
import com.facebook.react.module.model.ReactModuleInfoProvider

class NativeGetDeviceInfoPackage : TurboReactPackage() {

    // getModule 메서드는 요청된 모듈 이름이 NativeGetDeviceInfoModule.NAME과 일치하는지 확인하고, 일치하면 모듈의 인스턴스를 반환하며, 그렇지 않으면 null을 반환합니다. 

  override fun getModule(name: String, reactContext: ReactApplicationContext): NativeModule? =
    if (name == NativeGetDeviceInfoModule.NAME) {
      NativeGetDeviceInfoModule(reactContext)
    } else {
      null
    }

    // getReactModuleInfoProvider 메서드는 ReactModuleInfo 객체를 생성하여 모듈에 대한 메타데이터를 제공합니다. 이를 통해 모듈이 React Native 프레임워크에 올바르게 등록되고 인식되도록 보장합니다.

  override fun getReactModuleInfoProvider() = ReactModuleInfoProvider {
    mapOf(
      NativeGetDeviceInfoModule.NAME to ReactModuleInfo(
        _name = NativeGetDeviceInfoModule.NAME,
        _className = NativeGetDeviceInfoModule.NAME,
        _canOverrideExistingModule = false,
        _needsEagerInit = false,
        isCxxModule = false,
        isTurboModule = true
      )
    )
  }
}