package sample;

import java.io.IOException;
import java.util.ArrayList;

public class Search {
	private final ExcelFileManager userFile;
	private final ArrayList<Integer> studentsFoundByFirstNameRowIndex = new ArrayList<Integer>();
	private final ArrayList<Integer> studentsFoundByLastNameRowIndex = new ArrayList<Integer>();
	private final ArrayList<Integer> studentsFoundByDOBRowIndex = new ArrayList<Integer>();
	private final ArrayList<Integer> studentsFoundByIdNumberRowIndex = new ArrayList<Integer>();

	private final ArrayList<Integer> studentsThatMatchedWithAnotherArray = new ArrayList<Integer>();

	private String[][] results;

	private final ArrayList<String> namesOfAllCoursesTheStudentIsInItInArrayList = new ArrayList<String>();
	private String[] namesOfAllCoursesTheStudentIsInItInArray;
	private String[][] studentData;

	private final Grading grading;

	public Search(String userEmail) {

		userFile = new ExcelFileManager(userEmail + ".xlsx");
		grading = new Grading(userEmail);

	}

	private void find_Most_Related_First_Name(String courseName, String firstName) {
		try {
			for (int i = 8; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++) {
				if (userFile.get_Data_At(courseName, i, 1).contains(firstName) == true) {
					studentsFoundByFirstNameRowIndex.add(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void find_Most_Related_Last_Name(String courseName, String lastName) {
		try {
			for (int i = 8; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++) {
				if (userFile.get_Data_At(courseName, i, 2).contains(lastName) == true) {
					studentsFoundByLastNameRowIndex.add(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void find_Most_Related_DOB(String courseName, String DOB) {
		try {
			for (int i = 8; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++) {
				if (userFile.get_Data_At(courseName, i, 0).contains(DOB) == true) {
					studentsFoundByDOBRowIndex.add(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void find_Most_Related_ID(String courseName, String idNumber) {
		try {
			for (int i = 8; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++) {
				if (userFile.get_Data_At(courseName, i, 3).contains(idNumber) == true) {
					studentsFoundByDOBRowIndex.add(i);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private void compare_The_ArrayLists(ArrayList firstArray, ArrayList secondArray) {

		firstArray.retainAll(secondArray);
		studentsThatMatchedWithAnotherArray.addAll(firstArray);
		
	}
	
	private void find_Matches_In_The_ArrayLists() {
		studentsThatMatchedWithAnotherArray.clear();

		if (studentsFoundByFirstNameRowIndex.isEmpty() == false) {
			
			if(studentsFoundByLastNameRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByFirstNameRowIndex, studentsFoundByLastNameRowIndex);
			}
			
			if (studentsFoundByDOBRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByFirstNameRowIndex, studentsFoundByDOBRowIndex);
			}
			
			if (studentsFoundByIdNumberRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByFirstNameRowIndex, studentsFoundByIdNumberRowIndex);
			}
			
			if (studentsFoundByLastNameRowIndex.isEmpty() == true && studentsFoundByDOBRowIndex.isEmpty() == true && studentsFoundByIdNumberRowIndex.isEmpty() == true) {
				studentsThatMatchedWithAnotherArray.addAll(studentsFoundByFirstNameRowIndex);
			}
			
		} else if (studentsFoundByLastNameRowIndex.isEmpty() == false) {
			
			if (studentsFoundByDOBRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByLastNameRowIndex, studentsFoundByDOBRowIndex);
			}
			
			if (studentsFoundByIdNumberRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByLastNameRowIndex, studentsFoundByIdNumberRowIndex);
			}
			
			if (studentsFoundByDOBRowIndex.isEmpty() == true && studentsFoundByIdNumberRowIndex.isEmpty() == true) {
				studentsThatMatchedWithAnotherArray.addAll(studentsFoundByLastNameRowIndex);
			}
		} else if (studentsFoundByDOBRowIndex.isEmpty() == false) {
			
			if (studentsFoundByIdNumberRowIndex.isEmpty() == false) {
				compare_The_ArrayLists(studentsFoundByDOBRowIndex, studentsFoundByIdNumberRowIndex);
			} else {
				studentsThatMatchedWithAnotherArray.addAll(studentsFoundByDOBRowIndex);
			}
			
		} else if (studentsFoundByIdNumberRowIndex.isEmpty() == false) {
			studentsThatMatchedWithAnotherArray.addAll(studentsFoundByIdNumberRowIndex);
		}

	}
	
	private void load_Results_2D_Array_With_Data(String courseName) {
		
		results = new String[studentsThatMatchedWithAnotherArray.size()][3];
		for (int i = 0; i < studentsThatMatchedWithAnotherArray.size(); i++) {
			try {
				results[i][0] = userFile.get_Data_At(courseName, studentsThatMatchedWithAnotherArray.get(i), 1) + " " + userFile.get_Data_At(courseName, studentsThatMatchedWithAnotherArray.get(i), 2);
				results[i][1] = userFile.get_Data_At(courseName, studentsThatMatchedWithAnotherArray.get(i), 3);
				results[i][2] = userFile.get_Data_At(courseName, studentsThatMatchedWithAnotherArray.get(i), 0);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	private void load_Names_Of_The_Courses_The_Student_Is_In_To_The_ArrayList (String studentIdNumber) {
		
		try {
			for (int i = 0; i < userFile.get_Total_Number_Of_Sheets(); i++) {
				String currentCourseName = userFile.get_Sheet_Name_At(i);
				for (int j = 8; j <= userFile.get_Last_Row_Of_The_Sheet(currentCourseName); j++) {
					if (userFile.get_Data_At(currentCourseName, j, 3).equalsIgnoreCase(studentIdNumber) == true) {
						namesOfAllCoursesTheStudentIsInItInArrayList.add(currentCourseName);
						break;
					}
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	private int get_Student_Row(String courseName, String studentIdNumber) throws IOException {
		int lastRow = userFile.get_Last_Row_Of_The_Sheet(courseName);
		for (int i = 8; i <= lastRow; i++) {
			if (studentIdNumber.equalsIgnoreCase(userFile.get_Data_At(courseName, i, 3))) {
				return i;
			}
		}
		return -1; //this means the user does not exists
	}
	
	public String[][] get_Results_In_2D_Array(String courseName, String firstName, String lastName, String idNumber, String DOB) {
		if (firstName.equalsIgnoreCase("") == false) {
			find_Most_Related_First_Name(courseName, firstName);
		}
		
		if (lastName.equalsIgnoreCase("") == false) {
			find_Most_Related_Last_Name(courseName, lastName);
		}
		
		if (idNumber.equalsIgnoreCase("") == false) {
			find_Most_Related_ID(courseName, idNumber);
		}
		
		if (DOB.equalsIgnoreCase("") == false) {
			find_Most_Related_DOB(courseName, DOB);
		}

		find_Matches_In_The_ArrayLists();
		
		load_Results_2D_Array_With_Data(courseName);
		
		return results;
	}
	
	public String[] get_Names_Of_All_The_Courses_That_The_Student_Is_In_It_In_Array(String courseName, String studentIdNumber) {
		load_Names_Of_The_Courses_The_Student_Is_In_To_The_ArrayList(studentIdNumber);
		namesOfAllCoursesTheStudentIsInItInArray = new String[namesOfAllCoursesTheStudentIsInItInArrayList.size()];
		for (int i = 0; i < namesOfAllCoursesTheStudentIsInItInArray.length; i++) {
			namesOfAllCoursesTheStudentIsInItInArray[i] = namesOfAllCoursesTheStudentIsInItInArrayList.get(i);
		}
		
		return namesOfAllCoursesTheStudentIsInItInArray;
	}
	
	public String[][] get_2D_Array_Loaded_With_The_Student_Data_For_The_Course(String courseName, String studentIdNumber) {
		int numberOfRowsNeededForStudentData = 0;
		int studentRow = 0; 
		try {
			numberOfRowsNeededForStudentData = userFile.get_Last_Cell_Of_Row(courseName, 6);
			studentRow = get_Student_Row(courseName, studentIdNumber);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		studentData = new String[numberOfRowsNeededForStudentData + 1][3];
		
		studentData[0][0] = "Assignments";
		studentData[0][1] = "Score";
		studentData[0][2] = "Out of";
		
		int i = 1;
		for (int j = 5; j < numberOfRowsNeededForStudentData; i++, j++) {
			try {
				studentData[i][0] = userFile.get_Data_At(courseName, 6, j);
				studentData[i][1] = userFile.get_Data_At(courseName, studentRow, j);
				studentData[i][2] = userFile.get_Data_At(courseName, 7, j);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		grading.calculate_The_Overall_Grade(courseName, studentRow);
		
		studentData[i][0] = "Exams:";
		studentData[i][1] = grading.get_Student_Total_Exams_Percentage();
		studentData[i][2] = grading.get_Student_Total_Exams_Points() + "/" + grading.get_Total_Course_Exams_Points();
		i++;
		studentData[i][0] = "HomeWork:";
		studentData[i][1] = grading.get_Student_Total_Homeworks_Percentage();
		studentData[i][2] = grading.get_Student_Total_Homeworks_Points() + "/" + grading.get_Total_Course_Homeworks_Points();
		i++;
		studentData[i][0] = "Projects:";
		studentData[i][1] = grading.get_Student_Total_Projects_Percentage();
		studentData[i][2] = grading.get_Student_Total_Projects_Points() + "/" + grading.get_Total_Course_Projects_Points();
		i++;
		studentData[i][0] = "Quizzes";
		studentData[i][1] = grading.get_Student_Total_Quizes_Percentage();
		studentData[i][2] = grading.get_Student_Total_Quizes_Points() + "/" + grading.get_Total_Course_Quizes_Points();
		i++;
		studentData[i][0] = "Total: ";
		try {
			studentData[i][1] = userFile.get_Data_At(courseName, studentRow, 4);
			studentData[i][2] = grading.get_Total_Number_Of_Student_Points_For_The_The_Course() + "/" + grading.get_Total_Number_Of_Course_Points();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return studentData;
	}

}
