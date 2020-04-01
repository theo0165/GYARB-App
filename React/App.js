/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 *
 * @format
 * @flow strict-local
 */

import React from 'react';
import {
  SafeAreaView,
  StyleSheet,
  ScrollView,
  View,
  Text,
  StatusBar
} from 'react-native';

import {
  Header,
  LearnMoreLinks,
  Colors,
  DebugInstructions,
  ReloadInstructions,
} from 'react-native/Libraries/NewAppScreen';
import TabNavigation from './modules/TabNavigation';
import FabButtons from './modules/FabButtons';

import { createStackNavigator } from '@react-navigation/stack'
import { NavigationContainer, DarkTheme } from '@react-navigation/native'

import HomeScreen from './screens/HomeScreen';
import SettingsScreen from './screens/SettingsScreen';
import AddItemScreen from './screens/AddItemScreen';

const Stack = createStackNavigator();

const App = () => {
  return (
    <NavigationContainer theme={DarkTheme}>
      <Stack.Navigator>
        <Stack.Screen
          name="Home"
          component={HomeScreen}
          options={{headerShown: false}} />
        
        <Stack.Screen
          name="Settings"
          component={SettingsScreen}
          options={{headerShown: false}} />

        <Stack.Screen
          name="AddItem"
          component={AddItemScreen}
          options={{headerShown:false}} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

const styles = StyleSheet.create({
});


export default App;
