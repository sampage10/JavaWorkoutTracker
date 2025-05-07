package workout;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.util.*;

public class WorkoutTrackerMain {
    private static final Scanner scnr = new Scanner(System.in);
//home page
    public static void main(String[] args) {
        while (true) {
            System.out.println("\n==================================");
            System.out.println("Welcome to Sam's Workout Tracker");
            System.out.println("Please choose an option by entering the corresponding number");
            System.out.println("==================================");
            System.out.println("1. Create New Workout");
            System.out.println("2. View or Start Saved Workout");
            System.out.println("3. View Workout History");
            System.out.println("==================================\n");
            String choice = scnr.nextLine();
            switch (choice) {
                case "1" -> createWorkout();
                case "2" -> viewOrStartWorkout();
                case "3" -> viewWorkoutHistory();
                default -> System.out.println("Invalid option.");
            }
        }
    }
//create workout
    private static void createWorkout() {
        System.out.print("Enter workout name (or type 0 to go back): ");
        String name = scnr.nextLine();
        if (name.equals("0")) return;
        Workout workout = new Workout(name);

        List<Exercise> library = ExerciseLibrary.getPredefinedExercises();
        while (true) {
            System.out.println("\nSelect an exercise to add:");
            for (int i = 0; i < library.size(); i++) {
                System.out.println((i + 1) + ". " + library.get(i));
            }
            System.out.println((library.size() + 1) + ". Create Custom Exercise");
            System.out.println((library.size() + 2) + ". Done");
            //
            System.out.print("Your choice: ");

        String input = scnr.nextLine();
        int choice;
        try {
            choice = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Invalid input. Please enter a number.");
            continue;
        }

        if (choice == library.size() + 2) break;

        String exerciseName;
        if (choice == library.size() + 1) {
            System.out.print("Enter custom exercise name: ");
            exerciseName = scnr.nextLine();
        } else if (choice >= 1 && choice <= library.size()) {
            exerciseName = library.get(choice - 1).getName();
        } else {
            System.out.println("Invalid option.");
            continue;
        }

        System.out.print("Enter number of sets: ");
        int sets = Integer.parseInt(scnr.nextLine());
        System.out.print("Enter number of reps: ");
        int reps = Integer.parseInt(scnr.nextLine());
        System.out.print("Enter rest time in seconds: ");
        int rest = Integer.parseInt(scnr.nextLine());

        Exercise custom = new Exercise(exerciseName, sets, reps, rest);
        workout.addExercise(custom);
        System.out.println("Added: " + custom);
    }

    WorkoutStorage.saveWorkout(workout);
    System.out.println("Workout saved!");
}
// view or start workout
    private static void viewOrStartWorkout() {
        List<Workout> workouts = WorkoutStorage.loadWorkouts();
        if (workouts.isEmpty()) {
            System.out.println("No workouts found.");
            return;
        }

        System.out.println("Select a workout:");
        for (int i = 0; i < workouts.size(); i++) {
            System.out.println((i + 1) + ". " + workouts.get(i).getWorkoutName());
        }
        System.out.println("0. Back to Home");
        String input = scnr.nextLine();
        if (input.equals("0")) return;

        int index;
        try {
            index = Integer.parseInt(input) - 1;
            if (index < 0 || index >= workouts.size()) {
                System.out.println("Invalid selection.");
                return;
            }
        } catch (NumberFormatException e) {
            System.out.println("Please enter a valid number.");
            return;
        }
        Workout selected = workouts.get(index);

        System.out.println("1. View");
        System.out.println("2. Start");
        String choice = scnr.nextLine();
        if (choice.equals("1")) {
            System.out.println("\nWorkout: " + selected.getWorkoutName());
            System.out.println("==========================");
            for (Exercise ex : selected.getExercises()) {
                System.out.println(ex);
            }
        } else if (choice.equals("2")) {
            startWorkout(selected);
        }
    }
//log workout and write to history file
private static void startWorkout(Workout workout) {
    LocalDate today = LocalDate.now();

    try (FileWriter writer = new FileWriter("workoutHistory.txt", true)) {
        writer.write("Workout: " + workout.getWorkoutName() + "\n");
        writer.write("Date: " + today.toString() + "\n");

        for (Exercise ex : workout.getExercises()) {
            System.out.println("Starting exercise: " + ex.getName());
            writer.write("Exercise: " + ex.getName() + "\n");

            for (int i = 1; i <= ex.getSets(); i++) {
                System.out.println("Set " + i + ": Enter weight used:");
                String weight = scnr.nextLine();
                System.out.println("Enter reps completed:");
                String reps = scnr.nextLine();

                writer.write("Set " + i + " Reps: " + reps + " Weight: " + weight + "\n");

                try {
                    System.out.println("Resting for " + ex.getRestTime() + " seconds...");
                    Thread.sleep(ex.getRestTime() * 1000);
                    System.out.println("Rest over!");
                } catch (InterruptedException e) {
                    System.out.println("Rest interrupted.");
                }
            }
            writer.write("\n");
        }
    } catch (IOException e) {
        System.out.println("Error saving workout history.");
    }

    System.out.println("Workout complete!\n");
}
//view history
     private static void viewWorkoutHistory() {
        try (BufferedReader reader = new BufferedReader(new FileReader("workoutHistory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                System.out.println(line); 
            }
        } catch (IOException e) {
            System.out.println("No workout history found.");
        }
    }

}
