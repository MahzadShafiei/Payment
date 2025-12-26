package com.mahzad.payment.services;

import com.mahzad.payment.enums.Menu;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Report {

    LocalDate fromDate;
    LocalDate toDate;
    LocalTime fromTime;
    LocalTime toTime;
    List<ReportModel>reportModels  = new ArrayList<ReportModel>();
    String resultReport = "";

    public Report(LocalDate fromDate, LocalDate toDate, LocalTime fromTime, LocalTime toTime) {
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }

    public void generateReport() throws IOException
    {
        customizeReportModel();
        for(Menu menu : Menu.values())
            if(menu != Menu.Report)
                analyseMenuReport(menu);

        System.out.println("---------------Report-----------------\n"+resultReport);
    }

    private void analyseMenuReport(Menu  menu)
    {
        int counter = 0;
        double totalAmount = 0;
        List<ReportModel> customizeModels = reportModels.stream().filter(c-> c.action.equals(menu.getMenuName())).collect(Collectors.toList());
        for (ReportModel reportModel : customizeModels) {
            LocalDateTime dateTime = LocalDateTime.parse(reportModel.date);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            if((date.isBefore( toDate) && date.isAfter(fromDate)) || date.isEqual(fromDate) || date.isEqual(toDate))
                if((time.isBefore( toTime) && time.isAfter(fromTime)) || time.equals(fromTime) || time.equals(toTime))
                {
                    counter++;
                    totalAmount += Double.parseDouble(reportModel.amount);
                }

        }
        resultReport += menu.getMenuName() + " -> Count: " + counter+ ", TotalAmout: " + totalAmount + "\n";
    }

    private void customizeReportModel() throws IOException
    {
        File file = new File("report.txt");
        Scanner filescanner = new Scanner(file);

        while (filescanner.hasNextLine()) {
            ReportModel reportModel = new ReportModel();
            String line = filescanner.nextLine();
            String[] dataList = line.split(",");
            for (String item : dataList) {
                int valueIndex = item.indexOf(":");
                switch (item.substring(0, valueIndex)) {
                    case "Action":
                        reportModel.action = item.substring(valueIndex + 1);
                        break;
                    case "Result":
                        reportModel.result = item.substring(valueIndex + 1);
                        break;
                    case "Bank":
                        reportModel.bank = item.substring(valueIndex + 1);
                        break;
                    case "Pan":
                        reportModel.pan = item.substring(valueIndex + 1);
                        break;
                    case "Date":
                        reportModel.date = item.substring(valueIndex + 1);
                    case "Amount":
                        reportModel.amount = item.substring(valueIndex + 1);
                        break;
                }
            }
            reportModels.add(reportModel);
        }

        filescanner.close();
    }
}
