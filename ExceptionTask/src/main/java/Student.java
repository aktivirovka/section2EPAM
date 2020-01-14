
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Student implements AverageScore {
    private String surnameAndName;
    private Map<Subjects, List<Integer>> marksOfStudent = new HashMap<>();

    public Student(String surnameAndName) {
        this.surnameAndName = surnameAndName;
    }

    int validityOfMarks(int number) throws ValidityOfMarkExeption {
        if (number < 0 || number > 10) {
            throw new ValidityOfMarkExeption();
        }
        return number;

    }

    void addMarksForSubject(Subjects subject, List<Integer> list) {
        if (list.isEmpty()) {
            System.out.println(this.getSurnameAndName() + " has the empty list of marks in the subject " + subject);
            return;
        }
        for (int i = 0; i < list.size(); i++) {
            try {
                validityOfMarks(list.get(i));
            } catch (ValidityOfMarkExeption validityOfMarkExeption) {
                System.out.println("The entered mark (" + list.get(i) + ") is incorrect, it will be deleted from " + this.getSurnameAndName());
                list.remove(i);
            }
        }

        if (marksOfStudent.containsKey(subject)) {
            List<Integer> existingList = marksOfStudent.get(subject);
            existingList.addAll(list);
        } else
            marksOfStudent.put(subject, list);
    }

    @Override
    public double calculateAverageScore(Subjects subject) throws IllegalArgumentException {
        double averageValueOfStudentMarks = 0;
        int counter = 0;
        if (!marksOfStudent.containsKey(subject)) {
            throw new IllegalArgumentException();
        }
        List<Integer> marks = marksOfStudent.get(subject);
        for (int i = 0; i < marks.size(); i++) {
            averageValueOfStudentMarks += marks.get(i);
            counter++;
        }
        return averageValueOfStudentMarks / counter;
    }

    double averageScoreInSubjectsOfStudent() throws IllegalArgumentException {
        int valueOfMark = 0;
        int markCount = 0;
        if (marksOfStudent.isEmpty()) {
            throw new IllegalArgumentException();
        }
        List<List<Integer>> listsOfMarks = new ArrayList<>(marksOfStudent.values());
        for (int i = 0; i < listsOfMarks.size(); i++) {
            for (int j = 0; j < listsOfMarks.get(i).size(); j++) {
                valueOfMark += listsOfMarks.get(i).get(j);
                markCount++;
            }
        }
        return (double) valueOfMark / markCount;
    }


    public String getSurnameAndName() {
        return surnameAndName;
    }


}
