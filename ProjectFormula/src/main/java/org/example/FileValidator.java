package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.App.*;

public class FileValidator {

    App app = new App();

    ReadFile readFile = new ReadFile();

    public List<List> checkReadLists() {
        List<List>allReadFiles = createListReadFilesLists();
        List<List>allTheElements = createSubstringLists();
        validateAllDifferentLists(allReadFiles,allTheElements);
        validateOneBiggerAnother(allReadFiles,allTheElements);
        validateAllListsLengthTheSame(allReadFiles,allTheElements);
    return allReadFiles;
    }

    private List<List> createListReadFilesLists () {
        List<String> fileAbbreviations = readFile.readFile(pathOfAbbreviations);
        List<String> start = readFile.readFile(pathOfFileStart);
        List<String> end = readFile.readFile(pathOfFileEnd);
        List<List>allReadFiles = new ArrayList<>();
        allReadFiles.add(fileAbbreviations);
        allReadFiles.add(start);
        allReadFiles.add(end);
       return allReadFiles;
    }

    private List<List> createSubstringLists() {
        List<List>allReadFiles = createListReadFilesLists();
        List<String> fileAbbreviations = allReadFiles.get(0);
        List<String> start =allReadFiles.get(1);
        List<String> end = allReadFiles.get(2);
                List<String> elementsAbbreviations = fileAbbreviations.stream()
                .map(element -> element.substring(0, 2)).collect(Collectors.toList());
        List<String> elementsEnd = end.stream()
                .map(element -> element.substring(0, 2)).collect(Collectors.toList());
        List<String> elementsStart = start.stream()
                .map(element -> element.substring(0, 2)).collect(Collectors.toList());
        List<List> allTheElements = new ArrayList<>();
        allTheElements.add(elementsAbbreviations);
        allTheElements.add(elementsStart);
        allTheElements.add(elementsEnd);
        return allTheElements;
    }

    private void validateAllDifferentLists(List<List>allReadFiles,List<List> allTheElements) {
        List<String> fileAbbreviations = allReadFiles.get(0);
        List<String> start =allReadFiles.get(1);
        List<String> end = allReadFiles.get(2);
        List<String> elementsAbbreviations = allTheElements.get(0);
        List<String> elementsStart =allTheElements.get(1);
        List<String> elementsEnd = allTheElements.get(2);
        if (elementsAbbreviations.size() < elementsStart.size()) {
            if (elementsAbbreviations.size() < elementsEnd.size()) {
                if (elementsStart.size() > elementsEnd.size()) {
                    for (int i = 0; i < elementsStart.size(); i++) {
                        String elementAbbreviation = elementsStart.get(i).substring(0, 2);
                        if (!elementsAbbreviations.contains(elementAbbreviation)
                                && elementsStart.contains(elementAbbreviation)
                                && elementsEnd.contains(elementAbbreviation)) {
                            start.remove(i);
                            end.remove(i);
                        } else {
                            if (!elementsAbbreviations.contains(elementAbbreviation)
                                    && elementsStart.contains(elementAbbreviation)
                                    && !elementsEnd.contains(elementAbbreviation)) {
                                start.remove(i);
                            }
                        }
                    }
                }
            }
        }
        if (elementsAbbreviations.size() < elementsStart.size()) {
            if (elementsAbbreviations.size() < elementsEnd.size()) {
                if (elementsStart.size() < elementsEnd.size()) {
                    for (int i = 0; i < elementsEnd.size(); i++) {
                        String elementAbbreviation = elementsEnd.get(i).substring(0, 2);
                        if (!elementsAbbreviations.contains(elementAbbreviation)
                                && elementsStart.contains(elementAbbreviation)
                                && elementsEnd.contains(elementAbbreviation)) {
                            start.remove(i);
                            end.remove(i);
                        } else {
                            if (!elementsAbbreviations.contains(elementAbbreviation)
                                    && !elementsStart.contains(elementAbbreviation)
                                    && elementsEnd.contains(elementAbbreviation)) {
                                end.remove(i);
                            }
                        }
                    }
                }
            }
        }
        if (elementsAbbreviations.size() < elementsStart.size()) {
            if (elementsAbbreviations.size() > elementsEnd.size()) {
                if (elementsStart.size() > elementsEnd.size()) {
                    for (int i = 0; i < elementsStart.size(); i++) {
                        String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                        if (elementsAbbreviations.contains(elementAbbreviation)
                                && elementsStart.contains(elementAbbreviation)
                                && !elementsEnd.contains(elementAbbreviation)) {
                            start.remove(i);
                            fileAbbreviations.remove(i);
                        } else if (!elementsAbbreviations.contains(elementAbbreviation)
                                && elementsStart.contains(elementAbbreviation)
                                && !elementsEnd.contains(elementAbbreviation)) {
                            start.remove(i);
                        }
                    }
                }
            }
        }
        if (elementsAbbreviations.size() > elementsStart.size()) {
            if (elementsAbbreviations.size() < elementsEnd.size()) {
                if (elementsStart.size() < elementsEnd.size()) {
                    for (int i = 0; i < elementsEnd.size(); i++) {
                        String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                        if (elementsAbbreviations.contains(elementAbbreviation)
                                && !elementsStart.contains(elementAbbreviation)
                                && elementsEnd.contains(elementAbbreviation)) {
                            end.remove(i);
                            fileAbbreviations.remove(i);
                        } else if (!elementsAbbreviations.contains(elementAbbreviation)
                                && !elementsStart.contains(elementAbbreviation)
                                && elementsEnd.contains(elementAbbreviation)) {
                            start.remove(i);
                        }
                    }
                }
            }
        }
        if (elementsAbbreviations.size() > elementsStart.size()) {
            if (elementsAbbreviations.size() > elementsEnd.size()) {
                if (elementsStart.size() < elementsEnd.size()) {
                    for (int i = 0; i < fileAbbreviations.size(); i++) {
                        String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                        if (elementsAbbreviations.contains(elementAbbreviation)
                                && !elementsStart.contains(elementAbbreviation)
                                && elementsEnd.contains(elementAbbreviation)) {
                            fileAbbreviations.remove(i);
                            end.remove(i);
                        } else if (elementsAbbreviations.contains(elementAbbreviation)
                                && !elementsStart.contains(elementAbbreviation)
                                && !elementsEnd.contains(elementAbbreviation)) {
                            fileAbbreviations.remove(i);
                        }
                    }
                }
            }
        }
        if (elementsAbbreviations.size() > elementsStart.size()) {
            if (elementsAbbreviations.size() > elementsEnd.size()) {
                if (elementsStart.size() > elementsEnd.size()) {
                    for (int i = 0; i < fileAbbreviations.size(); i++) {
                        String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                        if (elementsAbbreviations.contains(elementAbbreviation)
                                && elementsStart.contains(elementAbbreviation)
                                && !elementsEnd.contains(elementAbbreviation)) {
                            fileAbbreviations.remove(i);
                            start.remove(i);
                        } else if (elementsAbbreviations.contains(elementAbbreviation)
                                && !elementsStart.contains(elementAbbreviation)
                                && !elementsEnd.contains(elementAbbreviation)) {
                            fileAbbreviations.remove(i);
                        }
                    }
                }
            }
        }
//        List<List>allCheckedLists = new ArrayList<>();
//        allCheckedLists.add(fileAbbreviations);
//        allCheckedLists.add(start);
//        allCheckedLists.add(end);
//        return allCheckedLists;
    }
    private void validateOneBiggerAnother(List<List>allReadFiles,List<List> allTheElements) {
        List<String> fileAbbreviations = allReadFiles.get(0);
        List<String> start =allReadFiles.get(1);
        List<String> end = allReadFiles.get(2);
        List<String> elementsAbbreviations = allTheElements.get(0);
        List<String> elementsStart =allTheElements.get(1);
        List<String> elementsEnd = allTheElements.get(2);
        if (elementsAbbreviations.size() > elementsStart.size()) {
            if (elementsStart.size() == elementsEnd.size()) {
                for (int i = 0; i < fileAbbreviations.size(); i++) {
                    String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                    if (!elementsStart.contains(elementAbbreviation)
                            && !elementsEnd.contains(elementAbbreviation)) {
                        fileAbbreviations.remove(i);
                    } else if (elementsStart.contains(elementAbbreviation)
                            && !elementsEnd.contains(elementAbbreviation)) {
                        System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                        fileAbbreviations.remove(i);
                        start.remove(i);
                    } else if (!elementsStart.contains(elementAbbreviation)
                            && elementsEnd.contains(elementAbbreviation)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
        if (elementsAbbreviations.size() < elementsStart.size()) {
            if (elementsStart.size() == elementsEnd.size()) {
                for (int i = 0; i < elementsStart.size(); i++) {
                    String elementStart = elementsStart.get(i).substring(0, 2);
                    if (!elementsAbbreviations.contains(elementStart)
                            && !elementsEnd.contains(elementsStart)) {
                        start.remove(i);
                    } else if (elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                        fileAbbreviations.remove(i);
                        start.remove(i);
                    } else if (!elementsAbbreviations.contains(i)
                            && elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    } else if (!elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
        if (elementsStart.size() > elementsAbbreviations.size()) {
            if (elementsAbbreviations.size() == elementsEnd.size()) {
                for (int i = 0; i < elementsStart.size(); i++) {
                    String elementStart = elementsStart.get(i).substring(0, 2);
                    if (!elementsAbbreviations.contains(elementStart)
                            && !elementsEnd.contains(elementStart)) {
                        start.remove(i);
                    } else if (elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                        fileAbbreviations.remove(i);
                        start.remove(i);
                    } else if (!elementsAbbreviations.contains(i)
                            && elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    } else if (!elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
        if (elementsAbbreviations.size() > elementsStart.size()) {
            if (elementsAbbreviations.size() == elementsEnd.size()) {
                for (int i = 0; i < elementsAbbreviations.size(); i++) {
                    String elementAbbreviation = elementsAbbreviations.get(i).substring(0, 2);
                    if (!elementsStart.contains(elementAbbreviation)
                            && !elementsEnd.contains(elementAbbreviation)) {
                        System.out.println();
                        fileAbbreviations.remove(i);
                    } else if (elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                        fileAbbreviations.remove(i);
                        start.remove(i);
                    } else if (!elementsAbbreviations.contains(i)
                            && elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    } else if (!elementsAbbreviations.contains(i)
                            && !elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
        if (elementsEnd.size() > elementsAbbreviations.size()) {
            if (elementsAbbreviations.size() == elementsStart.size()) {
                for (int i = 0; i < elementsEnd.size(); i++) {
                    String elementEnd = elementsStart.get(i).substring(0, 2);
                    if (!elementsAbbreviations.contains(elementEnd)
                            && !elementsStart.contains(elementEnd)) {
                        end.remove(i);
                    } else if (elementsAbbreviations.contains(i)
                            && !elementsStart.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    } else if (!elementsAbbreviations.contains(i)
                            && elementsStart.contains(i)) {
                        System.out.println("Unidentified racer is found out");
                        start.remove(i);
                        end.remove(i);
                    } else if (!elementsAbbreviations.contains(i)
                            && !elementsStart.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
        if (elementsAbbreviations.size() > elementsEnd.size()) {
            if (elementsStart.size() == elementsAbbreviations.size()) {
                for (int i = 0; i < elementsAbbreviations.size(); i++) {
                    String elementAbbreviation = elementsAbbreviations.get(i).substring(0, 2);
                    if (elementsStart.contains(elementAbbreviation)
                            && !elementsEnd.contains(elementAbbreviation)) {
                        System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                        fileAbbreviations.remove(i);
                        start.remove(i);
                    } else if (!elementsStart.contains(i)
                            && elementsEnd.contains(i)) {
                        throw new RuntimeException("The current racer is not on the list");
                    }
                }
            }
        }
//        List<List>allCheckedLists = new ArrayList<>();
//        allCheckedLists.add(fileAbbreviations);
//        allCheckedLists.add(start);
//        allCheckedLists.add(end);
//        return allCheckedLists;
    }
        private void validateAllListsLengthTheSame(List<List>allReadFiles,List<List> allTheElements){
            List<String> fileAbbreviations = allReadFiles.get(0);
            List<String> start =allReadFiles.get(1);
            List<String> end = allReadFiles.get(2);
            List<String> elementsAbbreviations = allTheElements.get(0);
            List<String> elementsStart =allTheElements.get(1);
            List<String> elementsEnd = allTheElements.get(2);
                if (fileAbbreviations.size() == start.size()) {
                    if (fileAbbreviations.size() == end.size()) {
                        for (int i = 0; i < fileAbbreviations.size(); i++) {
                            String elementAbbreviation = fileAbbreviations.get(i).substring(0, 2);
                            if (elementsAbbreviations.contains(elementAbbreviation)
                                    && !elementsEnd.contains(elementAbbreviation)
                                    && elementsStart.contains(elementAbbreviation)) {
                                System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                                fileAbbreviations.remove(i);
                                start.remove(i);
                                end.remove(i);
                            } else if (elementsAbbreviations.contains(elementAbbreviation)
                                    && elementsEnd.contains(elementAbbreviation)
                                    && !elementsStart.contains(elementAbbreviation)) {
                                throw new RuntimeException("The current racer is not on the list");
                            } else if (!elementsAbbreviations.contains(elementAbbreviation)
                                    && elementsEnd.contains(elementAbbreviation)
                                    && elementsStart.contains(elementAbbreviation)) {
                                throw new RuntimeException("The current racer is not on the list");
                            } else if (elementsAbbreviations.contains(elementAbbreviation)
                                    && !elementsEnd.contains(elementAbbreviation)
                                    && !elementsStart.contains(elementAbbreviation)) {
                                System.out.println(elementsAbbreviations.get(i) + "This racer didnt take part");
                                fileAbbreviations.remove(i);
                                start.remove(i);
                                end.remove(i);
                            } else if (!elementsAbbreviations.contains(elementAbbreviation)
                                    && !elementsEnd.contains(elementAbbreviation)
                                    && elementsStart.contains(elementAbbreviation)) {
                                throw new RuntimeException("The current racer is not on the list");
                            } else if (!elementsAbbreviations.contains(elementAbbreviation)
                                    && elementsEnd.contains(elementAbbreviation)
                                    && !elementsStart.contains(elementAbbreviation)) {
                                throw new RuntimeException("The current racer is not on the list");
                            }
                        }
                    }
                }
//            List<List>allCheckedLists = new ArrayList<>();
//            allCheckedLists.add(fileAbbreviations);
//            allCheckedLists.add(start);
//            allCheckedLists.add(end);
//            return allCheckedLists;
        }
    }
