import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

class Main {

    public static void main(String[] args) {

        List<Homework> homeworkList = new LinkedList<>();

//        do testów
//        LocalDate localDate = LocalDate.of(2019,3,3);
//        LocalTime localTime = LocalTime.of(13,41);
//        homeworkList.add(new Homework(1,LocalDateTime.of(localDate,localTime),""));

        Menu menu = new Menu();
        menu.openOptions(homeworkList);

    }

}