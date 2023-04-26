package org.example;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class EntityMapper {
   FileValidator fileValidator = new FileValidator();

    public List<Racer> getListRacer(List<String>infoAbbreviations,List<String>infoStart,List<String>infoEnd) {
        List<Racer> racers = new ArrayList<>();
        List<String> abbreviationsWithoutAbb = new ArrayList<>();
        infoAbbreviations.forEach(string -> abbreviationsWithoutAbb.add(string.substring(4)));
        List<Duration> timeLoopList =getTimeLoopList(infoStart,infoEnd);
        for (int i = 0;  i < abbreviationsWithoutAbb.size(); i++) {
            String[] element = abbreviationsWithoutAbb.get(i).split("_");
            Duration timeLoop = timeLoopList.get(i);
            for (int j = 0; j < element.length; j++) {
                String name = element[0];
                StringBuilder carStringbuilder = new StringBuilder(element[1]);
                String car = carStringbuilder.toString();
                if (j == element.length-1) {
                    Racer racer = new Racer(name, car, timeLoop);
                    racers.add(racer);
                }
            }
        }
        racers = racers.stream()
                .sorted(Comparator.comparing(Racer::getTimeLoop).reversed())
                .collect(Collectors.toList());
        return racers;
    }

    private List<Duration> getTimeLoopList(List<String>infoStart,List<String>infoEnd) {
        List<String> endDates = new ArrayList<>();
        List<String> startDates = new ArrayList<>();
        infoEnd.forEach(string -> endDates.add(string.substring(3)));
        infoStart.forEach(string -> startDates.add(string.substring(3)));
        List<Duration> timeLoopList = new ArrayList<>();
        for (int i = 0; i < startDates.size(); i++) {
            DateTimeFormatter time = DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss.SSS");
            LocalDateTime startLoop = LocalDateTime.parse(startDates.get(i), time);
            LocalDateTime endLoop = LocalDateTime.parse(endDates.get(i), time);
            Duration timeLoop = Duration.between(endLoop, startLoop);
            timeLoopList.add(timeLoop);
        }
        timeLoopList.stream().sorted().collect(Collectors.toList());
        return timeLoopList;
    }
}
