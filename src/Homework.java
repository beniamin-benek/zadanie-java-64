import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

class Homework {

    private int id;
    private LocalDateTime dataOddania; //coś słabo z angielskim
    private String task;

    public Homework(int id, LocalDateTime dataOddania, String task) {
        if (id < 0 || dataOddania == null) throw new NullPointerException();
        else {
            this.dataOddania = dataOddania;
            this.task = task;
            this.id = id;
        }
    }

    @Override
    public String toString() {
        DateTimeFormatter dateTimeFormatter= DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm");
        return "Zadanie domowe " + id + ":\n- polecenie: " + task + "\n- data oddania:" + dataOddania.format(dateTimeFormatter);
    }

    public LocalDateTime getDataOddania() {
        return dataOddania;
    }

    public void setDataOddania(LocalDateTime dataOddania) {
        this.dataOddania = dataOddania;
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