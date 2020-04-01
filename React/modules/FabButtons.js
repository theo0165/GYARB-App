import React from 'react'
import {
    StyleSheet,
    View,
    TouchableOpacity,
    Text
} from 'react-native';

import Icon from 'react-native-vector-icons/FontAwesome';
import { createStackNavigator, createAppContainer } from '@react-navigation/native';


export default FabButtons = ({navigation}) => {
    return (
        <View style={{alignItems: 'baseline'}}>
            <TouchableOpacity
                style={{
                    alignItems:'center',
                    justifyContent:'center',
                    width:55,
                    height:55,
                    position: 'absolute',                                          
                    bottom: 16,                                                    
                    right: 16,
                    backgroundColor:'#424242',
                    borderRadius:100,
                }}
                >
                <Icon name="plus"  size={32} color="#ffffff" onPress={() => {this.props.navigation.navigate("AddItem")}} />
            </TouchableOpacity>

            <TouchableOpacity
                style={{
                    alignItems:'center',
                    justifyContent:'center',
                    width:55,
                    height:55,
                    position: 'absolute',                                          
                    bottom: 16,                                                    
                    left: 16,
                    backgroundColor:'#424242',
                    borderRadius:100,
                }}
                >
                <Icon name="cog"  size={32} color="#ffffff" onPress={() => {this.props.navigation.navigate("Settings")}} />
            </TouchableOpacity>
        </View>
    );
}

const styles = StyleSheet.create({
    fab: {
        padding: 16,
        backgroundColor: "#424242"
    }
})