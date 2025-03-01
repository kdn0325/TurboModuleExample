package com.nativegetdeviceinfo

import android.content.Context
import android.os.BatteryManager
import android.os.Build
import android.os.SystemClock
import android.net.wifi.WifiManager
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.text.format.Formatter
import com.facebook.react.bridge.Promise
import com.facebook.react.bridge.ReactApplicationContext
import com.nativegetdeviceinfo.NativeGetDeviceInfoSpec

class NativeGetDeviceInfoModule(reactContext: ReactApplicationContext) : NativeGetDeviceInfoSpec(reactContext) {
  companion object {
      const val NAME = "NativeGetDeviceInfo"
  }
    // 안드로이드 버전 가져오기
    override fun getAndroidVersion(promise: Promise) {
        val androidVersion = Build.VERSION.RELEASE
        promise.resolve("안드로이드 $androidVersion")
    }

    // 디바이스 모델을 가져오는 메소드
    override fun getDeviceModel(promise: Promise) {
        val manufacturer = Build.MANUFACTURER
        val model = Build.MODEL
        promise.resolve("$manufacturer $model")
    }

    // 디바이스 IP 주소를 가져오는 메소드
    override fun getDeviceIpAddress(promise: Promise) {
        try {
            val connectivityManager = getReactApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val network = connectivityManager.activeNetwork
            val networkCapabilities = connectivityManager.getNetworkCapabilities(network)

            val ipAddress = when {
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) == true -> {
                    val wifiManager = getReactApplicationContext().getSystemService(Context.WIFI_SERVICE) as WifiManager
                    val wifiInfo = wifiManager.connectionInfo
                    Formatter.formatIpAddress(wifiInfo.ipAddress)
                }
                networkCapabilities?.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) == true -> "셀룰러 네트워크 IP를 확인할 수 없습니다"
                else -> "알 수 없음"
            }
            promise.resolve(ipAddress)
        } catch (e: Exception) {
            promise.reject("IP_ERROR", "IP 주소를 가져올 수 없습니다: ${e.message}")
        }
    }

    // 기기의 가동시간을 가져오는 메소드
    override fun getDeviceUptime(promise: Promise) {
        val uptimeMillis = SystemClock.uptimeMillis() // 기기 가동 시간 (밀리초)
        val uptimeSeconds = uptimeMillis / 1000
        val hours = uptimeSeconds / 3600
        val minutes = (uptimeSeconds % 3600) / 60
        val seconds = uptimeSeconds % 60
        promise.resolve("$hours 시간, $minutes 분, $seconds 초")
    }

    // 배터리 충전 상태를 가져오는 메소드
    override fun getBatteryStatus(promise: Promise) {
        try {
            val batteryManager = getReactApplicationContext().getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            val isCharging = batteryManager.isCharging
            promise.resolve(if (isCharging) "충전 중" else "충전 중 아님")
        } catch (e: Exception) {
            promise.reject("BATTERY_STATUS_ERROR", "배터리 상태를 가져올 수 없습니다: ${e.message}")
        }
    }

    // 배터리 수준을 가져오는 메소드
    override fun getBatteryLevel(promise: Promise) {
        try {
            val batteryManager = getReactApplicationContext().getSystemService(Context.BATTERY_SERVICE) as BatteryManager
            val level = batteryManager.getIntProperty(BatteryManager.BATTERY_PROPERTY_CAPACITY)
            promise.resolve("$level%")
        } catch (e: Exception) {
            promise.reject("BATTERY_LEVEL_ERROR", "배터리 잔량을 가져올 수 없습니다: ${e.message}")
        }
    }
}
