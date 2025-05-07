package workout;

import java.io.*;
import java.util.*;
//append workouts to text file
public class WorkoutStorage {
    private static final String FILE_NAME = "workouts.txt";

    public static void saveWorkout(Workout workout) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write("WORKOUT:" + workout.getWorkoutName() + "\n");

            for (Exercise e : workout.getExercises()) {
                writer.write(e.getName() + ";" + e.getSets() + ";" + e.getReps() + ";" + e.getRestTime() + "\n");
            }
            writer.write("END");
            writer.newLine();
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error saving workout.");
        }
    }
//load saved workouts
    public static List<Workout> loadWorkouts() {
        List<Workout> workouts = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            Workout current = null;

            while ((line = reader.readLine()) != null) {
                if (line.startsWith("WORKOUT:")) {
                    current = new Workout(line.substring(8).trim());
                } else if (line.equals("END")) {
                    if (current != null) {
                        workouts.add(current);
                        current = null;
                    }
                } else if (current != null) {
                    String[] parts = line.split(";");
                    if (parts.length == 4) {
                        String name = parts[0];
                        int sets = Integer.parseInt(parts[1]);
                        int reps = Integer.parseInt(parts[2]);
                        int restTime = Integer.parseInt(parts[3]);
                        current.addExercise(new Exercise(name, sets, reps, restTime));
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("No saved workouts found.");
        }

        return workouts;
    }
}