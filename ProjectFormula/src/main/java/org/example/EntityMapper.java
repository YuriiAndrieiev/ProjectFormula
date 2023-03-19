package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class EntityMapper {

    public List createTimeLoopList() {
        ReadFile readFile = new ReadFile();
        String wayOfFileStart = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\start.log";
        String wayOfFileEnd = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\end.log";
        List<String>end = readFile.readFile(wayOfFileEnd);
        List<String>start = readFile.readFile(wayOfFileStart);
        List<String> endDates = new ArrayList<>();
        List<String> startDates = new ArrayList<>();
        end.forEach(string -> endDates.add(string.substring(3)));
        start.forEach(string -> startDates.add(string.substring(3)));
        List<Duration> timeLoopList = new ArrayList<>();    for (int i = 0; i < startDates.size(); i++) {
            DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
            LocalDateTime startLoop = LocalDateTime.parse(startDates.get(i), time);
            LocalDateTime endLoop = LocalDateTime.parse(endDates.get(i), time);
            Duration timeLoop = Duration.between(endLoop, startLoop);
            timeLoopList.add(timeLoop);    }
        timeLoopList.stream().sorted().collect(Collectors.toList());    return timeLoopList;
    }

    public List<String> createListRacers() {
        ReadFile readFiles = new ReadFile();
        String abbreviations = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\abbreviations.txt";
        List<String> fileAbbreviations = readFiles.readFile(abbreviations);
        List<String> racers = new ArrayList<>();
        List<String> abbreviationsWithoutAbb = new ArrayList<>();
        fileAbbreviations.forEach(string -> abbreviationsWithoutAbb.add(string.substring(4)));
        for (int i = 0;  i < abbreviationsWithoutAbb.size(); i++) {
            String[] element = abbreviationsWithoutAbb.get(i).split("_");
            for (int j = 0; j < element.length; j++) {
                if (j == 0) {
                String racer = element[j];
                racers.add(racer);
                }
            }
        }
        return racers;
    }

    public List<String> CreateListCars() {
        ReadFile readFiles = new ReadFile();
        String abbreviations = "C:\\projects\\IdeaProjects\\ProjectFormula\\ProjectFormula\\src\\main\\resources\\abbreviations.txt";
        List<String> abbreviationsTxt = readFiles.readFile(abbreviations);
        List<String> abbreviationsWithoutAbb = new ArrayList<>();
        List<String> cars = new ArrayList<>();
        abbreviationsTxt.forEach(string -> abbreviationsWithoutAbb.add(string.substring(4)));
        for (int i = 0; i < abbreviationsWithoutAbb.size(); i++) {
            String[] element = abbreviationsWithoutAbb.get(i).split("_");
            for (int j = 1; j < element.length; j++) {
                StringBuilder car = new StringBuilder(element[j]);
                String carString = car.toString();
                cars.add(carString);
            }
        }
        return cars;
    }
}
