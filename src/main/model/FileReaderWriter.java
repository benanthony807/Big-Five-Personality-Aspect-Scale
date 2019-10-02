package model;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public interface FileReaderWriter {

    // RawScore reads and writes
    // Percentile just reads
    // so I guess it should go something like
    // percentile reads it and finds where it should go
    // then RawScore reads it and writes in the score
    // not sure if RawScore could read/write first, will depend on how the compileScores method works in Percentile
    // I guess for now RawScore can just do the reading and the writing and Percentile will just do nothing
    public List<String> read(String pathName) throws IOException;

    public void write(List<String> lines, String writerFile) throws FileNotFoundException, UnsupportedEncodingException;
//
//    public ArrayList<String> splitOnSpace(String line);

}
