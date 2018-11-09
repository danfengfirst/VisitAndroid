/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow
 */

import React, {Component} from 'react';
import {Platform, StyleSheet, Text, View, NativeModules, DeviceEventEmitter} from 'react-native';
import NativeTimerView from "./src/component/NativeTimerView";

const instructions = Platform.select({
  ios: 'Press Cmd+R to reload,\n' + 'Cmd+D or shake for dev menu',
  android:
  'Double tap R on your keyboard to reload,\n' +
  'Shake or press menu button for dev menu',
});

type Props = {};
export default class App extends Component<Props> {
  constructor() {
    super();
    this.state = {
      name: 'To get started, edit App.js',
      sendEvent: 'Send Event'
    }
  }

  toast = () => {
    let obj = {
      id: 1,
      name: "xiaohong"
    };
    NativeModules.TestModules.Toasts(obj);
  };
  toastFinishCallback = () => {
    NativeModules.TestModules.ToastFinished("调用了原生并带有回调", (result) => {
      this.setState({
        name: result.name
      });
    });
  };

  sendEvent = () => {
    NativeModules.TestModules.sendEvent();
  };

  componentWillMount() {
    DeviceEventEmitter.addListener('sendEvent', (e: Event) => {
      this.setState({
        sendEvent: e.name
      });
    })
  };

  startActivity = () => {
    NativeModules.TestModules.startActivity();
  };

  render() {
    return (
      <View style={styles.container}>
        <Text style={styles.welcome}
              onPress={this.toast}
        >Welcome to React Native!</Text>
        <Text style={styles.instructions}
              onPress={this.toastFinishCallback}
        >{this.state.name}</Text>
        <Text style={styles.instructions}
              onPress={this.sendEvent}
        >{this.state.sendEvent}</Text>

        <Text
          style={styles.instructions}
          onPress={this.startActivity}
        >点击开启activity</Text>

        <NativeTimerView
          style={{width: 100, height: 50, borderRadius: 20}}
          stimer={60}
        />
      </View>
    );
  }
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#F5FCFF',
  },
  welcome: {
    fontSize: 20,
    textAlign: 'center',
    margin: 10,
  },
  instructions: {
    textAlign: 'center',
    color: '#333333',
    marginBottom: 5,
  },
});
