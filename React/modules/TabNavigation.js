import React from 'react';
import StyleSheet from 'react-native';

import { createMaterialTopTabNavigator } from '@react-navigation/material-top-tabs';

import TodoView from './TabComponents/Todo';
import CompletedView from './TabComponents/Completed';
import { NavigationContainer, DarkTheme } from '@react-navigation/native';

const Tabs = createMaterialTopTabNavigator();

export default function TabNavigation(){
    return(
        <NavigationContainer theme={DarkTheme}>
            <Tabs.Navigator>
                <Tabs.Screen name="Todo" component={TodoView} />
                <Tabs.Screen name="Completed" component={CompletedView} />
            </Tabs.Navigator>
        </NavigationContainer>
    );
}