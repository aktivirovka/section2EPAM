public interface AverageScore {
    double calculateAverageScore(Subjects subject) throws AbsenceOfStudentsInGroup, AbsenceOfGroupInFaculty, AbsenceOfFacultyInUniversity;
}
