package org.example;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class ReadFile {

    public List<String> readFile(String string) {
        List<String> fileEnd= new ArrayList<>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new FileReader(string))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                fileEnd.add(line);
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        fileEnd = (List<String>) fileEnd.stream().sorted().collect(Collectors.toList());
        return fileEnd;
    }
}
