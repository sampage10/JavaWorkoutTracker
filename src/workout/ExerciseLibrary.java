package workout;

import java.util.*;

public class ExerciseLibrary {
    public static List<Exercise> getPredefinedExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise("Squat", 3, 8, 60));
        list.add(new Exercise("Deadlift", 3, 5, 90));
        list.add(new Exercise("Bench Press", 3, 8, 60));
        list.add(new Exercise("Barbell Row", 3, 12, 60));
        list.add(new Exercise("Overhead Press", 3, 10, 80));
        return list;
    }
}