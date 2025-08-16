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












































// import javax.swing.*;
// import java.awt.*;
// import java.awt.event.ActionEvent;
// import java.awt.event.ActionListener;

// public class StudentGradeCalculator {
//     public static void main(String[] args) {
//         JFrame frame = new JFrame("Student Grade Calculator");
//         frame.setSize(400, 400);
//         frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//         frame.setLayout(new GridLayout(0, 2, 10, 10));

//         // Components
//         JLabel subjectsLabel = new JLabel("Enter number of subjects:");
//         JTextField subjectsField = new JTextField();

//         JLabel marksLabel = new JLabel("Enter marks (comma separated):");
//         JTextField marksField = new JTextField();

//         JButton calculateButton = new JButton("Calculate Grade");

//         JTextArea resultArea = new JTextArea();
//         resultArea.setEditable(false);
//         resultArea.setLineWrap(true);
//         resultArea.setWrapStyleWord(true);

//         // Add components
//         frame.add(subjectsLabel);
//         frame.add(subjectsField);
//         frame.add(marksLabel);
//         frame.add(marksField);
//         frame.add(new JLabel()); // empty space
//         frame.add(calculateButton);
//         frame.add(new JLabel("Result:"));
//         frame.add(resultArea);

//         // Button action
//         calculateButton.addActionListener(new ActionListener() {
//             @Override
//             public void actionPerformed(ActionEvent e) {
//                 try {
//                     int subjects = Integer.parseInt(subjectsField.getText().trim());
//                     if (subjects <= 0) {
//                         resultArea.setText("Number of subjects must be greater than 0!");
//                         return;
//                     }

//                     String[] marksText = marksField.getText().split(",");
//                     if (marksText.length != subjects) {
//                         resultArea.setText("Please enter exactly " + subjects + " marks separated by commas.");
//                         return;
//                     }

//                     int totalMarks = 0;
//                     for (String s : marksText) {
//                         int marks = Integer.parseInt(s.trim());
//                         if (marks < 0 || marks > 100) {
//                             resultArea.setText("Each mark must be between 0 and 100!");
//                             return;
//                         }
//                         totalMarks += marks;
//                     }

//                     double percentage = (double) totalMarks / subjects;

//                     String grade;
//                     switch ((int) percentage / 10) {
//                         case 10:
//                         case 9:
//                             grade = "A+ (Excellent)";
//                             break;
//                         case 8:
//                             grade = "A (Very Good)";
//                             break;
//                         case 7:
//                             grade = "B (Good)";
//                             break;
//                         case 6:
//                             grade = "C (Average)";
//                             break;
//                         case 5:
//                             grade = "D (Pass)";
//                             break;
//                         default:
//                             grade = "F (Fail)";
//                     }

//                     resultArea.setText("Total Marks: " + totalMarks +
//                             "\nPercentage: " + String.format("%.2f", percentage) + "%" +
//                             "\nGrade: " + grade);

//                 } catch (NumberFormatException ex) {
//                     resultArea.setText("Invalid input! Please enter numbers only.");
//                 }
//             }
//         });

//         frame.setVisible(true);
//     }
// }
