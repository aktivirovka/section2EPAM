import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class WriterClass {
    String forAll = "|";
    private FileWriter savedWriter = null;

    public void close() throws IOException {
        if (savedWriter != null) {
            savedWriter.close();
        }
    }

    private FileWriter getBufferWriter(File fileForRecord) throws IOException {
        if (savedWriter == null) {
            savedWriter = new FileWriter(fileForRecord);
        }
        return savedWriter;
    }


    String getLineWithGap(int count) {
        if (count == 0) {
            return "";
        }
        StringBuilder line = new StringBuilder();
        line.append("|");
        for (int i = 0; i < count; i++) {
            line.append(" ");
        }

        return line.toString();
    }

    String symbolForDirectory = "|——";
    String symbolForFile = "└──";




    void recordOfDirectoryContents(File directory, File fileForRecord, String args0) throws IOException {
        FileWriter writer = getBufferWriter(fileForRecord); //предотвращение открытия еще потоков записи
        //Arrays.sort(directory.listFiles());

        for (File file : directory.listFiles()) {//проверено в предыдущем коде
            if (file.isDirectory()) {
                int countDash = file.getParent().length() - args0.length();
                String fullNameWithMarks = getLineWithGap(countDash) + symbolForDirectory + file.getName();
                writer.write(fullNameWithMarks);
                writer.write('\n');
                recordOfDirectoryContents(file, fileForRecord, args0);
            }
        }
        List<File> fileList = new ArrayList<>();

        for (File file : directory.listFiles()) {
            if (file.isFile()) {
                fileList.add(file);
            }
        }
        if (fileList.size() != 1) {
            for (int i = 0; i < fileList.size() - 1; i++) {
                int gasp = fileList.get(i).getParent().length() - args0.length();
                String fullNameWithGasp = getLineWithGap(gasp) + symbolForDirectory + fileList.get(i).getName();
                writer.write(fullNameWithGasp);
                writer.write('\n');
            }
            int gasp = fileList.get(fileList.size() - 1).getParent().length() - args0.length();
            String fullNameWithGasp = getLineWithGap(gasp) + symbolForFile + fileList.get(fileList.size() - 1).getName();
            writer.write(fullNameWithGasp);
        } else {
            int gasp = fileList.get(fileList.size() - 1).getParent().length() - args0.length();
            String fullNameWithGasp = getLineWithGap(gasp) + symbolForFile + fileList.get(fileList.size() - 1).getName();
            writer.write(fullNameWithGasp);
        }
        writer.write('\n');
        fileList.clear();
    }
     /* void recordOfDirectoryContents(File directory, File fileForRecord ,String prefix ) throws IOException {
        FileWriter writer = getBufferWriter(fileForRecord);//предотвращение открытия еще потоков записи
        File file;
        File[] fileList = directory.listFiles();
        Arrays.sort(fileList);

        for (int index = 0; index < fileList.length; index++) {
            file = fileList[index];
            if (index == fileList.length - 1) {
                writer.write(prefix + "└── " + file.getName());
                writer.write('\n');
                if (file.isDirectory()) {
                    recordOfDirectoryContents(file, fileForRecord,prefix + "    " );
                }
            } else {
                writer.write(prefix + "├── " + file.getName());
                writer.write('\n');
                if (file.isDirectory()) {
                    recordOfDirectoryContents(file,  fileForRecord,prefix + "│   ");
                }
            }
        }}
*/
}



