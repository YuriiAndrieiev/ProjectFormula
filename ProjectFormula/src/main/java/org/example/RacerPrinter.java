package org.example;

import java.util.ArrayList;
import java.util.List;


public class RacerPrinter {

    EntityMapper entityMapper = new EntityMapper();
    public List<String> racerTabPrint (List<Racer> racers) {
        List<String>outputList = new ArrayList<>();
        for (int i = 0; i < racers.size(); i++) {
            int pos = i+1;
            StringBuilder racerStringBuilder = new StringBuilder(pos +racers.get(i).getRacer());
            while (racerStringBuilder.length() < 27) {
                racerStringBuilder.append(" ");
            }
            String racer = racerStringBuilder.toString();
            StringBuilder car = new StringBuilder("|" + racers.get(i).getCar());
            while (car.length() < 36) {
                car.append(" ");
            }
            String carString = car.toString();
            String timeLoopString = racers.get(i).getTimeLoop().toString();
            char[] timeLoopArray = timeLoopString.toCharArray();
            StringBuilder timeLoopStringbuilder = new StringBuilder("|");
            for (int j = 0; j < timeLoopArray.length - 1; j++) {
                if (j != 0 && j != 1 && j != 2) {
                    if (timeLoopArray[j] == 'M') {
                        timeLoopStringbuilder.append(":");
                    } else if (timeLoopArray[j] == '-') {
                        timeLoopStringbuilder.append("");
                    } else {
                        timeLoopStringbuilder.append(timeLoopArray[j]);
                    }
                }
            }
            String loopTime = timeLoopStringbuilder.toString();
            String output = racer.concat(carString + loopTime);
            if (outputList.size()==15) {
                outputList.add("------------------------------------------------------------------------");
            }
            outputList.add(output);
        }
        return outputList;
    }
}
