import java.io.*;

public class WriterClass {
    private BufferedWriter savedWriter = null;

    public void close() throws IOException {
        if (savedWriter != null) {
            savedWriter.close();
        }
    }

    void recordOfDirectoryContents(File directory, File fileForRecord) throws IOException {
        BufferedWriter writer = getBufferWriter(fileForRecord); //предотвращение открытия еще потоков записи
        for (File item : directory.listFiles()) { //проверено в предыдущем коде
            if (item.isDirectory()) {
                writer.write(item.getName());
                writer.newLine();
                recordOfDirectoryContents(item, fileForRecord);
                writer.newLine();
            } else {
                writer.write(item.getName());
                writer.newLine();
            }
        }
    }

    private BufferedWriter getBufferWriter(File fileForRecord) throws IOException {
        if (savedWriter == null) {
            savedWriter = new BufferedWriter(new FileWriter(fileForRecord));
        }
        return savedWriter;
    }
}




