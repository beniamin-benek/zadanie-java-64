import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import static java.time.DayOfWeek.SUNDAY;
import static java.time.temporal.TemporalAdjusters.next;

class Menu {

    void openOptions(List <Homework> homeworkList) {

        int option = 0;

        while (option != 5) {
            System.out.println("-----------------");
            System.out.println("Co chcesz zrobić?");
            System.out.println("-----------------");
            System.out.println("1. Wyświetlić wszystkie zadania.\n2. Wyświetlić przeszłe zadania.\n3. Wyświetlić przyszłe zadania.\n4. Dodać zadanie.\n5. Zakończyć program.");
            option = readMenuOptionFromUser();

            switch (option) {
                case 1:
                    if (homeworkList.size() == 0)
                        System.out.println("Brak dodanych zadań.");
                    for (Homework homework : homeworkList)
                        System.out.println(homework);
                    break;
                case 2:
                    if (homeworkList.size() == 0)
                        System.out.println("Brak dodanych zadań.");
                    showPastHomeworks(homeworkList);
                    break;
                case 3:
                    if (homeworkList.size() == 0)
                        System.out.println("Brak dodanych zadań.");
                    else
                        openFutureHomeworksOptions(homeworkList);
                    break;
                case 4:
                    try {
                        addHomework(homeworkList);
                    } catch (NullPointerException e) {
                        System.out.println("Zadanie nie zostało dodane.");
                    }
                    break;
                case 5:
                    break;
                default:
                    System.out.println("Wybrałeś nieistniejącą opcję. Spróbuj ponownie:");
            }

        }
    }

    private void openFutureHomeworksOptions(List<Homework> homeworkList) {

        int option;
        boolean goToEnd = false;

        while (!goToEnd) {
            System.out.println("  1. wszystkie");
            System.out.println("  2. te które należy wykonać w ciągu najbliższych 24 godzin");
            System.out.println("  3. te które należy wykonać w bieżącym tygodniu");
            System.out.println("  4. te które należy wykonać w ciągu 30 dni");
            option = readMenuOptionFromUser();
            switch (option) {
                case 1:
                    showAllFutureHomeworks(homeworkList);
                    goToEnd = true;
                    break;
                case 2:
                    for (Homework homework : homeworkList) {
                        if (homework.getDataOddania().isBefore(LocalDateTime.now().plusHours(24)))
                            System.out.println(homework.toString());
                    }
                    goToEnd = true;
                    break;
                case 3:
                    LocalDate nextSunday = LocalDate.now().with(next(SUNDAY));
                    for (Homework homework : homeworkList) {
                        if (homework.getDataOddania().toLocalDate().isBefore(nextSunday))
                            System.out.println(homework.toString());
                    }
                    goToEnd = true;
                    break;
                case 4:
                    for (Homework homework : homeworkList) {
                        if (homework.getDataOddania().isBefore(LocalDateTime.now().plusDays(30)))
                            System.out.println(homework.toString());
                    }
                    goToEnd = true;
                    break;
                default:
                     System.out.println("Wybrałeś nieistniejącą opcję. Spróbuj ponownie:");
            }
        }
    }

    private void addHomework(List<Homework> homeworkList) {

        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Podaj numer zadania: "); //numer zadania nie musi być unikalny, unikalna jest pozycja na liście zadań
            int id = scanner.nextInt(); scanner.nextLine();
            System.out.print("Podaj teść zadania: ");
            String task = scanner.nextLine();
            System.out.println("Podaj datę oddania zadania: ");
            LocalDateTime date = readLocalDateFromUser();
            homeworkList.add(new Homework(id,date,task));
            System.out.println("Zadanie zostało dodane.");
        } catch (InputMismatchException e) {
            System.out.println("Nieprawidłowy numer zadania.");
        }
    }


    private int readMenuOptionFromUser() {

        int option = 0;
        Scanner scanner = new Scanner(System.in);
        try {
            option = scanner.nextInt();
            scanner.nextLine();
        } catch (InputMismatchException e) {
            scanner.nextLine();
        }
        return option;
    }

    private LocalDateTime readLocalDateFromUser() {

        Scanner scanner = new Scanner(System.in);
        String pattern1 = "dd.MM.yyyy HH:mm";
        String pattern2 = "yyyy-MM-dd HH:mm";

        DateTimeFormatter formatter1 = DateTimeFormatter.ofPattern(pattern1);
        DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern(pattern2);
        LocalDateTime dataOddania;

        System.out.println("Dozwolone formaty: 2015-12-05 14:00 lub 05.12.2015 14:00.");
        String userInput = scanner.nextLine();

        try {
            dataOddania = LocalDateTime.parse(userInput, formatter1);
            return dataOddania;
        } catch (DateTimeParseException e1) {
            try {
                dataOddania = LocalDateTime.parse(userInput, formatter2);
                return dataOddania;
            } catch (DateTimeParseException e2) {
                System.out.println("Podałeś datę w nieprawidłowym formacie.");
            }
        }

        return null;
    }

    private void showPastHomeworks(List<Homework> homeworkList) {
        for (Homework homework : homeworkList) {
            if (homework.getDataOddania().isBefore(LocalDateTime.now()))
                System.out.println(homework.toString());
        }
    }

    private void showAllFutureHomeworks(List<Homework> homeworkList) {
        for (Homework homework : homeworkList) {
            if (homework.getDataOddania().isAfter(LocalDateTime.now()))
                System.out.println(homework.toString());
        }
    }

}