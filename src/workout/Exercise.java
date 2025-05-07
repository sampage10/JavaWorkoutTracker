package workout;

public class Exercise {
    private final String name;
    private final int sets;
    private final int reps;
    private final int restTime;

    public Exercise(String name, int sets, int reps, int restTime) {
        this.name = name;
        this.sets = sets;
        this.reps = reps;
        this.restTime = restTime;
    }

    public String getName() {
        return name;
    }

    public int getSets() {
        return sets;
    }

    public int getReps() {
        return reps;
    }

    public int getRestTime() {
        return restTime;
    }

    public String toString() {
        return name + " - " + sets + " sets x " + reps + " reps (Rest: " + restTime + "s)";
    }
}
