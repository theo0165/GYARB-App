import React from 'react'
import {
    View,
    Text,
    StyleSheet
} from 'react-native'

import CheckBox from '@react-native-community/checkbox';
import Category from '../../models/Category';

export default TodoItem = (props) => {
    return (
        <View
            style={{
                paddingBottom: 0,
                paddingTop: 20,
                paddingLeft: 20,
                paddingRight: 20
            }}>
            <View
                style={{
                    flexDirection: "row",
                    alignItems: "center",
                    justifyContent: "space-between"
                }}>
                <View
                    style={{
                        flexDirection: "row",
                        alignItems: "flex-start"
                    }}>
                    <CheckBox
                        value={false}
                        tintColors={{
                            "true": "#FFFFFF",
                            "false": "#ffffff"
                        }}
                        onChange={() => {
                            
                        }}
                    />
                    <Text style={[styles.title, {maxWidth: 280}]}>{props.title}</Text>
                </View>
                <View
                    style={[(props.category == Category.NONE) ? styles.categoryBoxNone : styles.categoryBox, {backgroundColor: props.category.color}]} />
            </View>

            <View>
                <Text 
                    style={[styles.completeDate, {
                        paddingLeft: 40
                    }]}>
                    {(props.completeDate != null) ? props.completeDate : ""}
                </Text>
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    title: {
        fontSize: 25,
        fontWeight: "700",
        paddingLeft: 10
    },
    completeDate: {
        color: '#6d6d6d'
    },
    categoryBox: {
        width: 35,
        height: 30,
        borderWidth: 2,
        borderColor: "#ffffff"
    },
    categoryBoxNone: {
        width: 35,
        height: 30,
        borderWidth: 0
    }
})