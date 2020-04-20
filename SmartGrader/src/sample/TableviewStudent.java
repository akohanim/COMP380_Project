package sample;

import javafx.beans.property.SimpleStringProperty;

public class TableviewStudent {
    private final SimpleStringProperty firstName;
    private final SimpleStringProperty lastName;
    private final SimpleStringProperty studentID;
    private final SimpleStringProperty DOB;
    private final SimpleStringProperty overallGrade;

    public TableviewStudent(String firstName, String lastName, String studentID, String DOB, String overallGrade) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.studentID = new SimpleStringProperty(studentID);
        this.DOB = new SimpleStringProperty(DOB);
        this.overallGrade = new SimpleStringProperty(overallGrade);
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public SimpleStringProperty firstNameProperty() {
        return firstName;
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public SimpleStringProperty lastNameProperty() {
        return lastName;
    }

    public String getStudentID() {
        return studentID.get();
    }

    public void setStudentID(String studentID) {
        this.studentID.set(studentID);
    }

    public SimpleStringProperty studentIDProperty() {
        return studentID;
    }

    public String getDOB() {
        return DOB.get();
    }

    public void setDOB(String DOB) {
        this.DOB.set(DOB);
    }

    public SimpleStringProperty DOBProperty() {
        return DOB;
    }

    public String getOverallGrade() {
        return overallGrade.get();
    }

    public void setOverallGrade(String overallGrade) {
        this.overallGrade.set(overallGrade);
    }

    public SimpleStringProperty overallGradeProperty() {
        return overallGrade;
    }
}
