
package com.kiet.student.controller;

import com.kiet.student.model.Student;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class StudentController {

    private List<Student> students = new ArrayList<>();

    // Constructor: Adding 5 sample students
    public StudentController() {
        students.add(new Student(101, "Rahul", "rahul@gmail.com", "CSE", 2, 9.2));
        students.add(new Student(102, "Aman", "aman@gmail.com", "IT", 2, 8.5));
        students.add(new Student(103, "Priya", "priya@gmail.com", "CSE", 3, 9.5));
        students.add(new Student(104, "Neha", "neha@gmail.com", "ECE", 1, 8.8));
        students.add(new Student(105, "Rohan", "rohan@gmail.com", "IT", 3, 9.0));
    }

    // Q3: Get all students
    @GetMapping("/students")
    public List<Student> getAllStudents() {
        return students;
    }

    // Q3: Get student by ID
    @GetMapping("/students/{studentId}")
    public Object getStudentById(@PathVariable int studentId) {

        for (Student student : students) {
            if (student.getStudentId() == studentId) {
                return student;
            }
        }

        return Map.of("message", "Student not found");
    }

    // Q4: Add a new student
    @PostMapping("/students")
    public Student addStudent(@RequestBody Student student) {
        students.add(student);
        return student;
    }

    // Q5: Update student by ID
    @PutMapping("/students/{studentId}")
    public Object updateStudent(
            @PathVariable int studentId,
            @RequestBody Student updatedStudent) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                student.setName(updatedStudent.getName());
                student.setEmail(updatedStudent.getEmail());
                student.setBranch(updatedStudent.getBranch());
                student.setYear(updatedStudent.getYear());
                student.setCgpa(updatedStudent.getCgpa());

                return student;
            }
        }

        return Map.of("message", "Student not found");
    }

    // Q5: Delete student by ID
    @DeleteMapping("/students/{studentId}")
    public Object deleteStudent(@PathVariable int studentId) {

        for (Student student : students) {

            if (student.getStudentId() == studentId) {

                students.remove(student);

                return Map.of("message", "Student deleted successfully");
            }
        }

        return Map.of("message", "Student not found");
    }

    // Q6: Search students by branch and year
    @GetMapping("/students/search")
    public List<Student> searchStudents(
            @RequestParam String branch,
            @RequestParam int year) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getBranch().equalsIgnoreCase(branch)
                    && student.getYear() == year) {

                result.add(student);
            }
        }

        return result;
    }

    // Q6: Find students by minimum CGPA
    @GetMapping("/students/topper")
    public List<Student> getTopperStudents(@RequestParam double cgpa) {

        List<Student> result = new ArrayList<>();

        for (Student student : students) {

            if (student.getCgpa() >= cgpa) {
                result.add(student);
            }
        }

        return result;
    }
}
