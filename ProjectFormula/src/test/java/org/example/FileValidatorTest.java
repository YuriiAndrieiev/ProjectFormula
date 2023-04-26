package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static junit.framework.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertThrowsExactly;

public class FileValidatorTest {

    ReadFile readFile = new ReadFile();

    FileValidator fileValidator = new FileValidator();

    EntityMapper entityMapper = new EntityMapper();

    static final String pathOfFileStartTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\startValidator.log";

    static final String pathOfFileEndTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\endValidator.log";

    static final String abbreviationsTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\abbreviationsValidator.txt";
    // <-строки выше - старт проебался,эксепшин, строки одной длины

    static final String pathOfFileStartSecondTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\startValidator2.log";

    static final String pathOfFileEndSecondTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\endValidator2.log";

    static final String abbreviationsSecondTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\abbreviationsValidator2.txt";
    // <-строки выше - гонщик не доехал, (3 начало, 2 окончило) end короче start=abb

    static final String abbreviationsThirdTest = // юзается в тест 4
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\abbreviations3.txt";

    static final String pathOfFileStart = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\start.log"; //юзается в тест 4


    static final String pathOfFileEnd = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\end.log";

    static final String pathOfFileEndTestFourth = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\endTest4.log";

    // <- длина аббревиатуры(4) > start=end(3) один гонщик не начал гонку, используются файл с названиями как в мейне,
    // ожидается имя гощика, который не учавствовал над таблицей, файл кроме энда используется старт в тест 4


    @Test
    public void shouldReturnExceptionWhenWeOneOrMoreRacersDoesntHaveDataStart () {
        assertThrows(RuntimeException.class, () -> {
            List<String> fileAbbreviations = readFile.readFile(abbreviationsTest);
            List<String> start = readFile.readFile(pathOfFileStartTest);
            List<String> end = readFile.readFile(pathOfFileEndTest);
            FileValidator fileValidator = new FileValidator();
            List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        });
    }
    @Test
    public void shouldDeleteOneRecerWhenHeProebalsaDuringRace() {
        List<String> fileAbbreviations = readFile.readFile(abbreviationsSecondTest);
        List<String> start = readFile.readFile(pathOfFileStartSecondTest);
        List<String> end = readFile.readFile(pathOfFileEndSecondTest);
        FileValidator fileValidator = new FileValidator();
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<List<String>>expectedAllReadFiles = new ArrayList<>();
        List<String> expectedFileAbbreviations = new ArrayList<>();
        expectedFileAbbreviations.add("LHM_Lewis Hamilton_MERCEDES");
        expectedFileAbbreviations.add("SVF_Sebastian Vettel_FERRARI");
        expectedFileAbbreviations.add("SVM_Stoffel Vandoorne_MCLAREN RENAULT");
        List<String> expectedStart = new ArrayList<>();
        expectedStart.add("LHM2018-05-24_12:18:20.125");
        expectedStart.add("SVF2018-05-24_12:02:58.917");
        expectedStart.add("SVM2018-05-24_12:18:37.735");
        List<String> expectedEnd = new ArrayList<>();
        expectedEnd.add("LHM2018-05-24_12:19:32.585");
        expectedEnd.add("SVF2018-05-24_12:04:03.332");
        expectedAllReadFiles.add(expectedFileAbbreviations);
        expectedAllReadFiles.add(expectedStart);
        expectedAllReadFiles.add(expectedEnd);
        assertEquals(allReadFiles,expectedAllReadFiles);
    }
    @Test
    public void shouldDeleteOneRacerWhenHeDidntTakePart () {
        List<String> fileAbbreviations = readFile.readFile(abbreviationsThirdTest);
        List<String> start = readFile.readFile(pathOfFileStart);
        List<String> end = readFile.readFile(pathOfFileEnd);
        FileValidator fileValidator = new FileValidator();
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<List<String>>expectedAllReadFiles = new ArrayList<>();
        List<String> expectedFileAbbreviations = new ArrayList<>();
        expectedFileAbbreviations.add("SPF_Sergio Perez_FORCE INDIA MERCEDES");
        expectedFileAbbreviations.add("RGH_Romain Grosjean_HAAS FERRARI");
        expectedFileAbbreviations.add("MES_Marcus Ericsson_SAUBER FERRARI");
        expectedFileAbbreviations = (List<String>) expectedFileAbbreviations.stream().sorted().collect(Collectors.toList());
        List<String> expectedStart = new ArrayList<>();
        expectedStart.add("MES2018-05-24_12:04:45.513");
        expectedStart.add("RGH2018-05-24_12:04:45.513");
        expectedStart.add("SPF2018-05-24_12:04:45.513");
        List<String> expectedEnd = new ArrayList<>();
        expectedEnd.add("MES2018-05-24_12:05:58.778");
        expectedEnd.add("RGH2018-05-24_12:05:58.778");
        expectedEnd.add("SPF2018-05-24_12:05:58.778");
        expectedAllReadFiles.add(expectedFileAbbreviations);
        expectedAllReadFiles.add(expectedStart);
        expectedAllReadFiles.add(expectedEnd);
        assertEquals(allReadFiles,expectedAllReadFiles);
    }

    @Test
    public void shouldSolveTaskWhenWeHaveAllDifferentLists () { //abbreviations>start>end
        List<String> fileAbbreviations = readFile.readFile(abbreviationsThirdTest);
        List<String> start = readFile.readFile(pathOfFileStart);
        List<String> end = readFile.readFile(pathOfFileEndTestFourth);
        FileValidator fileValidator = new FileValidator();
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<List<String>>expectedAllReadFiles = new ArrayList<>();
        List<String> expectedFileAbbreviations = new ArrayList<>();
        expectedFileAbbreviations.add("RGH_Romain Grosjean_HAAS FERRARI");
        expectedFileAbbreviations.add("MES_Marcus Ericsson_SAUBER FERRARI");
        expectedFileAbbreviations = (List<String>) expectedFileAbbreviations.stream().sorted().collect(Collectors.toList());
        List<String> expectedStart = new ArrayList<>();
        expectedStart.add("MES2018-05-24_12:04:45.513");
        expectedStart.add("RGH2018-05-24_12:04:45.513");
        List<String> expectedEnd = new ArrayList<>();
        expectedEnd.add("MES2018-05-24_12:05:58.778");
        expectedEnd.add("RGH2018-05-24_12:05:58.778");
        expectedAllReadFiles.add(expectedFileAbbreviations);
        expectedAllReadFiles.add(expectedStart);
        expectedAllReadFiles.add(expectedEnd);
        assertEquals(allReadFiles,expectedAllReadFiles);
    }
}
