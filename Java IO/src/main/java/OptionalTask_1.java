import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Создать и заполнить файл случайными целыми числами. Отсортировать содержимое файла по возрастанию.
public class OptionalTask_1 {
    public static void main(String[] args) {
        File file = new File("FirstFile.txt_1");
        file.delete();//не надо удалять данные из файла каждый раз

        List<String> listOfNumbers = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            int number = (int) (Math.random() * 10);
            String str = Integer.toString(number);
            listOfNumbers.add(str);
        }
        writeNumbers(listOfNumbers, file);
        String line = readNumbers(file, false);
        List<String> sortedList = sorting(line);
        writeNumbers(sortedList, file);
        readNumbers(file, true);

    }

    static void writeNumbers(List<String> numbers, File file) {
        System.out.println("Writing numbers to the file");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            for (String num : numbers) {
                writer.write(num);
                System.out.print(num);
            }
            // writer.write("\n");
            writer.newLine();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static String readNumbers(File file, boolean areSortedNumbers) {
        String line = null;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            if (!areSortedNumbers) {
                System.out.println("\nReading numbers from the file");
                line = reader.readLine();
                System.out.println(line);
            } else {
                System.out.println("\nReading sorted numbers from the file");
                reader.readLine();//чтение неотсортированной строки
                String sortedLine = reader.readLine();
                System.out.println(sortedLine);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
    }

    static List<String> sorting(String line) {
        System.out.println("Sorting");
        List<String> stringList = new ArrayList<>();
        List<Integer> sortedNumbersFromFile = new ArrayList<>();
        String[] arraySymbols = line.split("");
        for (int i = 0; i < arraySymbols.length; i++) {
            int symbolInInteger = Integer.parseInt(arraySymbols[i]);
            sortedNumbersFromFile.add(symbolInInteger);
        }
        Collections.sort(sortedNumbersFromFile);
        for (int i = 0; i < sortedNumbersFromFile.size(); i++) {
            stringList.add(sortedNumbersFromFile.get(i).toString());
        }
        return stringList;
    }

}