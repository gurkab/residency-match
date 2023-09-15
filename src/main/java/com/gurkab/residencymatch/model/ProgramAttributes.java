package com.gurkab.residencymatch.model;

import lombok.Data;

import java.util.List;

@Data
public class ProgramAttributes {
    private int positions;
    private List<String> list;

    public ProgramAttributes(int positions, List<String> list) {
        this.positions = positions;
        this.list = list;
    }
}
