import React from 'react'
import {
    View,
    TouchableOpacity,
} from 'react-native';

import Icon from 'react-native-vector-icons/FontAwesome';


export default CompleteButton = (props) => {
    return (
        <View>
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

                onPress={() => {props.pressed()}}
                >
                <Icon name="check"  size={32} color="#ffffff" />
            </TouchableOpacity>
        </View>
    );
}