import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class University implements AverageScore {
    private String name;
    private List<Faculty> listOfFaculties = new ArrayList<>();


    public University(String name) {
        this.name = name;
    }


    void addFaculty(Faculty... name) {
        listOfFaculties.addAll(Arrays.asList(name));
    }

    @Override
    public double calculateAverageScore(Subjects subject) throws AbsenceOfFacultyInUniversity {
        double value = 0;
        int counter = 0;
        for (int i = 0; i < listOfFaculties.size(); i++) {
            try {
                double x = listOfFaculties.get(i).calculateAverageScore(subject);
                value += x;
                counter++;
            } catch (AbsenceOfGroupInFaculty e) {
                System.out.println(listOfFaculties.get(i).getName() + " faculty doesn't have any groups with subject " + subject + " " + e);
            }
        }
        if (counter == 0) {
            throw new AbsenceOfFacultyInUniversity();
        }
        return value / counter;
    }

    Faculty getFaculty(String nameOfFaculty) throws IllegalArgumentException {
        for (Faculty faculty : listOfFaculties) {
            if (faculty.getName().equals(nameOfFaculty)) {
                return faculty;
            }
        }
        throw new IllegalArgumentException("The name '" + nameOfFaculty + "' is incorrect");
    }

    public String getName() {
        return name;
    }
}
