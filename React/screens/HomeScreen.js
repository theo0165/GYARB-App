import React, { Component } from 'react';
import { Button, View, Text } from 'react-native';
import { createStackNavigator, createAppContainer } from '@react-navigation/native';

import TabNavigation from '../modules/TabNavigation';
import FabButtons from '../modules/FabButtons';

export default HomeScreen = ({navigation}) => {
    return (
        <View style={{alignSelf: 'baseline'}}>
          <TabNavigation />
        </View>
    )
}