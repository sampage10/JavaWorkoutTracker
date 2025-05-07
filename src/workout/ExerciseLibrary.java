package workout;

import java.util.*;

public class ExerciseLibrary {
    public static List<Exercise> getPredefinedExercises() {
        List<Exercise> list = new ArrayList<>();
        list.add(new Exercise("Squat", 3, 8, 5));
        list.add(new Exercise("Deadlift", 3, 5, 5));
        list.add(new Exercise("Bench Press", 3, 8, 5));
        list.add(new Exercise("Barbell Row", 3, 12, 5));
        list.add(new Exercise("Overhead Press", 3, 10, 5));
        return list;
    }
}