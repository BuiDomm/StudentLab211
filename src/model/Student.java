/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author ASUS
 */
public class Student implements Comparable<Student>{
    private int id ; 
    private String studentName; 
    private int semester; 
    private String courseName;

    public Student() {
    }

    public Student(int id, String studentName, int semester, String courseName) {
        this.id = id;
        this.studentName = studentName;
        this.semester = semester;
        this.courseName = courseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }
    
    
        public String getNames(String name) {
        name = name.trim();
        if (name.indexOf(" ") > 0) {
            int vt = name.lastIndexOf(" ");
            return name.substring(vt + 1);
        } else {
            return name;
        }
    }

    @Override
    public int compareTo(Student o) {
           {
                String nameO1 = getNames(o.getStudentName());
                String nameO2 = getNames(this.getStudentName());
                if (nameO1.compareToIgnoreCase(nameO2) > 0) {
                    return 1;
                } else if (nameO1.compareToIgnoreCase(nameO2) == 0) {
                    return 0;
                } else {
                    return -1;
                }
            
        }

    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", studentName=" + studentName + ", semester=" + semester + ", courseName=" + courseName + '}';
    }
    
    
    
}
