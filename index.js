import {AppRegistry} from 'react-native';
import 'react-native-gesture-handler';
import App from './app/index';
import {name} from './app.json';

AppRegistry.registerComponent(name, () => App);

console.ignoredYellowBox = [
  'Warning: componentWillMount is deprecated',
  'Warning: componentWillReceiveProps is deprecated',
  'Warning: componentWillUpdate is deprecated',
  'Warning: isMounted(...) is deprecated',
];
