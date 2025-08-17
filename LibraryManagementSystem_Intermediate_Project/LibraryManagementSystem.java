import java.io.*;
import java.util.*;

//  Class for Book management
class Book implements Serializable {
    private int id;
    private String title;
    private String author;
    private boolean issued;

    public Book(int id, String title, String author) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.issued = false;
    }

    public int getId() { 
        return id; 
    }
    public String getTitle() { 
        return title; 
    }
    public String getAuthor() { 
        return author; 
    }
    public boolean isIssued() { 
        return issued; 
    }
    public void setIssued(boolean issued) { 
        this.issued = issued; 
    }

    @Override
    public String toString() {
        return id + " - " + title + " by " + author + (issued ? " [Issued]" : " [Available]");
    }
}

//  Class For Student
class Student implements Serializable {
    private int studentId;
    private String name;

    public Student(int studentId, String name) {
        this.studentId = studentId;
        this.name = name;
    }

    public int getStudentId() { 
        return studentId; 
    }
    public String getName() {
        return name;
        }

    @Override
    public String toString() {
        return studentId + " - " + name;
    }
}

// Class for Libarary
class Library {
    private Map<Integer, Book> books = new HashMap<>();
    private Map<Integer, Student> students = new HashMap<>();
    private final String BOOK_FILE = "books.dat";
    private final String STUDENT_FILE = "students.dat";

    public Library() {
        loadBooks();
        loadStudents();
    }

    // File Save & Load all file handeling 
    private void saveBooks() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(BOOK_FILE))) {
            oos.writeObject(books);
        } catch (IOException e) {
            System.out.println(" Error saving books!");
        }
    }

    private void saveStudents() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(STUDENT_FILE))) {
            oos.writeObject(students);
        } catch (IOException e) {
            System.out.println(" Error saving students!");
        }
    }

    @SuppressWarnings("unchecked")
    private void loadBooks() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(BOOK_FILE))) {
            books = (Map<Integer, Book>) ois.readObject();
        } catch (Exception e) {
            books = new HashMap<>();
        }
    }

    @SuppressWarnings("unchecked")
    private void loadStudents() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(STUDENT_FILE))) {
            students = (Map<Integer, Student>) ois.readObject();
        } catch (Exception e) {
            students = new HashMap<>();
        }
    }

    // Book functions
    public void addBook(Book book) {
        if (books.containsKey(book.getId())) {
            System.out.println(" Book ID already exists!");
        } else {
            books.put(book.getId(), book);
            saveBooks();
            System.out.println(" Book added successfully!");
        }
    }

    public void showAvailableBooks() {
        boolean found = false;
        for (Book book : books.values()) {
            if (!book.isIssued()) {
                System.out.println(book);
                found = true;
            }
        }
        if (!found) System.out.println(" No available books!");
    }

    public void showAllBooks() {
        if (books.isEmpty()) {
            System.out.println(" No books found!");
            return;
        }
        for (Book book : books.values()) {
            System.out.println(book);
        }
    }

    // Student functions
    public void addStudent(Student student) {
        if (students.containsKey(student.getStudentId())) {
            System.out.println(" Student ID already exists!");
        } else {
            students.put(student.getStudentId(), student);
            saveStudents();
            System.out.println(" Student added successfully!");
        }
    }

    public void showStudents() {
        if (students.isEmpty()) {
            System.out.println(" No students found!");
            return;
        }
        for (Student s : students.values()) {
            System.out.println(s);
        }
    }

    public void removeStudent(int studentId) {
        if (students.containsKey(studentId)) {
            students.remove(studentId);
            saveStudents();
            System.out.println(" Student removed successfully!");
        } else {
            System.out.println(" Student not found!");
        }
    }

    // Transaction function
    public void issueBook(int bookId, int studentId) {
        Book book = books.get(bookId);
        Student student = students.get(studentId);

        if (book == null) {
            System.out.println(" Book not found!");
            return;
        }
        if (student == null) {
            System.out.println(" Student not found!");
            return;
        }
        if (book.isIssued()) {
            System.out.println(" Book already issued!");
        } else {
            book.setIssued(true);
            saveBooks();
            System.out.println(" Book issued to " + student.getName());
        }
    }

    public void returnBook(int bookId) {
        Book book = books.get(bookId);

        if (book == null) {
            System.out.println(" Book not found!");
            return;
        }
        if (!book.isIssued()) {
            System.out.println(" Book was not issued!");
        } else {
            book.setIssued(false);
            saveBooks();
            System.out.println(" Book returned successfully!");
        }
    }
}

// Main class functions
public class LibraryManagementSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Library library = new Library();

        int choice = 0;
        do {
            try {
                System.out.println("\n=== Library Menu ===");
                System.out.println("1. Add Book");
                System.out.println("2. Show Available Books");
                System.out.println("3. Show All Books");
                System.out.println("4. Add Student");
                System.out.println("5. Show Students");
                System.out.println("6. Remove Student");
                System.out.println("7. Issue Book");
                System.out.println("8. Return Book");
                System.out.println("9. Exit");
                System.out.print("Enter choice: ");

                choice = sc.nextInt();
                sc.nextLine();
                    System.out.println("");
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter Book ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Book Title: ");
                        String title = sc.nextLine();
                        System.out.print("Enter Book Author: ");
                        String author = sc.nextLine();
                        library.addBook(new Book(id, title, author));
                    }
                    case 2 -> library.showAvailableBooks();
                    case 3 -> library.showAllBooks();
                    case 4 -> {
                        System.out.print("Enter Student ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Student Name: ");
                        String name = sc.nextLine();
                        library.addStudent(new Student(id, name));
                    }
                    case 5 -> library.showStudents();
                    case 6 -> {
                        System.out.print("Enter Student ID to remove: ");
                        int id = sc.nextInt();
                        library.removeStudent(id);
                    }
                    case 7 -> {
                        System.out.print("Enter Book ID to issue: ");
                        int bookId = sc.nextInt();
                        System.out.print("Enter Student ID: ");
                        int studentId = sc.nextInt();
                        library.issueBook(bookId, studentId);
                    }
                    case 8 -> {
                        System.out.print("Enter Book ID to return: ");
                        int bookId = sc.nextInt();
                        library.returnBook(bookId);
                    }
                    case 9 -> System.out.println("Exiting... Bye!");
                    default -> System.out.println(" Invalid choice!");
                }
            } catch (InputMismatchException e) {
                System.out.println(" Please enter a valid number!");
                sc.nextLine();
            }
        } while (choice != 9);

        sc.close();
    }
}

