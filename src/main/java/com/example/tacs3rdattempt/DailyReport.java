package com.example.tacs3rdattempt;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DailyReport {
    Date date;
    List<DailyReportLine > dailyReportLineList = new ArrayList<>() ;

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<DailyReportLine> getDailyReportLineList() {
        return dailyReportLineList;
    }

    public void setDailyReportLineList(List<DailyReportLine> dailyReportLineList) {
        this.dailyReportLineList = dailyReportLineList;
    }
}
