import React from 'react';
import { StyleSheet, View } from 'react-native';

import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';

import TodoView from './TabComponents/Todo';
import CompletedView from './TabComponents/Completed';
import { NavigationContainer, DarkTheme } from '@react-navigation/native';

const Tabs = createMaterialTopTabNavigator();

export default function TabNavigation(){
    return(
        <Tabs.Navigator>
            <Tabs.Screen name="Todo" component={TodoView} />
            <Tabs.Screen name="Completed" component={CompletedView} />
        </Tabs.Navigator>
    );
}