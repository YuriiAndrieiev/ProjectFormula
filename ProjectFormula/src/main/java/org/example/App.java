package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;


public class App {

  static final
    String pathOfAbbreviations = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\abbreviations.txt";

  static final
    String pathOfFileStart = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\start.log";

  static final
    String pathOfFileEnd = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\end.log";



    public static void main(String[] args) {
        ReadFile readFile = new ReadFile();
        RacerPrinter racerPrinter = new RacerPrinter();
        List<String> fileAbbreviations = readFile.readFile(pathOfAbbreviations);
        List<String> start = readFile.readFile(pathOfFileStart);
        List<String> end = readFile.readFile(pathOfFileEnd);
        FileValidator fileValidator = new FileValidator();
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<String> infoAbbreviations = allReadFiles.get(0);
        List<String> infoStart = allReadFiles.get(1);
        List<String> infoEnd = allReadFiles.get(2);
        EntityMapper entityMapper = new EntityMapper();
        List<Racer> racers = entityMapper.getListRacer(infoAbbreviations,infoStart,infoEnd);
        List<String> outputList = racerPrinter.racerTabPrint(racers);
        outputList.forEach(element-> System.out.println(element));
    }
}
