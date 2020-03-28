import React from 'react';
import {
    View
} from 'react-native';
import TodoItem from './ListComponents/TodoItem';
import Category from '../models/Category';

export default TodoList =() => {
    return (
        <View>
            <TodoItem
                id="123"
                title="Test 1"
                category={Category.GREEN}
                completeDate="2020-01-01" />
        </View>
    )
}