/**
 * @format
 */

import {AppRegistry} from 'react-native';
import App from './App';
import {name as appName} from './app.json';

const ExampleTask = async () => {
    console.log('ExampleTask');
}

AppRegistry.registerComponent(appName, () => App);
AppRegistry.registerHeadlessTask('Example', () =>  ExampleTask);
