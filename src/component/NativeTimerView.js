/*
 * Created by Danfeng on 2018/11/8
 *
 */

import React, {Component} from 'react';
import PropTypes from 'prop-types';
import {
  requireNativeComponent,
  View
} from 'react-native';


const AndroidTimerView = requireNativeComponent('TimerView', NativeTimerView);
export default class NativeTimerView extends Component<Props> {
  static propTypes = {
    stimer: PropTypes.number,
  };

  render() {
    return <AndroidTimerView
      style={{width: 100, height: 50, borderRadius: 20}}
      start={this.props.stimer}
    />
  }
}

