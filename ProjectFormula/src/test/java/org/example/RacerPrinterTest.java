package org.example;


import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static junit.framework.Assert.assertEquals;


public class RacerPrinterTest {

    ReadFile readFile = new ReadFile();

    FileValidator fileValidator = new FileValidator();

    EntityMapper entityMapper = new EntityMapper();

    RacerPrinter racerPrinter = new RacerPrinter();

    static final
    String pathOfAbbreviations = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\abbreviations.txt";

    static final
    String pathOfFileStart = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\start.log";

    static final
    String pathOfFileEnd = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\end.log";

    @Test
    public void ShouldSplitTableWhenItsLengthIsFifteenOrMore () {
        List<String> fileAbbreviations = readFile.readFile(pathOfAbbreviations);
        List<String> start = readFile.readFile(pathOfFileStart);
        List<String> end = readFile.readFile(pathOfFileEnd);
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<String> infoAbbreviations = allReadFiles.get(0);
        List<String> infoStart = allReadFiles.get(1);
        List<String> infoEnd = allReadFiles.get(2);
        List<Racer> racers = entityMapper.getListRacer(infoAbbreviations,infoStart,infoEnd);
        List<String> outputList = racerPrinter.racerTabPrint(racers);
        List<String> expectedOutputList = new ArrayList<>(Arrays.asList(
                "1Sebastian Vettel          |FERRARI                            |1:4.415",
                "2Daniel Ricciardo          |RED BULL RACING TAG HEUER          |1:12.013",
                "3Valtteri Bottas           |MERCEDES                           |1:12.434",
                "4Lewis Hamilton            |MERCEDES                           |1:12.46",
                "5Stoffel Vandoorne         |MCLAREN RENAULT                    |1:12.463",
                "6Kimi Raikkonen            |FERRARI                            |1:12.639",
                "7Fernando Alonso           |MCLAREN RENAULT                    |1:12.657",
                "8Sergey Sirotkin           |WILLIAMS MERCEDES                  |1:12.706",
                "9Charles Leclerc           |SAUBER FERRARI                     |1:12.829",
                "10Sergio Perez             |FORCE INDIA MERCEDES               |1:12.848",
                "11Romain Grosjean          |HAAS FERRARI                       |1:12.93",
                "12Pierre Gasly             |SCUDERIA TORO ROSSO HONDA          |1:12.941",
                "13Carlos Sainz             |RENAULT                            |1:12.95",
                "14Esteban Ocon             |FORCE INDIA MERCEDES               |1:13.028",
                "15Nico Hulkenberg          |RENAULT                            |1:13.065",
                "------------------------------------------------------------------------",
                "16Brendon Hartley          |SCUDERIA TORO ROSSO HONDA          |1:13.179",
                "17Marcus Ericsson          |SAUBER FERRARI                     |1:13.265",
                "18Lance Stroll             |WILLIAMS MERCEDES                  |1:13.323",
                "19Kevin Magnussen          |HAAS FERRARI                       |1:13.393"
));
        assertEquals(outputList,expectedOutputList);
    }
}
