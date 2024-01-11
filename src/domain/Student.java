package domain;

import java.util.List;

public class Student {
    private final long accountId;
    private final String name;
    private final List<Course> myCourse;

    public Student(long accountId, String name, List<Course> myCourse) {
        this.accountId = accountId;
        this.name = name;
        this.myCourse = myCourse;
    }


    public long getAccountId() {
        return accountId;
    }

    public String getName() {
        return name;
    }

    public List<Course> getMyCourse() {
        return myCourse;
    }
}
