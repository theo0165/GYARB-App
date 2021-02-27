import React, { Component, useState } from 'react';
import { Button, View, Text, StyleSheet } from 'react-native';
import { createStackNavigator, createAppContainer } from '@react-navigation/native';
import { TextInput, TouchableOpacity, TouchableWithoutFeedback } from 'react-native-gesture-handler';
import DatePickerTime from '@react-native-community/datetimepicker';
import CheckBox from '@react-native-community/checkbox';
import Icon from 'react-native-vector-icons/FontAwesome';
import CompleteButton from './CompleteButton'
import Category from '../models/Category';

export default AddItemScreen = () => {
	const [showDatePicker, setShow] = useState(false);
	const [dateButtonShow, setDateButtonShow] = useState({})

	const [catNone, setNoneCategory] = useState(true);
	const [catGreen, setGreenCategory] = useState(false);
	const [catRed, setRedCategory] = useState(false);
	const [catOrange, setOrangeCategory] = useState(false);
	const [catBlue, setBlueCategory] = useState(false);

	const [title, setTitle] = useState("");
	const [deadline, setDeadline] = useState("");
	const [category, setCategory] = useState(Category.NONE)

	const dateOnChange = (e, selectedDate) => {
		console.log("DATE CHANGED")
		console.log(selectedDate)
		setShow(false)
	}

	const noDeadlineButton = (value) => {
		console.log(value)
		setDateButtonShow(!value);
		setDeadline(!value);
	}

	const completePressed = () => {

	}

	const setActive = (which) => {
		switch(which){
			case "none":
				setNoneCategory(true);
				setGreenCategory(false);
				setRedCategory(false);
				setOrangeCategory(false);
				setBlueCategory(false);

				setCategory(Category.NONE);
				break;
			case "green":
				setNoneCategory(false);
				setGreenCategory(true);
				setRedCategory(false);
				setOrangeCategory(false);
				setBlueCategory(false);

				setCategory(Category.GREEN);
				break;
			case "red":
				setNoneCategory(false);
				setGreenCategory(false);
				setRedCategory(true);
				setOrangeCategory(false);
				setBlueCategory(false);

				setCategory(Category.RED);
				break;
			case "orange":
				setNoneCategory(false);
				setGreenCategory(false);
				setRedCategory(false);
				setOrangeCategory(true);
				setBlueCategory(false);

				setCategory(Category.ORANGE);
				break;
			case "blue":
				setNoneCategory(false);
				setGreenCategory(false);
				setRedCategory(false);
				setOrangeCategory(false);
				setBlueCategory(true);

				setCategory(Category.BLUE);
				break;
		}
	}

	return (
		<View style={{padding: 18}}>
			<View style={{paddingBottom: 25}}>
				<Text style={styles.label}>Name</Text>
				<TextInput style={styles.textInput} onChangeText={text => {setTitle}} />
			</View>
			<View style={{paddingBottom: 25}}>
				<Text style={styles.label}>Deadline</Text>
				{dateButtonShow && (
					<Button style={styles.datePickerButton} onPress={() => {setShow(true)}} title="Choose deadline" />
				)}
				{showDatePicker && (
					<DatePickerTime
						mode="date"
						onChange={dateOnChange}
						minimumDate={new Date()}
						value={new Date()} 
						style={{textColor: "#000000"}}/>
				)}
				<View style={{flexDirection: "row", alignItems: "center"}}>
					<CheckBox
						value={false}
						tintColors={{
                            "true": "#FFFFFF",
                            "false": "#ffffff"
						}}
						onValueChange={noDeadlineButton} />
					<Text>No Deadline</Text>
				</View>
			</View>
			<View style={{flexDirection: "row", justifyContent: "space-around"}}>
				<TouchableWithoutFeedback
					style={[
						styles.category,
						styles.catNone,
						{alignItems: "center", justifyContent: "center"},
						(catNone) ? styles.categorySelected : ""]}
					onPress={() => {setActive("none")}}
				>
					<Text>NONE</Text>
				</TouchableWithoutFeedback>

				<TouchableWithoutFeedback
					style={[
						styles.category,
						styles.catGreen,
						(catGreen) ? styles.categorySelected : ""]}
					onPress={() => {setActive("green")}}
				/>

				<TouchableWithoutFeedback
					style={[
						styles.category,
						styles.catRed,
						(catRed) ? styles.categorySelected : ""]}
					onPress={() => {setActive("red")}}
				/>

				<TouchableWithoutFeedback
					style={[
						styles.category, 
						styles.catOrange,
						(catOrange) ? styles.categorySelected : ""]}
					onPress={() => {setActive("orange")}}
				/>

				<TouchableWithoutFeedback
					style={[
						styles.category,
						styles.catBlue,
						(catBlue) ? styles.categorySelected : ""]}
					onPress={() => {setActive("blue")}}
				/>
			</View>
			<CompleteButton pressed={completePressed} />
		</View>
	)
}

const styles = StyleSheet.create({
	label: {
		fontSize: 18,
		color: "#BCBCBC"
	},

	textInput: {
		borderBottomColor: "#bcbcbc",
		borderBottomWidth: 2,
		color: "#BCBCBC"
	},
	datePickerButton: {

	},

	category: {
		borderColor: "#333333",
		borderWidth: 2,
		borderRadius: 7,
		width: 50,
		height: 50
	},
	
	categorySelected: {
		borderColor: "#eeeeee"
	},

	catNone: {
		backgroundColor: "transparent"
	},
	catGreen: {
		backgroundColor: "#27ae60"
	},
	catRed: {
		backgroundColor: "#c0392b"
	},
	catOrange: {
		backgroundColor: "#e67e22"
	},
	catBlue: {
		backgroundColor: "#2980b9"
	}
})