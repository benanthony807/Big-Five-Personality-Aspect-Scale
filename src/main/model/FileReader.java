package model;


import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Observable;

public abstract class FileReader extends Observable {

    //EFFECTS: reads (loads) data from a txt file, returns the data as a list of String
    public List<String> read(String input) throws IOException {
        return Files.readAllLines(Paths.get(input));
    }

    //taken from FileReaderWriter example
    //EFFECTS: splits string at occurrences of tab, returns split strings as ArrayList<String>
    static ArrayList<String> splitOnTab(String line) {
        String[] splits = line.split("\t");
        return new ArrayList<>(Arrays.asList(splits));
    }

}
