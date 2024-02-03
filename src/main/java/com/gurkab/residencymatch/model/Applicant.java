package com.gurkab.residencymatch.model;

import java.util.List;
import lombok.Data;

@Data
public class Applicant {

    private String name;
    private List<String> list;
    private boolean matched;
    private int rankingPosition;

    public Applicant(String name, List<String> list) {
        this.name = name;
        this.list = list;
        this.matched = false;
        this.rankingPosition = -1;
    }

    public boolean isExhaustedPrograms() {
        return rankingPosition >= list.size() - 1;
    }

    public String nextProgram() {
        if (isExhaustedPrograms()) {
            throw new IllegalStateException("No ranked programs available!");
        }
        rankingPosition++;
        return list.get(rankingPosition);
    }
}
