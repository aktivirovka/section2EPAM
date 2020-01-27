import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReaderClass {
    private List<String> listOfLines;
    private List<char[]> listOfEveryLineSymbols;

    ReaderClass readFileLineByLine(String file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        listOfLines = new ArrayList<>();
        String lineFromFile;
        while ((lineFromFile = reader.readLine()) != null) {
            if (lineFromFile.isEmpty()) {
                continue;
            }
            listOfLines.add(lineFromFile);
        }
        reader.close();
        return this;
    }

    void fillListOfSymbolsOfEveryLine() {
        listOfEveryLineSymbols = new ArrayList<>();
        for (int i = 0; i < listOfLines.size(); i++) {
            char[] charsFromLine = listOfLines.get(i).toCharArray();
            listOfEveryLineSymbols.add(charsFromLine);
        }
    }

    int getFolderCount() {
        int folderCount = 0;
        for (int i = 0; i < listOfEveryLineSymbols.size(); i++) {
            for (int j = 0; j < listOfEveryLineSymbols.get(i).length; j++) {
                if (listOfEveryLineSymbols.get(i)[j] == '*') {
                    folderCount++;
                }
            }
        }
        return folderCount;
    }

    int getFileCount() {
        return listOfLines.size() - getFolderCount();
    }

    private int getLengthOfAllSymbolsFromFileNames() {
        int charactersOutsideFileName = 3; // номер файла(01) и пробел до названия файла;
        int lengthOfAllSymbolsFromFileNames = 0;
        for (int i = 0; i < listOfEveryLineSymbols.size(); i++) {
            for (int j = 0; j < listOfEveryLineSymbols.get(i).length; ) {
                if (listOfEveryLineSymbols.get(i)[j] == '*') {
                    break;
                }
                lengthOfAllSymbolsFromFileNames += listOfEveryLineSymbols.get(i).length - charactersOutsideFileName;
                break;
            }
        }
        return lengthOfAllSymbolsFromFileNames;
    }

    double getAverageFileCount() {
        if (getFileCount() == 0) {
            return -1;
        }
        return (double) getFileCount() / getFolderCount();
    }

    double getAverageLengthOfFileName() {
        if (getLengthOfAllSymbolsFromFileNames() == 0) {
            return -1;
        }
        return (double) getLengthOfAllSymbolsFromFileNames() / getFileCount();
    }
}
