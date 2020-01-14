import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Faculty implements AverageScore {
    private String name;
    private List<Group> listOfGroups = new ArrayList<>();

    public Faculty(String name) {
        this.name = name;
    }

    @Override
    public double calculateAverageScore(Subjects subject) throws AbsenceOfGroupInFaculty {
        double averageValueOfMarksInFaculty = 0;
        int groupCount = 0;
        for (int i = 0; i < listOfGroups.size(); i++) {
            try {
                averageValueOfMarksInFaculty += listOfGroups.get(i).calculateAverageScore(subject);
                groupCount++;
            } catch (AbsenceOfStudentsInGroup e) {
                System.out.println(listOfGroups.get(i).getName() + " doesn't have students who learn " + subject + " " + e);
            }
        }
        if (groupCount == 0) {
            throw new AbsenceOfGroupInFaculty();
        }
        return averageValueOfMarksInFaculty / groupCount;
    }

    Group getGroup(String name) {
        for (Group group : listOfGroups) {
            if (group.getName().equals(name)) {
                return group;
            }
        }
        throw new IllegalArgumentException("The name '" + name + "' is incorrect");
    }

    void addGroup(Group... name) {
        listOfGroups.addAll(Arrays.asList(name));
    }

    public String getName() {
        return name;
    }
}
