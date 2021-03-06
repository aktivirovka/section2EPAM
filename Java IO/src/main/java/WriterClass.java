import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriterClass {
    private FileWriter savedWriter = null;

    public void close() throws IOException {
        if (savedWriter != null) {
            savedWriter.close();
        }
    }

    private FileWriter getWriter(File fileForRecord) throws IOException {
        if (savedWriter == null) {
            savedWriter = new FileWriter(fileForRecord);
        }
        return savedWriter;
    }

    String writeLine(int countDirectory) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < countDirectory; i++)
            line.append("|     ");
        return line.toString();
    }


    void recordOfDirectoryContents(File directory, File fileForRecord, int countDirectory) throws IOException {
        FileWriter writer = getWriter(fileForRecord); //предотвращение открытия еще потоков записи
        Arrays.sort(directory.listFiles());
        List<File> fileList = new ArrayList<>();
        int temporaryCountDirectory = countDirectory;
        for (File file : directory.listFiles()) {//проверено в предыдущем коде
            if (file.isDirectory()) {
                temporaryCountDirectory++;
                writer.write("|—*" + file.getName());
                writer.write('\n');
                if (isDirectoryEmpty(file)) {
                    temporaryCountDirectory--;
                }
                writer.write(writeLine(temporaryCountDirectory));
                recordOfDirectoryContents(file, fileForRecord, temporaryCountDirectory);
                if (!isDirectoryEmpty(file)) {
                    temporaryCountDirectory--;
                }
            } else if (file.isFile()) {
                fileList.add(file);
            }
        }

        temporaryCountDirectory = countDirectory;
        for (int i = 0; i < fileList.size(); i++) {
            if (i == fileList.size() - 1) {//для последнего файла
                writer.write("└——" + fileList.get(i).getName());
                writer.write('\n');
                temporaryCountDirectory--;
            } else {
                writer.write("|——" + fileList.get(i).getName());
                writer.write('\n');
            }
            writer.write(writeLine(temporaryCountDirectory));
        }
        fileList.clear();
    }

    private boolean isDirectoryEmpty(File file) {
        return file.listFiles().length == 0;
    }

}



