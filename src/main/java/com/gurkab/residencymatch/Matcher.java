package com.gurkab.residencymatch;

import com.gurkab.residencymatch.model.Applicant;
import com.gurkab.residencymatch.model.Program;
import com.gurkab.residencymatch.model.ProgramAttributes;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Matcher {

    private final List<Applicant> applicants;
    private final Map<String, Program> programs;

    public static Matcher run(Map<String, List<String>> applicantsMap, Map<String, ProgramAttributes> programsMap) {
        List<Applicant> applicants = applicantsMap
            .entrySet()
            .stream()
            .map(entry -> new Applicant(entry.getKey(), entry.getValue()))
            .collect(Collectors.toList());

        Map<String, Program> programs = programsMap
            .entrySet()
            .stream()
            .collect(
                Collectors.toMap(
                    Map.Entry::getKey,
                    entry -> new Program(entry.getKey(), entry.getValue().getPositions(), entry.getValue().getList())
                )
            );

        return new Matcher(applicants, programs).run();
    }

    public Matcher(List<Applicant> applicants, Map<String, Program> programs) {
        this.applicants = applicants;
        this.programs = programs;
    }

    public Matcher run() {
        int round = 0;
        while (!remainingApplicants().isEmpty()) {
            round++;
            System.out.print("Round #" + round + " (");
            System.out.print(remainingApplicants().stream().map(Applicant::getName).collect(Collectors.joining(", ")));
            System.out.println(")");

            remainingApplicants()
                .forEach(applicant -> {
                    System.out.print("  " + applicant.getName() + ": ");
                    while (!applicant.isExhaustedPrograms() && !applicant.isMatched()) {
                        Program program = programs.get(applicant.nextProgram());
                        System.out.print(program.getName() + " ");
                        program.tryToMatch(applicant);
                    }
                    System.out.println();
                });
        }
        return this;
    }

    public List<Applicant> remainingApplicants() {
        return applicants
            .stream()
            .filter(applicant -> !applicant.isMatched() && !applicant.isExhaustedPrograms())
            .collect(Collectors.toList());
    }

    public String resultsDescription() {
        StringBuilder str = new StringBuilder("\nMatch results:\n");
        String programDescriptions = programs.values().stream().map(Program::description).collect(Collectors.joining("\n"));
        str.append(programDescriptions);
        String unmatchedApplicants = applicants
            .stream()
            .filter(applicant -> !applicant.isMatched())
            .map(Applicant::getName)
            .collect(Collectors.joining(", "));
        str.append("\nUnmatched: ").append(unmatchedApplicants);
        return str.toString();
    }
}
