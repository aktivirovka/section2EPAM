import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Создать и заполнить файл случайными целыми числами. Отсортировать содержимое файла по возрастанию.
public class OptionalTask_1 {
    static int newLineCharacter = 10;
    static int endOfLine = -1;

    public static void main(String[] args) {

       File file = new File("FirstFile.txt_1");
       file.delete();//не надо удалять данные из файла каждый раз

        List<Integer> listOfNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int number = (int) (Math.random() * 10);
            listOfNumbers.add(number);
        }
        writeNumbers(listOfNumbers, file);
        List<Integer> listOfReadNumbers = readNumbers(file);
        System.out.println("\nSorting");
        Collections.sort(listOfReadNumbers);
        writeNumbers(listOfReadNumbers, file);
        readSortedNumbers(file);

    }

    static void writeNumbers(List<Integer> numbers, File file) {
        System.out.println("Writing numbers to the file");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (Integer num : numbers) {
                writer.write(num);
                System.out.print(num);
            }
            writer.write("\n");
            //  writer.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static List<Integer> readNumbers(File file) {
        List<Integer> numbersFromFile = new ArrayList<>();
        System.out.println("\nReading numbers from the file");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int number;
            do {
                number = reader.read();
                if (number != OptionalTask_1.endOfLine && number != OptionalTask_1.newLineCharacter) {
                    numbersFromFile.add(number);
                    System.out.print(number);
                }
            } while (number != OptionalTask_1.endOfLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return numbersFromFile;
    }

    static void readSortedNumbers(File file) {
        List<Integer> sortedNumbersFromFile = new ArrayList<>();
        System.out.println("\nReading sorted numbers from the file");
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            int number;
            do {
                number = reader.read();
            } while (number != OptionalTask_1.newLineCharacter);
            do {
                number = reader.read();
                if (number != OptionalTask_1.endOfLine && number != OptionalTask_1.newLineCharacter) {
                    sortedNumbersFromFile.add(number);
                    System.out.print(number);
                }
            } while (number != OptionalTask_1.endOfLine);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}