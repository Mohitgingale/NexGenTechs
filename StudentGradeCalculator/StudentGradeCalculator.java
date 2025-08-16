import java.util.InputMismatchException;
import java.util.Scanner;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        int subjects = 0;

        try {
            // How many Subjects you have 
            System.out.print("Enter number of subjects: ");
            subjects = scan.nextInt();

            if (subjects <= 0) {
                System.out.println("Number of subjects must be greater than 0");
                return;
            }

            int totalMarks = 0;

            // Enter  Marks for each subject
            for (int i = 1; i <= subjects; i++) {
                try {
                    System.out.print("Enter marks for Subject " + i + " (out of 100): ");
                    int marks = scan.nextInt();

                    if (marks < 0 || marks > 100) {
                        System.out.println("Marks must be between 0 and 100! Try again.");
                        i--; 
                        continue;
                    }

                    totalMarks += marks;

                } catch (InputMismatchException e) {
                    System.out.println("Invalid input! Please enter numbers only.");
                    scan.next(); 
                    i--; 
                }
            }

            // Store percentage
            double percentage = (double) totalMarks / subjects;

            // Show grade as per your percentages
            String grade;
            switch ((int) percentage / 10) {
                case 10:
                case 9:
                    grade = "A+ (Excellent)";
                    break;
                case 8:
                    grade = "A (Very Good)";
                    break;
                case 7:
                    grade = "B (Good)";
                    break;
                case 6:
                    grade = "C (Average)";
                    break;
                case 5:
                    grade = "D (Pass)";
                    break;
                default:
                    grade = "F (Fail)";
            }

            // result
            System.out.println("\n-------- Result --------");
            System.out.println("Total Marks: " + totalMarks);
            System.out.printf("Percentage: %.2f%%\n", percentage);
            System.out.println("Grade: " + grade);

        } catch (InputMismatchException e) {
            System.out.println("Invalid input! Please enter a Valid number.");
        } finally {
            scan.close();
        }
    }
}













































//         frame.setVisible(true);
//     }
// }

