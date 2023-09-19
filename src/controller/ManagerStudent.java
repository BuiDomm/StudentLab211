/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Common.Library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.function.Predicate;
import model.Report;
import model.Student;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class ManagerStudent extends Menu<String> {

    Scanner sc = new Scanner(System.in);
    Library lib = new Library();
    Student st = new Student();
    static String[] choices = {"Create", "Find and Sort", "Update/Date", "Report"};

    public ManagerStudent() {
        super("Management Student App", choices, "Exit");

    }
    ArrayList<Student> listSt = new ArrayList<>();

    @Override
    public void execute(int n) {
        Student st;
        switch (n) {
            case 1: {
                int id = lib.autoIncreasingID(listSt);
                String name = lib.getValue("Input your name:");
                int semester = lib.getInt("Input your semester:");
                String courseName = lib.getValue("Input course name:");
                if (listSt.size() > 10) {
                    String check = lib.getValue("Do you want to continue (Y/N");
                    if (check.equals("Y")) {
                        st = new Student(id, name, semester, courseName);
                        listSt.add(st);
                    }
                    break;
                } else {
                    st = new Student(id, name, semester, courseName);
                    listSt.add(st);

                }
                displayList(listSt);
                break;
            }

            case 2: {
                String[] mc = {"Find", "Sort"};

                Menu m = new Menu("Find/Sort", mc, "Exit") {
                    @Override
                    public void execute(int n) {
                        switch (n) {
                            case 2: {
                                System.out.println("The list before sort:");
                                System.out.println("=======================================================");
                                displayList(listSt);
                                break;
                            }
                            case 1: {
                                String name = lib.getValue("Input name student you want to find:");
                                ArrayList<Student> haha = search(st -> st.getStudentName().contains(name));
                                displayList(haha);
                                break;
                            }
                        }
                    }

                };
                m.run();
                break;

            }
            case 3: {
                int id = lib.getInt("Input id you want to search:");
                editStudentByID(id);
                break;
            }
            case 4: {
                report();
                break;
            }
        }

    }

    public ArrayList<Student> search(Predicate<Student> st) {
        ArrayList<Student> subList = new ArrayList<>();
        for (Student s : listSt) {
            if (st.test(s)) {
                subList.add(s);
            }
        }
        return subList;
    }

    public void displayList(ArrayList<Student> list) {
        for (Student s : list) {
            System.out.println("Student name: " + s.getStudentName() + "|Semester :" + s.getSemester() + "|Course name: " + s.getCourseName());
        }
    }

    public void editStudentByID(int id) {
        ArrayList<Student> haha = search(st -> st.getId() == id);
        if (haha.isEmpty()) {
            System.out.println("No exists");
        } else {
            String input = lib.checkUD("Do you want to update (U) or delete (D) student.");
            switch (input) {
                case "U": {
                    System.out.println("Input your name:");
                    String name = sc.nextLine();
                    if (name.isEmpty()) {
                        name = haha.get(0).getStudentName();
                    }
                    System.out.println("Input your Semeter:");
                    String semesterString = sc.nextLine();
                    int semester;

                    if (semesterString.isEmpty()) {
                        semester = haha.get(0).getSemester();
                    } else {
                        semester = Integer.parseInt(semesterString);
                    }

                    System.out.println("Input course name:");
                    String courseName = sc.nextLine();
                    if (courseName.isEmpty()) {
                        courseName = haha.get(0).getCourseName();
                    }
                    Student s = new Student(id, name, semester, courseName);
                    listSt.remove(haha.get(0));
                    listSt.add(s);
                    System.out.println("=======List Before to update======");
                    displayList(listSt);
                    break;
                }
                case "D": {
                    listSt.remove(haha.get(0));
                    System.out.println("=======List Before to delete======");
                    displayList(listSt);
                    break;

                }
            }

        }
    }

    public void report() {
        ArrayList<Student> stu = listSt;
        if (stu.isEmpty()) {
            System.out.println("List is Empty.");
            return;
        }
        ArrayList<Report> listReports = new ArrayList<>();

        for (Student student : stu) {
            String id = student.getId() + "";
            String courseName = student.getCourseName();
            String studentName = student.getCourseName();

            int total = 0;

            for (Student students : stu) {
                if (studentName.equalsIgnoreCase(students.getStudentName()) && courseName.equalsIgnoreCase(students.getCourseName())) {
                    total++;
                }
            }

            if (!lib.checkReportExist(listReports, studentName, courseName, total)) {
                listReports.add(new Report(studentName, courseName, total));
            }
        }

        for (Report report : listReports) {
            System.out.printf("%-10s|%-5s|%-5d\n", report.getStuName(), report.getCourseName(), report.getTotalCourse());
        }
    }
}
