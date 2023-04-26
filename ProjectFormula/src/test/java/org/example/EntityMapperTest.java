package org.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class  EntityMapperTest {

    private ReadFile readFile;
  private EntityMapper entityMapper;


    static final String pathOfFileStartTest =
          "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\start.log";

    static final String pathOfFileEndTest =
          "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\end.log";

    static final String abbreviationsTest =
          "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\tes\\resources\\abbreviations.txt";

    static final String pathOfFileStartEmptyTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\tes\\resources\\startEmpty.log";
    static final String pathOfFileEndEmptyTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\endEmpty.log";

    static final String abbreviationsEmptyTest =
            "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\test\\resources\\abbreviationsEmpty.txt";

    @Test
  public void shouldReturnsSortedRacersListWhenWeHaveTwoRacersWithTheSameTime() {
        assertThrows(RuntimeException.class, () -> {
      List<Racer>racersExpected = new ArrayList<>();
      racersExpected.add(new Racer("Marcus Ericsson","SAUBER FERRARI",Duration.parse("PT-1M-13.265S")));
      racersExpected.add(new Racer("Romain Grosjean","HAAS FERRARI", Duration.parse("PT-1M-13.265S")));
      racersExpected.add(new Racer("Sergio Perez","FORCE INDIA MERCEDES", Duration.parse("PT-1M-13.265S")));
        List<String> fileAbbreviations = readFile.readFile(abbreviationsTest);
        List<String> start = readFile.readFile(pathOfFileStartTest);
        List<String> end = readFile.readFile(pathOfFileEndTest);
        FileValidator fileValidator = new FileValidator();
        List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
        List<String> infoAbbreviations = allReadFiles.get(0);
        List<String> infoStart = allReadFiles.get(1);
        List<String> infoEnd = allReadFiles.get(2);
      List<Racer> racers = entityMapper.getListRacer(infoAbbreviations,infoStart,infoEnd);
      assertEquals(racers, racers);
        });
  }

    @Test
  public void shouldThrowIndexOutOfBoundsExceptionWhenWeGetEmptyFiles () {
      assertThrows(RuntimeException.class, () -> {
          List<Racer>racers = new ArrayList<>();
          List<String> fileAbbreviations = readFile.readFile(abbreviationsEmptyTest);
          List<String> start = readFile.readFile(pathOfFileStartEmptyTest);
          List<String> end = readFile.readFile(pathOfFileEndTest);
          FileValidator fileValidator = new FileValidator();
          List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
          List<String> infoAbbreviations = allReadFiles.get(0);
          List<String> infoStart = allReadFiles.get(1);
          List<String> infoEnd = allReadFiles.get(2);
          List<Racer> racersExpected = entityMapper.getListRacer(infoAbbreviations,infoStart,infoEnd);
          assertEquals(racersExpected,racers);
      });
  }

  @Test
    public void shouldReturnExceptionWhenFileAbbIsEmpty () {
      assertThrows(RuntimeException.class, () -> {
          List<String> fileAbbreviations = readFile.readFile(abbreviationsEmptyTest);
          List<String> start = readFile.readFile(pathOfFileStartTest);
          List<String> end = readFile.readFile(pathOfFileEndTest);
          FileValidator fileValidator = new FileValidator();
          List<List<String>>allReadFiles = fileValidator.getValidatedList(fileAbbreviations,start,end);
          List<String> infoAbbreviations = allReadFiles.get(0);
          List<String> infoStart = allReadFiles.get(1);
          List<String> infoEnd = allReadFiles.get(2);
          List<Racer> racers = entityMapper.getListRacer(infoAbbreviations,infoStart,infoEnd);
      });
  }
}
