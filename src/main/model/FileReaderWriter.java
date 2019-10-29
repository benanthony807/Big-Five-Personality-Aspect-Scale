package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public abstract class FileReaderWriter {

    //EFFECTS: reads (loads) data from a txt file, returns the data as a list of String
    public List<String> read(String input) throws IOException {
//        try {
        return Files.readAllLines(Paths.get(input));
//        } catch (IOException e) {
//            System.out.println("file cannot be read");
//            return null;
//        }
    }
    // both taken from Load/save example given in Deliverable 4 prompt

    //MODIFIES: writerFile.txt
    //EFFECTS: adds this.rawScores to the end of writerFile
    public abstract void write(List<String> l, String wf) throws FileNotFoundException, UnsupportedEncodingException;
//
//    public ArrayList<String> splitOnSpace(String line);

}
