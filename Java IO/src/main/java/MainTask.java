import java.io.*;
/*Реализовать программу которая будет получать в качестве аргумента командной строки путь к директории,
например "D:/movies". Записать в текстовый файл структуру папок и файлов в виде, похожем на выполнение программы tree /F.
Если в качестве параметра в программу передается не путь к директории, а путь к txt файлу по образцу выше
 - прочитать файл и вывести в консоль следующие данные:
 Количество папок
 Количество файлов
Среднее количество файлов в папке
Среднюю длинну названия файла*/
public class MainTask {
    public static void main(String[] args) {
        String pathToDirectoryOrFile = args[0];
        File givenFile = new File(pathToDirectoryOrFile);

        if (args.length != 1 || !givenFile.exists()) {
            System.out.println("Mistake in the file or directory name");
            return;
        }

        if (givenFile.isDirectory()) {
            WriterClass writer = new WriterClass();
            try {
                File file = new File("MainTaskFile.txt");
                writer.recordOfDirectoryContents(givenFile, file);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            ReaderClass reader = new ReaderClass();
            try {
                reader.readFileLineByLine(pathToDirectoryOrFile).fillListOfSymbolsOfEveryLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }

            System.out.println("Counter of folders is " + reader.getFolderCount());
            System.out.println("Counter of files in folders is " + reader.getFileCount());
            System.out.printf("Average counter of the files in a folder is %.2f", reader.getAverageFileCount());
            System.out.printf("\nAverage length of the file name is %.2f", reader.getAverageLengthOfFileName());

        }
    }
}