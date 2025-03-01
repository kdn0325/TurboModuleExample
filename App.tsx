import React, {useState, useEffect} from 'react';
import {View, Text, Button, StyleSheet} from 'react-native';
import NativeGetDeviceInfo from './specs/NativeGetDeviceInfo';

const App = () => {
  const [value, setValue] = useState<string | null>('');

  const getBatteryLevel = async () => {
    const data = await NativeGetDeviceInfo?.getBatteryLevel();
    setValue(data ?? '');
  };

  const getDeviceModel = async () => {
    const data = await NativeGetDeviceInfo?.getDeviceModel();
    setValue(data ?? '');
  };

  const getDeviceIpAddress = async () => {
    const data = await NativeGetDeviceInfo?.getDeviceIpAddress();
    setValue(data ?? '');
  };

  const getDeviceUptime = async () => {
    const data = await NativeGetDeviceInfo?.getDeviceUptime();
    setValue(data ?? '');
  };

  const getAndroidVersion = async () => {
    const data = await NativeGetDeviceInfo?.getAndroidVersion();
    setValue(data ?? '');
  };

  useEffect(() => {
    getBatteryLevel();
  }, []);

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{value}</Text>
      <View style={styles.buttonContainer}>
        <Button title={'배터리 잔량 확인'} onPress={getBatteryLevel} />
      </View>
      <View style={styles.buttonContainer}>
        <Button title={'기기 모델 확인'} onPress={getDeviceModel} />
      </View>
      <View style={styles.buttonContainer}>
        <Button title={'IP 주소 확인'} onPress={getDeviceIpAddress} />
      </View>
      <View style={styles.buttonContainer}>
        <Button title={'기기 가동 시간 확인'} onPress={getDeviceUptime} />
      </View>
      <View style={styles.buttonContainer}>
        <Button title={'안드로이드 버전 확인'} onPress={getAndroidVersion} />
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {flex: 1, backgroundColor: '#f5f5f5'},
  title: {fontSize: 24, fontWeight: 'bold', marginBottom: 20},
  taskTitle: {fontSize: 18},
  buttonContainer: {marginBottom: 20},
});

export default App;
