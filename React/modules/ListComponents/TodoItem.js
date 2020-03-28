import React from 'react'
import {
    View,
    CheckBox,
    Text,
    StyleSheet} from 'react-native'

export default TodoItem = (props) => {
    return (
        <View>
            <View>
                <CheckBox
                    checked='false'
                    onPress={() => {

                    }}
                />
                <Text style={styles.title}>{props.title}</Text>
            </View>
            <View>
                <Text style={}>{(props.completeDate != null) ? "" : props.completeDate}</Text>
            </View>
            <View>
                <View
                    style={[styles.categoryBox, {backgroundColor: props.category.color}]}
                    />
            </View>
        </View>
    );
}

const styles = StyleSheet.create({
    title: {
    },
    categoryBox: {
        width: 30,
        height: 30,
        borderWidth: 2,
        borderColor: "#ffffff"
    }
})

/*

[] TITLE
                    Category
   CompleteDate

*/