import java.io.*;

public class WriterClass {
    String forAll = "|";
    private BufferedWriter savedWriter = null;

    public void close() throws IOException {
        if (savedWriter != null) {
            savedWriter.close();
        }
    }

    String getLineWithMarK(int count) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < count; i++) {
            line.append("-");
        }
        return line.toString();
    }

    String getLineWithGap(int count) {
        StringBuilder line = new StringBuilder();
        for (int i = 0; i < count; i++) {
            line.append(" ");
        }
        return line.toString();
    }


    int countDash = 1;

    void recordOfDirectoryContents(File directory, File fileForRecord, String args0) throws IOException {
        BufferedWriter writer = getBufferWriter(fileForRecord); //предотвращение открытия еще потоков записи
        for (File item : directory.listFiles()) { //проверено в предыдущем коде
            if (item.isDirectory()) {
             /*   if (item.getParent().equals(args0)) {
                    String fullNameWithMarks = forAll + getLineWithMarK(countDash) + item.getName();
                    System.out.println(fullNameWithMarks);
                    writer.write(fullNameWithMarks);
                } else {*/
                countDash = item.getParent().length() - args0.length();
                //System.out.println("path - " + item.getAbsolutePath().length() + " - " + " parent " + item.getParent().length());
                // System.out.println(item.getAbsolutePath() + "  " + item.getParent());
                String fullNameWithMarks = forAll + getLineWithMarK(countDash + 3) + item.getName();
                System.out.println(fullNameWithMarks);
                writer.write(fullNameWithMarks);
                //}
                writer.newLine();
                countDash = 1;
                recordOfDirectoryContents(item, fileForRecord, args0);
            }
            if (item.isFile()) {
                int gasp = 4;
                gasp = item.getParent().length() - args0.length();

                // System.out.println("path - " + item.getAbsolutePath().length() + " - " + " parent " + item.getParent().length());
                // System.out.println(item.getAbsolutePath() + "  " + item.getParent());
                String fullNameWithGasp = forAll + getLineWithGap(gasp + 3) + item.getName();
                System.out.println(fullNameWithGasp);
                writer.write(fullNameWithGasp);
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




