package application;

import entities.Department;
import entities.HourContract;
import entities.Worker;
import entities.enums.WorkerLevel;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Locale.setDefault(Locale.US);
        Scanner scanner = new Scanner(System.in);


        String departmentName, workerName, level;
        double baseSalary;

        System.out.print("Enter department's name: ");
        departmentName = scanner.next();

        System.out.println("Enter worker data: ");

        System.out.print("Name: ");
        workerName = scanner.next();

        System.out.print("Level: ");
        level = scanner.next();

        System.out.print("Base salary: ");
        baseSalary = scanner.nextDouble();

        Worker worker = new Worker(
                workerName,
                WorkerLevel.valueOf(level),
                baseSalary,
                new Department(departmentName)
        );

        int numberOfContracts;
        System.out.print("How many contracts to this worker? ");
        numberOfContracts = scanner.nextInt();

        String stringDate;
        double valuePerHour;
        int hours;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");

        for (int i = 0; i < numberOfContracts; i++) {
            System.out.printf("Enter contract #%d data: ", i + 1);

            System.out.print("Date (DD/MM/YYYY): ");
            stringDate = scanner.next();
            Date date = null;
            try {
                date = formatter.parse(stringDate);
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }

            System.out.print("Value per hour: ");
            valuePerHour = scanner.nextDouble();

            System.out.print("Duration: ");
            hours = scanner.nextInt();

            HourContract contract = new HourContract(date, valuePerHour, hours);
            worker.addContract(contract);
        }

        System.out.print("Enter month and year to calculate income (MM/YYYY): ");
        stringDate = scanner.next();
        formatter = new SimpleDateFormat("MM/yyyy");
        Date incomeDate = null;
        try {
            incomeDate = formatter.parse(stringDate);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(incomeDate);
        int year = cal.get(Calendar.YEAR);
        int month = 1 + cal.get(Calendar.MONTH);

        System.out.println("Name: " + worker.getName());
        System.out.println("Department: " + worker.getDepartment().getName());
        System.out.printf("Income for %s: %.2f", stringDate, worker.income(year, month));
    }
}
