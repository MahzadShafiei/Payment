package com.mahzad.payment.services;

import com.github.mfathi91.time.PersianDate;
import com.mahzad.payment.enums.Menu;
import com.mahzad.payment.enums.ReportType;
import common.ConfigLoadException;

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

    Scanner scanner = new Scanner(System.in);
    ReportType reportType;
    LocalDate fromDate;
    LocalDate toDate;
    LocalDate byTimeDate;
    LocalTime fromTime;
    LocalTime toTime;
    List<ReportModel> reportModels = new ArrayList<ReportModel>();
    String resultReport = "";

    public Report() {
    }

    public void reportProcess() {
        try {
            for (ReportType report : ReportType.values())
                System.out.println(report.getReportCode() + ". " + report.getReportName());

            System.out.println("Please return you desired report code: ");
            int selectedReportCode = Integer.parseInt(scanner.nextLine());
            ReportType selectedReport = ReportType.fromCode(selectedReportCode);

            switch (selectedReport) {
                case Daily:
                    System.out.println("Please enter the from date (YYYY-MM-DD): ");
                    PersianDate persianFromDate = PersianDate.parse(scanner.nextLine());
                    this.fromDate = persianFromDate.toGregorian();

                    System.out.println("Please enter the to date (YYYY-MM-DD): ");
                    PersianDate persianToDate = PersianDate.parse(scanner.nextLine());
                    this.toDate = persianToDate.toGregorian();

                    break;

                case ByTime:

                    System.out.println("Please enter the date (YYYY-MM-DD): ");
                    PersianDate persianByTimeDate = PersianDate.parse(scanner.nextLine());
                    this.byTimeDate = persianByTimeDate.toGregorian();

                    System.out.println("Please enter the from time: ");
                    this.fromTime = LocalTime.parse(scanner.nextLine());

                    System.out.println("Please enter the to time: ");
                    this.toTime = LocalTime.parse(scanner.nextLine());

                    break;

                default:
                    break;
            }
            reportType = selectedReport;
            generateReport();

        } catch (IOException e) {
            throw new ConfigLoadException("Failed to log report", e);
        } catch (Exception e) {
            throw new ConfigLoadException(e.getMessage(), e);
        }
    }

    public void generateReport() throws IOException {
        customizeReportModel();
        for (Menu menu : Menu.values())
            if (menu != Menu.Report)
                analyseMenuReport(menu);

        System.out.println("---------------Report-----------------\n" + resultReport);
    }

    private void analyseMenuReport(Menu menu) {
        int counter = 0;
        double totalAmount = 0;
        List<ReportModel> customizeModels = reportModels.stream().filter(c -> c.action.equals(menu.getMenuName())).collect(Collectors.toList());
        for (ReportModel reportModel : customizeModels) {
            LocalDateTime dateTime = LocalDateTime.parse(reportModel.date);
            LocalDate date = dateTime.toLocalDate();
            LocalTime time = dateTime.toLocalTime();

            if ((reportType == ReportType.Daily &&
                    ((date.isBefore(toDate) && date.isAfter(fromDate)) || date.isEqual(fromDate) || date.isEqual(toDate))) ||
                    reportType == ReportType.ByTime && date.isEqual(byTimeDate) &&
                            ((time.isBefore(toTime) && time.isAfter(fromTime)) || time.equals(fromTime) || time.equals(toTime))) {
                counter++;
                totalAmount += Double.parseDouble(reportModel.amount);
            }

        }
        resultReport += menu.getMenuName() + " -> Count: " + counter + ", TotalAmout: " + totalAmount + "\n";
    }

    private void customizeReportModel() throws IOException {
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
