package domain;

import console.Console;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class StudentCreateRead {

    private static long accountIdCounter = 0;

    // 학생 데이터들을 계속 하고 싶다면 while문으로 감싸세요
    public static Student StudentCreate () {
        Student newStudent = null;
        boolean studentInfoSave = true;
        while (studentInfoSave) {

            String studentsName = studentName();
            if (studentsName.equalsIgnoreCase("exit")) {
                studentInfoSave = false;
                continue;
            }

            List<Course> selectCourses = selectCourse();

            long countId = countIdCreate();

            newStudent = new Student(countId, studentsName, selectCourses);
            StudentData.getInstance().addStudent(newStudent);

            System.out.println("학생이 성공적으로 생성되었습니다!\n");
        }
        return newStudent;
    }

    private static String studentName () {
        System.out.println("이름을 입력하세요:");
        String studentName = null;
        boolean studentNameSave = true;
        while (studentNameSave) {
            studentName = Console.inputString();
            if (Pattern.matches("^[가-힣]*$", studentName) == true ||
                studentName.equalsIgnoreCase("exit")) {
                studentNameSave = false;
            } else {
                System.out.println("한글로 다시 입력해주세요:");
            }
        }
        return studentName;
    }

    private static List<Course> selectCourse() {

        System.out.println("선택 가능 과목 목록");
        for (CourseList selectCourseList : CourseList.values()) {
            System.out.println(selectCourseList.getCourseName() +
                                " - " +
                                selectCourseList.getType());
        }
        System.out.println("수강할 과목을 선택하세요 (하나 이상 선택, 종료는 'exit'):");
        List<Course> selectCourses = new ArrayList<>();
            while (true) {
                String studentInput = Console.inputString();
                if (studentInput.equalsIgnoreCase("exit")) {
                    break;
                }

                try {
                    if (selectCourses.size() > 7) {
                        System.out.println("과목 수를 초과 했습니다.");
                        break;
                    }
                    for (CourseList selectCourseList : CourseList.values()) {
                        if (selectCourseList.getCourseName().equalsIgnoreCase(studentInput)) {
                            selectCourses.add(selectCourseList.getCourse());
                        }
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("유효하지 않은 과목입니다. 다시 입력하세요.");
                }
            }
            return selectCourses;
        }

    private static long courseId() {
        return System.currentTimeMillis();
    }

    private static long countIdCreate() {
        return ++accountIdCounter;
    }

    public static void studentRead() {
        while (true) {
            System.out.println("등록된 학생 목록 : ");
            String studentSearch = Console.inputString();
            if (studentSearch.equalsIgnoreCase("end")) {
                break;
            }

            boolean studentFound = false;

            for (Student student : StudentData.getInstance().getStudents()) {
                if (student.getName().equals(studentSearch)) {
                    System.out.println("계정 ID: " + student.getAccountId());
                    System.out.println("이름: " + student.getName());
                    System.out.println("수강 과목:");

                    for (Course course : student.getMyCourse()) {
                        System.out.println("- " + course.getCourseName());
                    }
                    studentFound = true;
                }
            }
            if (!studentFound) {
                System.out.println(!studentFound);
                System.out.println("일치하는 이름이 없습니다.");
            }
            System.out.println("----------------------------------");
        }
    }
}
