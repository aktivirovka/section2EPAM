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
        File givenFile = new File(args[0]);

        if (args.length != 1 || !givenFile.exists()) {
            System.out.println("Mistake in the file or directory name or you have gasp in file name");
            return;
        }

        if (givenFile.isDirectory()) {
            WriterClass writer = new WriterClass();
            try {
                File file = new File("MainTaskFile.txt");
                writer.recordOfDirectoryContents(givenFile, file, 0);
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else if (givenFile.isFile()) {
            ReaderClass reader = new ReaderClass();
            try {
                reader.readFileLineByLine(args[0]).fillListOfSymbolsOfEveryLine();
            } catch (IOException e) {
                e.printStackTrace();
                System.exit(0);
            }
            System.out.println("Counter of folders is " + reader.getFolderCount());
            System.out.println("Counter of files in folders is " + reader.getFileCount());
            System.out.printf("Average counter of the files in a folder is %.2f", reader.getAverageFileCount());
            System.out.printf("\nAverage length of the file name is %.2f", reader.getAverageLengthOfFileName());

        } else {
            System.out.println("Your file is hidden");
        }
    }
}