import java.util.*;
/*В университете есть несколько факультетов, в которых учатся студенты, объединенные в группы. У каждого студента есть несколько учебных предметов по которым он получает оценки. Необходимо реализовать иерархию студентов, групп и факультетов.

        Посчитать средний балл по всем предметам студента
        Посчитать средний балл по конкретному предмету в конкретной группе и на конкретном факультете
        Посчитать средний балл по предмету для всего университета
        Релизовать следующие исключения:

        Оценка ниже 0 или выше 10
        Отсутсвие предметов у студента (должен быть хотя бы один)
        Отсутствие студентов в группе
        Отсутствие групп на факультете
        Отсутствие факультетов в университете*/


public class Runner {

    public static void main(String[] args) {

        University university = new University("Oxford");
        university.addFaculty(new Faculty("Historical"), new Faculty("Economic"));
        try {
            university.getFaculty("Historical").addGroup(new Group("GR_H1"), new Group("GR_H2"));
            university.getFaculty("Economic").addGroup(new Group("GR_E3"), new Group("GR_E4"));
        } catch (IllegalArgumentException e) {
            System.out.println("There is no faculty with that name " + e);
        }
        Student ivanov = new Student("Ivanov Ivan");
        ivanov.addMarksForSubject(Subjects.HISTORY, new ArrayList<>(Arrays.asList(5, 2, 2)));
        ivanov.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(1, 3, 5)));

        Student ghost = new Student("Ghost ghost");

        Student sidorov = new Student("Sidorov Oleg");
        sidorov.addMarksForSubject(Subjects.HISTORY, new ArrayList<>(Arrays.asList(2, 8, 5, 6, 3)));
        sidorov.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(7, 6, 9, 7)));

        Student kovalev = new Student("Kovalev Stanislav");
        kovalev.addMarksForSubject(Subjects.HISTORY, new ArrayList<>(Arrays.asList(7, 8, 2, 6)));
        kovalev.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(7, 9, 7)));

        Student skiba = new Student("Skiba Aleksandr");
        skiba.addMarksForSubject(Subjects.HISTORY, new ArrayList<>(Arrays.asList(4, 5, 2, 6)));
        skiba.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(3, 8, 7)));

        Student petrov = new Student("Petrov Peter");
        petrov.addMarksForSubject(Subjects.ECONOMY, new ArrayList<>(Arrays.asList(5, 5, 7, 7, 2)));
        petrov.addMarksForSubject(Subjects.MATH, new ArrayList<>(Arrays.asList(4, 5, 6, 7)));
        petrov.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(2, 5, 7)));

        Student maguchaya = new Student("Maguchaya Elena");
        maguchaya.addMarksForSubject(Subjects.ECONOMY, new ArrayList<>(Arrays.asList(7, 7, 7)));
        maguchaya.addMarksForSubject(Subjects.MATH, new ArrayList<>(Arrays.asList(9, 5, 7)));
        maguchaya.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(2, 5)));

        Student kozlova = new Student("Kozlova Anna");
        kozlova.addMarksForSubject(Subjects.ECONOMY, new ArrayList<>(Arrays.asList(9, 8, 7, 7)));
        kozlova.addMarksForSubject(Subjects.MATH, new ArrayList<>(Arrays.asList(4, 5, 6, 7)));
        kozlova.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(8, 7)));

        Student volkov = new Student("Volkov Peter");
        volkov.addMarksForSubject(Subjects.ECONOMY, new ArrayList<>(Arrays.asList(5, 5, 7, 7, 2)));
        volkov.addMarksForSubject(Subjects.MATH, new ArrayList<>(Arrays.asList(4, 5, 6, 7)));
        volkov.addMarksForSubject(Subjects.PHILOSOPHY, new ArrayList<>(Arrays.asList(9, 5)));
        try {
            Group gr_h1 = university.getFaculty("Historical").getGroup("GR_H1");
          /*  gr_h1.addStudent(ivanov);
            gr_h1.addStudent(ghost);
            gr_h1.addStudent(sidorov);*/

            Group gr_h2 = university.getFaculty("Historical").getGroup("GR_H2");
            gr_h2.addStudent(kovalev);
            gr_h2.addStudent(skiba);

            Group gr_h3 = university.getFaculty("Economic").getGroup("GR_E3");
            gr_h3.addStudent(petrov);
            gr_h3.addStudent(maguchaya);

            Group gr_h4 = university.getFaculty("Economic").getGroup("GR_E4");
            gr_h4.addStudent(kozlova);
            gr_h4.addStudent(volkov);
        } catch (IllegalArgumentException e) {
            System.out.println("Entered the name of the group/faculty is incorrect " + e);
            System.exit(0);
        }

        Student student = volkov;
        try {
            double averageScoreInSubjectsForStudent = student.averageScoreInSubjectsOfStudent();
            System.out.printf("Average score of student " + student.getSurnameAndName() + " in all subjects is %.2f %n", averageScoreInSubjectsForStudent);
        } catch (IllegalArgumentException e) {
            System.out.println("The student " + student.getSurnameAndName() + " doesn't have any marks.");
        }

        Subjects subject = Subjects.MATH;
        try {
            double averageScoreInSubjectForUniversity = university.calculateAverageScore(subject);
            System.out.printf("Average score in subject " + subject + " for university %.2f %n", averageScoreInSubjectForUniversity);
        } catch (AbsenceOfFacultyInUniversity e) {
            System.out.println(university.getName() + " doesn't have faculties, groups, and students who learn " + subject + " " + e);
        }

        String nameOfFaculty = "Historical";
        String nameOfGroup = "GR_H2";
        Subjects choiceOfSubject = Subjects.HISTORY;
        try {
            double averageScoreInSubject = university.getFaculty(nameOfFaculty).getGroup(nameOfGroup).calculateAverageScore(choiceOfSubject);
            System.out.printf("Average score in " + nameOfFaculty + " faculty in group " + nameOfGroup
                    + " in subject " + choiceOfSubject + " is %.2f %n", averageScoreInSubject);
        } catch (AbsenceOfStudentsInGroup | IllegalArgumentException e) {
            System.out.println("There is exception " + e);
        }
    }
}
