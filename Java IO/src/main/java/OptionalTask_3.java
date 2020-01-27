import java.io.*;
import java.util.ArrayList;
import java.util.List;

//В файле, содержащем фамилии студентов и их оценки, записать прописными буквами фамилии тех студентов,
// которые имеют средний балл более 7.
public class OptionalTask_3 {
    public static void main(String[] args) {
        String fileName = "Java IO/data/StudentsAndMarks";
        int valueToCompare = 7;
        List<String[]> listOfArrays = readLinesFromFile(fileName);
        List<String[]> listForWriting = makeUppercaseIfGreaterThanGivenAverage(listOfArrays, valueToCompare);
        writeLinesToFile(listForWriting, fileName);
    }

    static List<String[]> readLinesFromFile(String file) {
        List<String[]> listOfLines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                String[] arrayString = lineFromFile.split(" ");
                listOfLines.add(arrayString);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return listOfLines;
    }

    static List<String[]> makeUppercaseIfGreaterThanGivenAverage(List<String[]> listOfArrays, int value) {
        for (int i = 0; i < listOfArrays.size(); i++) {
            double sumOfNumbers = 0;
            for (int j = 1; j < listOfArrays.get(i).length; j++) {
                sumOfNumbers += Double.parseDouble(listOfArrays.get(i)[j]);
            }
            if ((sumOfNumbers / (listOfArrays.get(i).length - 1)) > value) {
                listOfArrays.get(i)[0] = listOfArrays.get(i)[0].toUpperCase();
            }
        }
        return listOfArrays;
    }

    static void writeLinesToFile(List<String[]> listOfArrays, String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (int i = 0; i < listOfArrays.size(); i++) {
                for (int j = 0; j < listOfArrays.get(i).length; j++) {
                    String lineWithGasp = listOfArrays.get(i)[j] + " ";
                    writer.write(lineWithGasp);
                }
                writer.newLine();
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}