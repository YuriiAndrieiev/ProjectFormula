package org.example;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;


public class OutputLinesList {

    public List createListOutputines () {
        EntityMapper entityMapper = new EntityMapper();
        List <Duration> timeLoopList = entityMapper.createTimeLoopList();
        List <String> racers = entityMapper.createListRacers();
        List<String> cars = entityMapper.CreateListCars();
        List<OutputLine> consoleLineList = new ArrayList<>();
        for (int i = 0; i < racers.size(); i++) {
            String racer = racers.get(i);
            String car = cars.get(i);
            Duration timeLoop = timeLoopList.get(i);
            OutputLine outputLine = new OutputLine(racer, car, timeLoop);
            consoleLineList.add(outputLine);
        }
        List<OutputLine> consoleLinesSort = new ArrayList<>();
        consoleLineList = consoleLineList.stream()
                .sorted(Comparator.comparing(OutputLine::getTimeLoop))
                .collect(Collectors.toList());
        for (int i = consoleLineList.size()-1; i >= 0; i--) {
            consoleLinesSort.add(consoleLineList.get(i));
        }
        return consoleLinesSort ;
    }
}
