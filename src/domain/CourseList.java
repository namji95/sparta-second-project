package domain;

// 과목 목록에 대한 enum class입니다.
// 여기서 과목을 설정할 수 있으며, 추가적으로 과목을 넣고 싶다면 이 enum class 에 추가를 하면 됩니다.
public enum CourseList {
    JAVA(1,"Java",CourseType.MANDATORY),
    SPRING(2,"Spring",CourseType.MANDATORY),
    JPA(3,"JPA",CourseType.MANDATORY),
    MYSQL(4,"MySQL",CourseType.MANDATORY),


    DESIGNPATTERN(101,"디자인패턴",CourseType.OPTIONAL),
    SPRINGSECURITY(102,"Spring Security",CourseType.OPTIONAL),
    REDIS(103,"Redis",CourseType.OPTIONAL),
    MONGODB(104,"MongoDB",CourseType.OPTIONAL);

    private final long idNumber;
    private final String courseName;
    private final CourseType type;

    CourseList(long idNumber, String courseName, CourseType type) {
        this.idNumber = idNumber;
        this.courseName = courseName;
        this.type = type;
    }

    // 과목에 대한 정보를 찾을 때, 파라미터 값으로 과목 이름을 넣고 해당 괌고이 없을 경우 에러 발생
    public static CourseList getCourseList(String name) {
        int count = 1;
        for(CourseList courseList : CourseList.values()) {
            if(courseList.getCourseName().equals(name))
                return courseList;
        }

        throw new IllegalArgumentException();
    }

    // 과목의 고유 번호를 반환값으로 넘겨줍니다.
    public long getIdNumber() {
        return idNumber;
    }

    // 과목의 이름을 넘겨줍니다.
    public String getCourseName() {
        return courseName;
    }

    // 과목의 타입을 넘겨줍니다.
    public CourseType getType() {
        return type;
    }
}