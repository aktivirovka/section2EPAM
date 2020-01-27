import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Прочитать текст Java-программы и все слова public в объявлении атрибутов и методов класса заменить на слово private.
public class OptionalTask_2 {
    public static void main(String[] args) throws IOException {
        String fileName = "Java IO/data/Task_2.txt";
        List<String> listOfLines = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String lineFromFile;
            while ((lineFromFile = reader.readLine()) != null) {
                listOfLines.add(lineFromFile.replace("public", "private"));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            for (String line : listOfLines) {
                writer.write(line);
                writer.newLine();
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}