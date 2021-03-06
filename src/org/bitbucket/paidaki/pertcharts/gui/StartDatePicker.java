package org.bitbucket.paidaki.pertcharts.gui;

import org.bitbucket.paidaki.pertcharts.application.Activity;
import org.bitbucket.paidaki.pertcharts.application.util.Util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StartDatePicker extends CustomDatePicker {

    private Activity activity;

    public StartDatePicker(Activity activity) {
        super();
        this.activity = activity;
        this.setValue(activity.getStartDate());
    }

    public StartDatePicker(Activity activity, DateTimeFormatter dateFormatter) {
        super(dateFormatter);
        this.activity = activity;
        this.setValue(activity.getStartDate());
    }

    @Override
    protected boolean dateIsAllowed(LocalDate testDate) {
        return Util.validateAfterDate(testDate, activity.getProjectStartDate()) && testDate.isAfter(Util.maxEndDate(activity.getPredecessors()));
    }

    @Override
    protected boolean dateIsBetweenStartEnd(LocalDate testDate) {
        return (testDate.isEqual(activity.getEndDate()) && !activity.getStartDate().equals(activity.getEndDate())) || Util.isBetweenDates(testDate, activity.getStartDate(), activity.getEndDate());
    }
}
