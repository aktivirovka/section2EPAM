import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Group implements AverageScore {
    private String name;
    private List<Student> listOfStudent = new ArrayList<>();

    public Group(String name) {
        this.name = name;
    }

    void addStudent(Student... students) {
        listOfStudent.addAll(Arrays.asList(students));
    }

    @Override
    public double calculateAverageScore(Subjects subject) throws AbsenceOfStudentsInGroup {
        double averageValueOfMarksOfAllStudentInGroup = 0;
        int studentCount = 0;
        for (int i = 0; i < listOfStudent.size(); i++) {
            try {
                averageValueOfMarksOfAllStudentInGroup += listOfStudent.get(i).calculateAverageScore(subject);
                studentCount++;
            } catch (IllegalArgumentException e) {
                System.out.println(listOfStudent.get(i).getSurnameAndName() + " doesn't have such subject as " + subject + " " + e);
            }

        }
        if (studentCount == 0) {
            throw new AbsenceOfStudentsInGroup();
        }
        return averageValueOfMarksOfAllStudentInGroup / studentCount;
    }

    public String getName() {
        return name;
    }
}

