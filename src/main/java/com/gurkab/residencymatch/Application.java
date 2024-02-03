package com.gurkab.residencymatch;

import com.gurkab.residencymatch.model.ProgramAttributes;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Application {

    public static void main(String[] args) {
        Map<String, List<String>> applicantsMap = new HashMap<>();
        applicantsMap.put("Brian", List.of("city", "general"));
        applicantsMap.put("Rachel", List.of("city", "mercy", "state"));
        applicantsMap.put("Alex", List.of("city", "mercy"));
        applicantsMap.put("Katherine", List.of("mercy", "city", "general", "state"));
        applicantsMap.put("Dina", List.of("city", "mercy", "state", "general"));
        applicantsMap.put("Michael", List.of("city", "general", "mercy", "state"));
        applicantsMap.put("Aaron", List.of("city", "mercy", "state", "general"));
        applicantsMap.put("Emily", List.of("state", "city", "mercy", "general"));

        Map<String, ProgramAttributes> programsMap = new HashMap<>();
        programsMap.put(
            "city",
            new ProgramAttributes(2, List.of("Aaron", "Emily", "Dina", "Brian", "Rachel", "Alex", "Katherine", "Michael"))
        );
        programsMap.put("general", new ProgramAttributes(2, List.of("Rachel", "Dina", "Emily", "Brian", "Alex", "Katherine", "Aaron")));
        programsMap.put("mercy", new ProgramAttributes(2, List.of("Alex", "Aaron", "Rachel")));
        programsMap.put(
            "state",
            new ProgramAttributes(2, List.of("Rachel", "Dina", "Brian", "Alex", "Emily", "Michael", "Katherine", "Aaron"))
        );

        Matcher matcher = Matcher.run(applicantsMap, programsMap);
        System.out.println(matcher.resultsDescription());
    }
}
