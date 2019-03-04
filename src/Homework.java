import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Homework {

    private int id;
    private LocalDateTime endDate; //coś słabo z angielskim
    private String task;

    public Homework(int id, LocalDateTime endDate, String task) {
        if (id < 0 || endDate == null) throw new NullPointerException();
        else {
            this.endDate = endDate;
            this.task = task;
            this.id = id;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Zadanie domowe " + id + ":\n- polecenie: " + task + "\n- termin oddania:" + endDate.format(dateTimeFormatter);
    }

    public LocalDateTime getDataOddania() {
        return endDate;
    }

    public void setDataOddania(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public String getTask() {
        return task;
    }

    public void setTask(String task) {
        this.task = task;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}