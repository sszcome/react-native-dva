
import { DeviceEventEmitter, NativeModules, Platform } from 'react-native';

const {SmsHelperModule:Module} = NativeModules;

const EVEN_LISTENER = 'com.first.app:SmsEvent';

const SmsHelperModule = (Platform.OS === "ios") ? null :{
    startSmsObserver:Module.startSmsObserver,
    stopSmsObserver:Module.stopSmsObserver,
    addSmsListener: (callback) => DeviceEventEmitter.addListener(EVEN_LISTENER, callback),
    removeSmsListener: () => DeviceEventEmitter.removeAllListeners(EVEN_LISTENER)
} ;
// public String getName()中返回的字符串
export default SmsHelperModule;
