package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.example.App.*;

public class FileValidator {

    ReadFile readFile = new ReadFile();

    public List<List<String>>getValidatedList(List<String> fileAbbreviations,List<String> start,List<String> end) {
        validateAllDifferentLists(fileAbbreviations,start,end);
        validateOneBiggerAnother(fileAbbreviations,start,end);
        validateAllListsLengthTheSame(fileAbbreviations,start,end);
        List<List<String>>allReadFiles = new ArrayList<>();
        allReadFiles.add(fileAbbreviations);
        allReadFiles.add(start);
        allReadFiles.add(end);
        return allReadFiles;
    }

    private List<String> getListOfSubstrings(List<String>list) {
        return list.stream().map(element->element.substring(0,2)).collect(Collectors.toList());
    }

    private void validateAllDifferentLists(List<String> fileAbbreviations,List<String> start,List<String> end) {
        List<String> substringsAbbreviations = getListOfSubstrings(fileAbbreviations);
        List<String> substringsStart =getListOfSubstrings(start);
        List<String> substringsEnd = getListOfSubstrings(end);
        if (substringsAbbreviations.size() < substringsStart.size()
                && substringsAbbreviations.size() < substringsEnd.size()
                && substringsStart.size() > substringsEnd.size()) {
            for (int i = 0; i < substringsStart.size(); ) {
                String substringAbbreviation = substringsStart.get(i);
                if (!substringsAbbreviations.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    start.remove(i);
                    end.remove(i);
                    substringsStart.remove(i);
                    substringsEnd.remove(i);
                } else {
                    if (!substringsAbbreviations.contains(substringAbbreviation)
                            && !substringsEnd.contains(substringAbbreviation)) {
                        start.remove(i);
                        substringsStart.remove(i);
                    } else if (substringsAbbreviations.contains(substringAbbreviation)
                            && substringsStart.contains(substringAbbreviation)
                            && substringsEnd.contains(substringAbbreviation)) {
                        i++;
                    }
                }
            }
        }
        if (substringsAbbreviations.size() < substringsStart.size()
                && substringsAbbreviations.size() < substringsEnd.size()
                && substringsStart.size() < substringsEnd.size()) {
            for (int i = 0; i < substringsEnd.size(); ) {
                String substringAbbreviation = substringsEnd.get(i);
                if (!substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)) {
                    start.remove(i);
                    end.remove(i);
                    substringsStart.remove(i);
                    substringsEnd.remove(i);
                } else {
                    if (!substringsAbbreviations.contains(substringAbbreviation)
                            && !substringsStart.contains(substringAbbreviation)) {
                        end.remove(i);
                        substringsEnd.remove(i);
                    } else if (substringsAbbreviations.contains(substringAbbreviation)
                            && substringsStart.contains(substringAbbreviation)
                            && substringsEnd.contains(substringAbbreviation)) {
                        i++;
                    }
                }
            }
        }
        if (substringsAbbreviations.size() < substringsStart.size()
                && substringsAbbreviations.size() > substringsEnd.size()) {
            for (int i = 0; i < substringsStart.size(); ) {
                String substringAbbreviation = substringsStart.get(i);
                if (substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    start.remove(i);
                    fileAbbreviations.remove(i);
                    substringsStart.remove(i);
                    substringsAbbreviations.remove(i);
                } else if (!substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    start.remove(i);
                    substringsStart.remove(i);
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsAbbreviations.size() > substringsStart.size()
                && substringsAbbreviations.size() < substringsEnd.size()) {
            for (int i = 0; i < substringsEnd.size(); ) {
                String substringAbbreviation = substringsEnd.get(i);
                if (!substringsStart.contains(substringAbbreviation)
                        && substringsAbbreviations.contains(substringAbbreviation)) {
                    end.remove(i);
                    fileAbbreviations.remove(i);
                    substringsEnd.remove(i);
                    substringsAbbreviations.remove(i);
                } else if (!substringsStart.contains(substringAbbreviation)
                        && !substringsAbbreviations.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsAbbreviations.size() > substringsStart.size()
                && substringsAbbreviations.size() > substringsEnd.size()
                && substringsStart.size() < substringsEnd.size()) {
                    for (int i = 0; i < substringsAbbreviations.size();) {
                        String substringAbbreviation = fileAbbreviations.get(i);
                        if (!substringsStart.contains(substringAbbreviation)
                                && substringsEnd.contains(substringAbbreviation)) {
                            throw new RuntimeException("The current racer is not on the list");
                        } else if (!substringsStart.contains(substringAbbreviation)
                                && !substringsEnd.contains(substringAbbreviation)) {
                            System.out.println(fileAbbreviations.get(i) + "This racer didnt take part");
                            fileAbbreviations.remove(i);
                            substringsAbbreviations.remove(i);
                        }else if (substringsAbbreviations.contains(substringAbbreviation)
                                && substringsStart.contains(substringAbbreviation)
                                && substringsEnd.contains(substringAbbreviation)){
                            i++;
                        }
                    }
                }
        if (substringsAbbreviations.size() > substringsStart.size()
                && substringsAbbreviations.size() > substringsEnd.size()
                && substringsStart.size() > substringsEnd.size()) {
                    for (int i = 0; i < substringsAbbreviations.size();) {
                        String substringAbbreviation = substringsAbbreviations.get(i);
                        if (substringsStart.contains(substringAbbreviation)
                                && !substringsEnd.contains(substringAbbreviation)) {
                            System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                            fileAbbreviations.remove(i);
                            start.remove(i);
                            substringsAbbreviations.remove(i);
                            substringsStart.remove(i);
                        }   else if (!substringsStart.contains(substringAbbreviation)
                                    && substringsEnd.contains(substringAbbreviation)) {
                            throw new RuntimeException("The current racer is not on the list");
                        } else if (!substringsStart.contains(substringAbbreviation)
                                && !substringsEnd.contains(substringAbbreviation)) {
                            System.out.println(fileAbbreviations.get(i) + "This racer didnt take part");
                            fileAbbreviations.remove(i);
                            substringsAbbreviations.remove(i);
                        }else if (substringsAbbreviations.contains(substringAbbreviation)
                                && substringsStart.contains(substringAbbreviation)
                                && substringsEnd.contains(substringAbbreviation)){
                            i++;
                        }
                    }
                }
            }

    private void validateOneBiggerAnother(List<String> fileAbbreviations,List<String> start,List<String> end) {
        List<String> substringsAbbreviations = getListOfSubstrings(fileAbbreviations);
        List<String> substringsStart = getListOfSubstrings(start);
        List<String> substringsEnd = getListOfSubstrings(end);
        if (substringsAbbreviations.size() > substringsStart.size() && substringsStart.size() == substringsEnd.size()) {
            for (int i = 0; i < substringsAbbreviations.size(); ) {
                String substringAbbreviation = substringsAbbreviations.get(i);
                if (!substringsStart.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer didnt take part");
                    fileAbbreviations.remove(i);
                    substringsAbbreviations.remove(i);
                } else if (substringsStart.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                    fileAbbreviations.remove(i);
                    start.remove(i);
                    substringsAbbreviations.remove(i);
                    substringsStart.remove(i);
                } else if (!substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsAbbreviations.size() < substringsStart.size()
                && substringsStart.size() == substringsEnd.size()) {
            for (int i = 0; i < substringsStart.size(); ) {
                String substringAbbreviation = substringsStart.get(i);
                String substringEnd = substringsEnd.get(i);
                if (!substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    start.remove(i);
                    substringsStart.remove(i);
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(i)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                    fileAbbreviations.remove(i);
                    start.remove(i);
                    substringsAbbreviations.remove(i);
                    substringsStart.remove(i);
                } else if (!substringsAbbreviations.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (!substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsStart.contains(substringEnd)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsStart.size() > substringsAbbreviations.size()
                && substringsAbbreviations.size() == substringsEnd.size()) {
            for (int i = 0; i < substringsStart.size(); ) {
                String substringAbbreviation = substringsStart.get(i);
                if (!substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    start.remove(i);
                    substringsStart.remove(i);
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                    fileAbbreviations.remove(i);
                    start.remove(i);
                    substringsAbbreviations.remove(i);
                    substringsStart.remove(i);
                } else if (!substringsAbbreviations.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsAbbreviations.size() > substringsStart.size()
                && substringsAbbreviations.size() == substringsEnd.size()) {
            for (int i = 0; i < substringsAbbreviations.size(); ) {
                String substringAbbreviation = substringsAbbreviations.get(i);
                String substringEnd = substringsEnd.get(i);
                if (!substringsStart.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer didnt take part");
                    fileAbbreviations.remove(i);
                    substringsAbbreviations.remove(i);
                } else if (substringsAbbreviations.contains(substringEnd)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                    fileAbbreviations.remove(i);
                    start.remove(i);
                    substringsAbbreviations.remove(i);
                    substringsStart.remove(i);
                } else if (!substringsAbbreviations.contains(substringEnd)
                        && substringsEnd.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (!substringsAbbreviations.contains(i)
                        && !substringsEnd.contains(i)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsEnd.size() > substringsAbbreviations.size()
                && substringsAbbreviations.size() == substringsStart.size()) {
            for (int i = 0; i < substringsEnd.size(); ) {
                String substringAbbreviation = substringsStart.get(i);
                if (!substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsStart.contains(substringAbbreviation)) {
                    end.remove(i);
                    substringsEnd.remove(i);
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && !substringsStart.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (!substringsAbbreviations.contains(i)
                        && substringsStart.contains(i)) {
                    System.out.println("Unidentified racer is found out");
                    start.remove(i);
                    end.remove(i);
                    substringsStart.remove(i);
                    substringsEnd.remove(i);
                } else if (!substringsAbbreviations.contains(i)
                        && !substringsStart.contains(i)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
        if (substringsAbbreviations.size() > substringsEnd.size()
                && substringsStart.size() == substringsAbbreviations.size()) {
            for (int i = 0; i < substringsAbbreviations.size(); ) {
                String substringAbbreviation = substringsAbbreviations.get(i);
                if (substringsStart.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                    fileAbbreviations.remove(i);
                    start.remove(i);
                    substringsAbbreviations.remove(i);
                    substringsStart.remove(i);
                } else if (!substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    throw new RuntimeException("The current racer is not on the list");
                } else if (!substringsStart.contains(substringAbbreviation)
                        && !substringsEnd.contains(substringAbbreviation)) {
                    System.out.println(fileAbbreviations.get(i) + "This didnt take part");
                    fileAbbreviations.remove(i);
                    substringsAbbreviations.remove(i);
                } else if (substringsAbbreviations.contains(substringAbbreviation)
                        && substringsStart.contains(substringAbbreviation)
                        && substringsEnd.contains(substringAbbreviation)) {
                    i++;
                }
            }
        }
    }

        private void validateAllListsLengthTheSame(List<String> fileAbbreviations,List<String> start,List<String> end){
            List<String> substringsAbbreviations = getListOfSubstrings(fileAbbreviations);
            List<String> substringsStart =getListOfSubstrings(start);
            List<String> substringsEnd = getListOfSubstrings(end);
                if (fileAbbreviations.size() == start.size()
                        && fileAbbreviations.size() == end.size()) {
                    for (int i = 0; i < fileAbbreviations.size(); ) {
                        String substringAbbreviation = substringsAbbreviations.get(i);
                        String substringStart = substringsStart.get(i);
                        String substringEnd = substringsEnd.get(i);
                        if (!substringsEnd.contains(substringAbbreviation)
                                && substringsStart.contains(substringAbbreviation)) {
                            System.out.println(fileAbbreviations.get(i) + "This racer got lost");
                            fileAbbreviations.remove(i);
                            start.remove(i);
                            substringsAbbreviations.remove(i);
                            substringsStart.remove(i);
                        } else if (substringsEnd.contains(substringAbbreviation)
                                && !substringsStart.contains(substringAbbreviation)) {
                            throw new RuntimeException("The current racer is not on the list");
                        } else if (!substringsAbbreviations.contains(substringEnd)
                                && substringsEnd.contains(substringEnd)
                                && substringsStart.contains(substringEnd)) {
                            throw new RuntimeException("Unidentified racer is found out");
                        } else if (substringsAbbreviations.contains(substringAbbreviation)
                                && !substringsEnd.contains(substringAbbreviation)
                                && !substringsStart.contains(substringAbbreviation)) {
                            System.out.println(substringsAbbreviations.get(i) + "This racer didnt take part");
                            fileAbbreviations.remove(i);
                            substringsAbbreviations.remove(i);
                        } else if (!substringsAbbreviations.contains(substringStart)
                                && !substringsEnd.contains(substringStart)) {
                            throw new RuntimeException("The current racer is not on the list");
                        } else if (!substringsAbbreviations.contains(substringEnd)
                                && !substringsStart.contains(substringEnd)) {
                            throw new RuntimeException("The current racer is not on the list");
                        } else if (substringsAbbreviations.contains(substringAbbreviation)
                                && substringsStart.contains(substringAbbreviation)
                                && substringsEnd.contains(substringAbbreviation)) {
                            i++;
                        }
                    }
                }
        }
    }
