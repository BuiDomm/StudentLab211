/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import Common.Library;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.Predicate;
import model.Student;
import view.Menu;

/**
 *
 * @author ASUS
 */
public class ManagerStudent extends Menu<String> {

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
            System.out.println("Student name: " + s.getStudentName() + "Semester :" + s.getSemester() + "Course name: " + s.getCourseName());
        }
    }

    public void editStudentByID(int id) {
        Student s;
        ArrayList<Student> haha = search(st -> st.getId() == id);
        if (haha.isEmpty()) {
            System.out.println("No exists");
        } else {
            String input = lib.checkUD("Do you want to update (U) or delete (D) student.");
            if (input == "U") {
                String name = lib.getValue("Input your name:");
                if (name.isEmpty()) {
                    name = haha.get(id).getStudentName();
                }

                int semester = lib.getInt("Input your semester:");
                if (semester == haha.get(id).getSemester()) {
                    semester = haha.get(id).getSemester();
                }
                String courseName = lib.getValue("Input course name:");
                if (courseName.isEmpty()) {
                    courseName = haha.get(id).getCourseName();
                }
                s = new Student(id, name, semester, courseName);
                listSt.add(s);

            } else {
                haha.remove(id);
            }
        }
    }

    public void report(ArrayList<Student> listSt) {
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Java", 1);
        map.put(".Net", 2);
        map.put("C/C++", 3);

        for (Student s : listSt) {
            if (map.containsKey(s.getCourseName())) {
                int count = 0;
                count++;
            }
        }
    }
}
