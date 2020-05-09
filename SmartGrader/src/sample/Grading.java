package sample;

import java.io.IOException;
import java.text.DecimalFormat;

public class Grading {
    private final ExcelFileManager userFile;

    private double quizesWeight;
    private double homeworksWeight;
    private double examsWeight;
    private double projectsWeight;

    private double totalCourseQuizesPoints;
    private double totalCourseHomeworksPoints;
    private double totalCourseExamsPoints;
    private double totalCourseProjectsPoints;

    private double studentTotalQuizesPoints;
    private double studentTotalHomeworksPoints;
    private double studentTotalExamsPoints;
    private double studentTotalProjectsPoints;

    private double studentTotalQuizesPercentage;
    private double studentTotalHomeworksPercentage;
    private double studentTotalExamsPercentage;
    private double studentTotalProjectsPercentage;

    private final int[] arrayOfTotalNumberOfAsBsCsDsAndFs = new int[5];

    private int gradeRangeForA;
    private int gradeRangeForB;
    private int gradeRangeForC;
    private int gradeRangeForD;
    private int gradeRangeForF;

    private boolean useTheWeights;

    public Grading(String userEmail) {
		userFile = new ExcelFileManager(userEmail + ".xlsx");
	}
	private void reset_All_The_Variables() {
		quizesWeight = 0;
		homeworksWeight = 0;
		examsWeight = 0;
		projectsWeight = 0;
		
		totalCourseQuizesPoints = 0;
		totalCourseHomeworksPoints = 0;
		totalCourseExamsPoints = 0;
		totalCourseProjectsPoints = 0;
		
		studentTotalQuizesPoints = 0;
		studentTotalHomeworksPoints = 0;
		studentTotalExamsPoints = 0;
		studentTotalProjectsPoints = 0;
		
		studentTotalQuizesPercentage = 0;
		studentTotalHomeworksPercentage = 0;
		studentTotalExamsPercentage = 0;
		studentTotalProjectsPercentage = 0;
		
	}
	private void load_Weight_Percentages(String courseName) {
		try {
			
			quizesWeight = Double.parseDouble(userFile.get_Data_At(courseName, 2, 0))/100; //divide by 100 because we need it to be not in percentage.
			homeworksWeight = Double.parseDouble(userFile.get_Data_At(courseName, 2, 1))/100;
			examsWeight = Double.parseDouble(userFile.get_Data_At(courseName, 2, 2))/100;
			projectsWeight = Double.parseDouble(userFile.get_Data_At(courseName, 2, 3))/100;
			
			useTheWeights = Boolean.parseBoolean(userFile.get_Data_At(courseName, 2, 4));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load_Grade_Ranges(String courseName) {
		try {
			
			gradeRangeForA = Integer.parseInt(userFile.get_Data_At(courseName, 4, 0));
			
			gradeRangeForB = Integer.parseInt(userFile.get_Data_At(courseName, 4, 1));
			gradeRangeForC = Integer.parseInt(userFile.get_Data_At(courseName, 4, 2));
			gradeRangeForD = Integer.parseInt(userFile.get_Data_At(courseName, 4, 3));
			gradeRangeForF = Integer.parseInt(userFile.get_Data_At(courseName, 4, 4));
			
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void load_Overall_Course_And_Student_Total_Points_For_Each_Category(String courseName, int rowIndex) {
		try {
			for (int currentColumn = 5; currentColumn < userFile.get_Last_Cell_Of_Row(courseName, 5); currentColumn++) {
				if (userFile.get_Data_At(courseName, 5, currentColumn).equals("Quiz")) {
					
					totalCourseQuizesPoints += Double.parseDouble(userFile.get_Data_At(courseName, 7, currentColumn));
					studentTotalQuizesPoints += Double.parseDouble(userFile.get_Data_At(courseName, rowIndex, currentColumn));
		
				} else if (userFile.get_Data_At(courseName, 5, currentColumn).equals("Homework")) {
					
					totalCourseHomeworksPoints += Double.parseDouble(userFile.get_Data_At(courseName, 7, currentColumn));
					studentTotalHomeworksPoints += Double.parseDouble(userFile.get_Data_At(courseName, rowIndex, currentColumn));
					
				} else if (userFile.get_Data_At(courseName, 5, currentColumn).equals("Exam")) {
					
					totalCourseExamsPoints += Double.parseDouble(userFile.get_Data_At(courseName, 7, currentColumn));
					studentTotalExamsPoints += Double.parseDouble(userFile.get_Data_At(courseName, rowIndex, currentColumn));
					
				} else if (userFile.get_Data_At(courseName, 5, currentColumn).equals("Projects")) {
					
					totalCourseProjectsPoints += Double.parseDouble(userFile.get_Data_At(courseName, 7, currentColumn));
					studentTotalProjectsPoints += Double.parseDouble(userFile.get_Data_At(courseName, rowIndex, currentColumn));
	
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void calculate_Student_Overall_Percentage_For_Each_Category() {


		if (totalCourseQuizesPoints == 0) {//Can't divide by 0 so we need to change it to 1.
			totalCourseQuizesPoints = 1;
			studentTotalQuizesPercentage = (studentTotalQuizesPoints / totalCourseQuizesPoints) * 100;
			totalCourseQuizesPoints = 0;
			
		} else {
			studentTotalQuizesPercentage = (studentTotalQuizesPoints / totalCourseQuizesPoints) * 100;
		}
		
		if (totalCourseHomeworksPoints == 0) {//Can't divide by 0 so we need to change it to 1.
			totalCourseHomeworksPoints = 1;
			studentTotalHomeworksPercentage = (studentTotalHomeworksPoints / totalCourseHomeworksPoints) * 100;
			totalCourseHomeworksPoints = 0;

		} else {
			studentTotalHomeworksPercentage = (studentTotalHomeworksPoints / totalCourseHomeworksPoints) * 100;
		}
		
		if (totalCourseExamsPoints == 0) {//Can't divide by 0 so we need to change it to 1.
			totalCourseExamsPoints = 1;
			studentTotalExamsPercentage = (studentTotalExamsPoints / totalCourseExamsPoints) * 100;	
			totalCourseExamsPoints = 0;

		} else {
			studentTotalExamsPercentage = (studentTotalExamsPoints / totalCourseExamsPoints) * 100;	
		}
		
		if (totalCourseProjectsPoints == 0) {//Can't divide by 0 so we need to change it to 1.

			totalCourseProjectsPoints = 1;
			studentTotalProjectsPercentage = (studentTotalProjectsPoints / totalCourseProjectsPoints) * 100;
			totalCourseProjectsPoints = 0;

		} else {
			studentTotalProjectsPercentage = (studentTotalProjectsPoints / totalCourseProjectsPoints) * 100;
		}
		
	}
	
	private void count_Total_Number_Of_As_Bs_Cs_Ds_And_Fs(String courseName) {
		load_Grade_Ranges(courseName);
			
		try {
			for (int currentRow = 8; currentRow <= userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
				if (Double.parseDouble(userFile.get_Data_At(courseName, currentRow, 4)) >= gradeRangeForA) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[0]++;
				} else if (Double.parseDouble(userFile.get_Data_At(courseName, currentRow, 4)) >= gradeRangeForB) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[1]++;
				} else if (Double.parseDouble(userFile.get_Data_At(courseName, currentRow, 4)) >= gradeRangeForC) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[2]++;
				} else if (Double.parseDouble(userFile.get_Data_At(courseName, currentRow, 4)) >= gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[3]++;
				} else if (Double.parseDouble(userFile.get_Data_At(courseName, currentRow, 4)) >= gradeRangeForF) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[4]++;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void calculate_The_Overall_Grade(String courseName, int rowIndex) {
		reset_All_The_Variables();
		
		load_Weight_Percentages(courseName);
		load_Overall_Course_And_Student_Total_Points_For_Each_Category(courseName, rowIndex);
		calculate_Student_Overall_Percentage_For_Each_Category();
		
		DecimalFormat decimalFormat = new DecimalFormat("0.00");
		double studentOverallGrade = 0;

		if (useTheWeights == false) {
			studentOverallGrade = ((studentTotalExamsPoints + studentTotalHomeworksPoints + studentTotalProjectsPoints + studentTotalQuizesPoints) / (totalCourseExamsPoints + totalCourseHomeworksPoints + totalCourseProjectsPoints + totalCourseQuizesPoints)) * 100;
		} else {
			studentOverallGrade = (studentTotalQuizesPercentage * quizesWeight) + (studentTotalHomeworksPercentage * homeworksWeight) + (studentTotalExamsPercentage * examsWeight) + (studentTotalProjectsPercentage * projectsWeight);
		}
		String studentOverallGradeFormated = decimalFormat.format(studentOverallGrade);
		
		try {
			userFile.update_Cell(studentOverallGradeFormated, courseName, rowIndex, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update_The_Grade(String courseName, int grade, int rowIndex, int cellIndex) {
		try {
			userFile.update_Cell(Integer.toString(grade), courseName, rowIndex, cellIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void update_Grade_Range(String courseName, String forGradeLetter, String value) {
		try {
			
			if(forGradeLetter.equalsIgnoreCase("A") == true) {
				userFile.update_Cell(value, courseName, 4, 0);
			} else if(forGradeLetter.equalsIgnoreCase("B") == true) {
				userFile.update_Cell(value, courseName, 4, 1);
			} else if(forGradeLetter.equalsIgnoreCase("C") == true) {
				userFile.update_Cell(value, courseName, 4, 2);
			} else if(forGradeLetter.equalsIgnoreCase("D") == true) {
				userFile.update_Cell(value, courseName, 4, 3);
			} else if(forGradeLetter.equalsIgnoreCase("F") == true) {
				userFile.update_Cell(value, courseName, 4, 4);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
	}
	
	public String reload_The_Overall_Grade(String courseName, int rowIndex) {
		String studentOverAllGrade = "";
		try {
			studentOverAllGrade = userFile.get_Data_At(courseName, rowIndex, 4);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentOverAllGrade;
	}
	
	public String get_Student_Score_At(String courseName, int rowIndex, int cellIndex) {
		String studentScore = ""; 
		try {
			studentScore = userFile.get_Data_At(courseName, rowIndex, cellIndex);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return studentScore;
	}
	
	public int[] get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Overall_Grade(String courseName) {
		for (int i = 0; i < 5; i++) {//reseting the array
			arrayOfTotalNumberOfAsBsCsDsAndFs[i] = 0;
		}
		count_Total_Number_Of_As_Bs_Cs_Ds_And_Fs(courseName);
		return arrayOfTotalNumberOfAsBsCsDsAndFs;
	}
	
	public int[] get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Quizes(String courseName) {
		load_Grade_Ranges(courseName);
		
		for (int i = 0; i < arrayOfTotalNumberOfAsBsCsDsAndFs.length; i++) {//reseting the array
			arrayOfTotalNumberOfAsBsCsDsAndFs[i] = 0;
		}
		
		try {
			for(int currentRow = 8; currentRow <= userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
				
				load_Overall_Course_And_Student_Total_Points_For_Each_Category(courseName, currentRow);
				calculate_Student_Overall_Percentage_For_Each_Category();
				
				if (studentTotalQuizesPercentage >= gradeRangeForA) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[0]++;
				} else if (studentTotalQuizesPercentage < gradeRangeForA && studentTotalQuizesPercentage >= gradeRangeForB) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[1]++;
				} else if (studentTotalQuizesPercentage < gradeRangeForB && studentTotalQuizesPercentage >= gradeRangeForC) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[2]++;
				} else if (studentTotalQuizesPercentage < gradeRangeForC && studentTotalQuizesPercentage >= gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[3]++;
				} else if (studentTotalQuizesPercentage < gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[4]++;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayOfTotalNumberOfAsBsCsDsAndFs;
	}
	
	public int[] get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Homeworks(String courseName) {
		load_Grade_Ranges(courseName);
		
		for (int i = 0; i < arrayOfTotalNumberOfAsBsCsDsAndFs.length; i++) {//reseting the array
			arrayOfTotalNumberOfAsBsCsDsAndFs[i] = 0;
		}
		
		try {
			for(int currentRow = 8; currentRow <= userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
				
				load_Overall_Course_And_Student_Total_Points_For_Each_Category(courseName, currentRow);
				calculate_Student_Overall_Percentage_For_Each_Category();
				
				if (studentTotalHomeworksPercentage >= gradeRangeForA) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[0]++;
				} else if (studentTotalHomeworksPercentage < gradeRangeForA && studentTotalHomeworksPercentage >= gradeRangeForB) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[1]++;
				} else if (studentTotalHomeworksPercentage < gradeRangeForB && studentTotalHomeworksPercentage >= gradeRangeForC) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[2]++;
				} else if (studentTotalHomeworksPercentage < gradeRangeForC && studentTotalHomeworksPercentage >= gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[3]++;
				} else if (studentTotalHomeworksPercentage < gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[4]++;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayOfTotalNumberOfAsBsCsDsAndFs;
	}
	
	public int[] get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Projects(String courseName) {
		load_Grade_Ranges(courseName);
		
		for (int i = 0; i < arrayOfTotalNumberOfAsBsCsDsAndFs.length; i++) {//reseting the array
			arrayOfTotalNumberOfAsBsCsDsAndFs[i] = 0;
		}
		
		try {
			for(int currentRow = 8; currentRow <= userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
				
				load_Overall_Course_And_Student_Total_Points_For_Each_Category(courseName, currentRow);
				calculate_Student_Overall_Percentage_For_Each_Category();
				
				if (studentTotalProjectsPercentage >= gradeRangeForA) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[0]++;
				} else if (studentTotalProjectsPercentage < gradeRangeForA && studentTotalProjectsPercentage >= gradeRangeForB) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[1]++;
				} else if (studentTotalProjectsPercentage < gradeRangeForB && studentTotalProjectsPercentage >= gradeRangeForC) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[2]++;
				} else if (studentTotalProjectsPercentage < gradeRangeForC && studentTotalProjectsPercentage >= gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[3]++;
				} else if (studentTotalProjectsPercentage < gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[4]++;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return arrayOfTotalNumberOfAsBsCsDsAndFs;
	}
	
	public int[] get_The_Array_Loaded_With_Total_Number_Of_As_Bs_Cs_Ds_And_Fs_For_Exams(String courseName) {
		load_Grade_Ranges(courseName);
		
		for (int i = 0; i < arrayOfTotalNumberOfAsBsCsDsAndFs.length; i++) {//reseting the array
			arrayOfTotalNumberOfAsBsCsDsAndFs[i] = 0;
		}
		
		try {
			for(int currentRow = 8; currentRow <= userFile.get_Last_Row_Of_The_Sheet(courseName); currentRow++) {
				
				load_Overall_Course_And_Student_Total_Points_For_Each_Category(courseName, currentRow);
				calculate_Student_Overall_Percentage_For_Each_Category();
				
				if (studentTotalExamsPercentage >= gradeRangeForA) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[0]++;
				} else if (studentTotalExamsPercentage < gradeRangeForA && studentTotalExamsPercentage >= gradeRangeForB) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[1]++;
				} else if (studentTotalExamsPercentage < gradeRangeForB && studentTotalExamsPercentage >= gradeRangeForC) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[2]++;
				} else if (studentTotalExamsPercentage < gradeRangeForC && studentTotalExamsPercentage >= gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[3]++;
				} else if (studentTotalExamsPercentage < gradeRangeForD) {
					arrayOfTotalNumberOfAsBsCsDsAndFs[4]++;
				}
				
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return arrayOfTotalNumberOfAsBsCsDsAndFs;
	}
	
	public void recalculate_The_Grades_Of_All_Students(String courseName) {
		try {
			for (int i = 8; i <= userFile.get_Last_Row_Of_The_Sheet(courseName); i++) {
				calculate_The_Overall_Grade(courseName, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String get_Total_Course_Quizes_Points() {
		return String.valueOf(totalCourseQuizesPoints);
	}
	
	public String get_Total_Course_Homeworks_Points() {
		return String.valueOf(totalCourseHomeworksPoints);
	}
	
	public String get_Total_Course_Exams_Points() {
		return String.valueOf(totalCourseExamsPoints);
	}
	
	public String get_Total_Course_Projects_Points() {
		return String.valueOf(totalCourseProjectsPoints);
	}
		
	public String get_Student_Total_Quizes_Points() {
		return String.valueOf(studentTotalQuizesPoints);
	}
	
	public String get_Student_Total_Homeworks_Points() {
		return String.valueOf(studentTotalHomeworksPoints);
	}
	
	public String get_Student_Total_Exams_Points() {
		return String.valueOf(studentTotalExamsPoints);
	}
	
	public String get_Student_Total_Projects_Points() {
		return String.valueOf(studentTotalProjectsPoints);
	}
	
	public String get_Student_Total_Quizes_Percentage() {
		return String.valueOf(studentTotalQuizesPercentage);
	}
	
	public String get_Student_Total_Homeworks_Percentage() {
		return String.valueOf(studentTotalHomeworksPercentage);
	}
	
	public String get_Student_Total_Exams_Percentage() {
		return String.valueOf(studentTotalExamsPercentage);
	}

	public String get_Student_Total_Projects_Percentage() {
		return String.valueOf(studentTotalProjectsPercentage);
	}
	
	public String get_Total_Number_Of_Student_Points_For_The_The_Course() {
		return String.valueOf(studentTotalExamsPoints + studentTotalHomeworksPoints + studentTotalProjectsPoints + studentTotalQuizesPoints);
	}
	
	public String get_Total_Number_Of_Course_Points() {
		return String.valueOf(totalCourseExamsPoints + totalCourseHomeworksPoints + totalCourseProjectsPoints + totalCourseQuizesPoints);
	}
}
