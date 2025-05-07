package workout;

import java.util.ArrayList;
import java.util.List;

public class Workout {
    private final String workoutName;
    private final List<Exercise> exercises = new ArrayList<>();

    public Workout(String workoutName) {
        this.workoutName = workoutName;
    }

    public void addExercise(Exercise e) {
        exercises.add(e);
    }

    public String getWorkoutName() {
        return workoutName;
    }

    public List<Exercise> getExercises() {
        return exercises;
    }

}