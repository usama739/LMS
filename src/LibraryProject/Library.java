package LibraryProject;

import java.sql.*;
import javax.swing.JOptionPane;

enum BookStatus{
    ISSUED,
    AVAILABLE
}



class BookItem{
    private String title;
    private String author;
    private String price;
    private BookStatus status;

    public BookItem(String title, String author, String price) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.status = BookStatus.AVAILABLE;
    }
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public BookStatus getStatus() {
        return status;
    }

    public void setStatus(BookStatus status) {
        this.status = status;
    }
}



class BookIssue{
    private String BookID;
    private String RollNumber;
    private String IssueDate;
    
    public BookIssue(String BookID, String RollNumber, String IssueDate) {
        this.BookID = BookID;
        this.RollNumber = RollNumber;
        this.IssueDate = IssueDate;
    }

    public String getBookID() {
        return BookID;
    }

    public void setBookID(String BookID) {
        this.BookID = BookID;
    }

    public String getRollNumber() {
        return RollNumber;
    }

    public void setRollNumber(String RollNumber) {
        this.RollNumber = RollNumber;
    }

    public String getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(String IssueDate) {
        this.IssueDate = IssueDate;
    }
}



class BookReturn{
    private String bookID;
    private String rollNumber;
    private String returnDate;

    public BookReturn(String bookID, String rollNumber, String returnDate) {
        this.bookID = bookID;
        this.rollNumber = rollNumber;
        this.returnDate = returnDate;
    }

    public String getBookID() {
        return bookID;
    }

    public void setBookID(String bookID) {
        this.bookID = bookID;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }
}
        
        

class User{
    String RollNo;
    String firstName;
    String lastName;
    String username;
    String password;
    
    public User(String RollNo, String firstName, String lastName, String username, String password) {
        this.RollNo = RollNo;
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }

    public String getRollNo() {
        return RollNo;
    }

    public void setRollNo(String RollNo) {
        this.RollNo = RollNo;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}




class UserTasks{
    public boolean renewbook(String id, String rollnumber){
        UserInterface obj = new UserDAL();
        int rows = obj.extendDate(id, rollnumber);
        if(rows > 0)
            return true;
        else
            return false;
    }
    
    public boolean mybooks(String rollnum){
        UserInterface obj = new UserDAL();
        try{
        ResultSet result = obj.viewmybooks(rollnum);
        if(result.next())
            return true;
        else
            return false;
        }catch(SQLException e){
             JOptionPane.showMessageDialog(null, "No record exist");
        }
        
        return false;
    }
}