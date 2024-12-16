package software.ulpgc.kata6.architecture.control;

import software.ulpgc.kata6.architecture.io.Input;
import software.ulpgc.kata6.architecture.io.Output;
import software.ulpgc.kata6.architecture.model.Calendary;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

public class CalculateTaskDaysWithRestrictionCommand implements Command {
    public static final String DATE_FROM = "date-from";
    public static final String DATE_TO = "date-to";
    private final Input input;
    private final Output output;
    private final LocalDate fromDate;
    private final LocalDate toDate;

    public CalculateTaskDaysWithRestrictionCommand(Input input, Output output) {
        this.input = input;
        this.output = output;
        this.fromDate = LocalDate.parse(this.input.getInput(DATE_FROM));
        this.toDate = LocalDate.parse(this.input.getInput(DATE_TO));
    }

    @Override
    public void execute() {
        long numberOfDays = new Calendary().getNumberOfDaysBetween(fromDate, toDate);
        int count = 0;
        for (LocalDate date : getDatesFrom()){
            if (numberOfDays < 0) break;
            if (!isExcluded(date)) count++;
            numberOfDays--;
        }
        this.output.setOutput(asJson(count));
    }

    private String asJson(int count) {
        return String.format("{\"days\":%d}", count);
    }

    private boolean isExcluded(LocalDate date) {
        return excludedDays().contains(DayOfWeek.from(date));
    }

    private List<DayOfWeek> excludedDays() {
        return new Calendary().getDaysOfWeekFrom(this.input.getInput("excluded-days").split(","));
    }

    private Iterable<LocalDate> getDatesFrom(){
        return new Calendary().getDatesFrom(fromDate);
    }
}