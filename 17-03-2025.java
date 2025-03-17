//Easy
import java.sql.*;

public class EmployeeData {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Employee");

            while (rs.next()) {
                int empID = rs.getInt("EmpID");
                String name = rs.getString("Name");
                double salary = rs.getDouble("Salary");
                System.out.println("EmpID: " + empID + ", Name: " + name + ", Salary: " + salary);
            }

            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Medium
import java.sql.*;
import java.util.Scanner;

public class ProductCRUD {
    static Connection conn;
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");

            while (true) {
                System.out.println("1. Create  2. Read  3. Update  4. Delete  5. Exit");
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        createProduct();
                        break;
                    case 2:
                        readProduct();
                        break;
                    case 3:
                        updateProduct();
                        break;
                    case 4:
                        deleteProduct();
                        break;
                    case 5:
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Invalid choice");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void createProduct() {
        try {
            System.out.println("Enter Product Name: ");
            String name = sc.next();
            System.out.println("Enter Price: ");
            double price = sc.nextDouble();
            System.out.println("Enter Quantity: ");
            int quantity = sc.nextInt();

            String query = "INSERT INTO Product (ProductName, Price, Quantity) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, name);
            pst.setDouble(2, price);
            pst.setInt(3, quantity);
            pst.executeUpdate();
            System.out.println("Product Added Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void readProduct() {
        try {
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM Product");

            while (rs.next()) {
                int id = rs.getInt("ProductID");
                String name = rs.getString("ProductName");
                double price = rs.getDouble("Price");
                int quantity = rs.getInt("Quantity");
                System.out.println("ProductID: " + id + ", Name: " + name + ", Price: " + price + ", Quantity: " + quantity);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void updateProduct() {
        try {
            System.out.println("Enter Product ID to Update: ");
            int id = sc.nextInt();
            System.out.println("Enter New Price: ");
            double price = sc.nextDouble();
            System.out.println("Enter New Quantity: ");
            int quantity = sc.nextInt();

            String query = "UPDATE Product SET Price = ?, Quantity = ? WHERE ProductID = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setDouble(1, price);
            pst.setInt(2, quantity);
            pst.setInt(3, id);
            pst.executeUpdate();
            System.out.println("Product Updated Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void deleteProduct() {
        try {
            System.out.println("Enter Product ID to Delete: ");
            int id = sc.nextInt();

            String query = "DELETE FROM Product WHERE ProductID = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, id);
            pst.executeUpdate();
            System.out.println("Product Deleted Successfully");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

//Hard
//Model
public class Student {
    private int studentID;
    private String name;
    private String department;
    private double marks;

    // Getters and Setters
    public int getStudentID() {
        return studentID;
    }

    public void setStudentID(int studentID) {
        this.studentID = studentID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public double getMarks() {
        return marks;
    }

    public void setMarks(double marks) {
        this.marks = marks;
    }
}
//Conttroller
import java.sql.*;

public class StudentController {
    static Connection conn;

    public StudentController() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/your_database", "username", "password");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addStudent(Student student) {
        try {
            String query = "INSERT INTO Student (Name, Department, Marks) VALUES (?, ?, ?)";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, student.getName());
            pst.setString(2, student.getDepartment());
            pst.setDouble(3, student.getMarks());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateStudent(Student student) {
        try {
            String query = "UPDATE Student SET Name = ?, Department = ?, Marks = ? WHERE StudentID = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setString(1, student.getName());
            pst.setString(2, student.getDepartment());
            pst.setDouble(3, student.getMarks());
            pst.setInt(4, student.getStudentID());
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteStudent(int studentID) {
        try {
            String query = "DELETE FROM Student WHERE StudentID = ?";
            PreparedStatement pst = conn.prepareStatement(query);
            pst.setInt(1, studentID);
            pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public ResultSet viewStudents() {
        try {
            Statement stmt = conn.createStatement();
            return stmt.executeQuery("SELECT * FROM Student");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

//View
import java.sql.*;

public class StudentView {
    public static void displayMenu() {
        System.out.println("1. Add Student");
        System.out.println("2. Update Student");
        System.out.println("3. Delete Student");
        System.out.println("4. View Students");
        System.out.println("5. Exit");
    }

    public static void viewStudents(ResultSet rs) {
        try {
            while (rs.next()) {
                System.out.println("StudentID: " + rs.getInt("StudentID") + ", Name: " + rs.getString("Name")
                        + ", Department: " + rs.getString("Department") + ", Marks: " + rs.getDouble("Marks"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

//Main
import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        StudentController controller = new StudentController();

        while (true) {
            StudentView.displayMenu();
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    Student student = new Student();
                    System.out.println("Enter Name: ");
                    student.setName(sc.next());
                    System.out.println("Enter Department: ");
                    student.setDepartment(sc.next());
                    System.out.println("Enter Marks: ");
                    student.setMarks(sc.nextDouble());
                    controller.addStudent(student);
                    break;

                case 2:
                    System.out.println("Enter StudentID to update: ");
                    int updateID = sc.nextInt();
                    System.out.println("Enter New Name: ");
                    String updateName = sc.next();
                    System.out.println("Enter New Department: ");
                    String updateDept = sc.next();
                    System.out.println("Enter New Marks: ");
                    double updateMarks = sc.nextDouble();
                    Student updateStudent = new Student();
                    updateStudent.setStudentID(updateID);
                    updateStudent.setName(updateName);
                    updateStudent.setDepartment(updateDept);
                    updateStudent.setMarks(updateMarks);
                    controller.updateStudent(updateStudent);
                    break;

                case 3:
                    System.out.println("Enter StudentID to delete: ");
                    int deleteID = sc.nextInt();
                    controller.deleteStudent(deleteID);
                    break;

                case 4:
                    ResultSet rs = controller.viewStudents();
                    StudentView.viewStudents(rs);
                    break;

                case 5:
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}
