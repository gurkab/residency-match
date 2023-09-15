package com.gurkab.residencymatch.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class Program {
    private String name;
    private int positions;
    private List<String> list;
    private List<Applicant> matches;

    public Program(String name, int positions, List<String> list) {
        this.name = name;
        this.positions = positions;
        this.list = list;
        this.matches = new ArrayList<>();
    }

    public void tryToMatch(Applicant applicant) {
        if (list.contains(applicant.getName())) {
            System.out.print("(" + list.indexOf(applicant.getName()) + ") ");
            matches.add(applicant);
            applicant.setMatched(true);
            removeMatches();
        } else {
            System.out.print("(unranked) ");
        }
    }

    public void removeMatches() {
        while (matches.size() > positions) {
            Applicant reject = matches.stream()
                    .max((a1, a2) -> Integer.compare(list.indexOf(a1.getName()), list.indexOf(a2.getName())))
                    .orElse(null);
            if (reject != null) {
                reject.setMatched(false);
                matches.remove(reject);
                System.out.print("[remove " + reject.getName() + "] ");
            }
        }
    }

    public String description() {
        StringBuilder str = new StringBuilder(name + ": ");
        String matchedApplicants = matches.stream()
                .map(Applicant::getName)
                .collect(Collectors.joining(", "));
        str.append(matchedApplicants);
        if (matches.size() < positions) {
            if (!matches.isEmpty()) {
                str.append(" ");
            }
            str.append("(").append(positions - matches.size()).append(" unfilled)");
        }
        return str.toString();
    }

    public String getName() {
        return name;
    }
}
