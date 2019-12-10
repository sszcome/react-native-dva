import React, {Component} from 'react';
import {StyleSheet, View} from 'react-native';
import {connect} from 'react-redux';

import {Button} from '../components';
import WebView from '../native/ui/WebView';
// import { WebView } from 'react-native-webview';

import {NavigationActions} from '../utils';

@connect()
class Detail extends Component {
  static navigationOptions = {
    title: 'Detail',
  };

  goBack = () => {
    this.props.dispatch(NavigationActions.back({routeName: 'Account'}));
  };

  render() {
    return (
      <View style={styles.container}>
        <WebView style={styles.webViewClass} url={'https://www.baidu.com'} />
        <Button text="Go Back" onPress={this.goBack} />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  webViewClass: {
    flex: 1,
    width: 375,
    height: 200,
  },
});

export default Detail;
