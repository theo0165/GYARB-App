import React from 'react';
import {
    View
} from 'react-native';
import CompletedItem from './ListComponents/CompletedItem'
import Category from '../models/Category';
import { FlatList } from 'react-native-gesture-handler';

const DATA = [
    {
        "id": "123",
        "title": "Test 1",
        "category": Category.GREEN,
        "completeDate": "2020-01-01"
    },
    {
        "id": "321",
        "title": "Test 2",
        "category": Category.RED,
        "completeDate": null
    },
    {
        "id": "312",
        "title": "Test 3",
        "category": Category.ORANGE,
        "completeDate": "2020-01-01"
    },
    {
        "id": "132",
        "title": "Test 4",
        "category": Category.BLUE,
        "completeDate": "2020-01-01"
    }
]

export default TodoList =() => {
    return (
        <FlatList
            data={DATA}
            renderItem={({item}) => <CompletedItem id={item.id} title={item.title} category={item.category} completeDate={item.completeDate} />} 
            keyExtractor={item => item.id}
        />
    )
}