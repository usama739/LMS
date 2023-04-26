package LibraryProject;

import java.sql.*;
import java.time.LocalDate;
import javax.swing.JOptionPane;

public class AdminDAL implements AdminInterface {
    Connection con = connect();
    @Override
    public int addBookToDatabase(BookItem book){
            if(con != null){
                try{
                    String query = "insert into Books (title, author, price, Status) values (?, ?, ?, ?)";
                    PreparedStatement statement = con.prepareStatement(query);
                    statement.setString(1, book.getTitle());
                    statement.setString(2, book.getAuthor());
                    statement.setString(3, book.getPrice());
                    statement.setString(4, "Available");
                    int rows = statement.executeUpdate();
                    return rows;
                }catch(SQLException e) {
                   System.out.println(e.getErrorCode());
                }
            }
            else
                return -1;
            
            return -1;
    }
    
    
    public ResultSet viewBooks(){
            if(con != null){
                try{
                    String query = "select * from Books";
                    Statement statement = con.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    return result;
                }catch(SQLException e){
                     JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            return null;
    }
    
    
    
    public int insertRecord(BookIssue book){
        String issuedate = book.getIssueDate();
        DateHandler obj = new DateHandler();
        String duedate = obj.getReturnDate(issuedate);
        if(con != null){
            try{
                String sql = "insert into IssuedBooks (BookID, RollNumber, IssuedDate, DueDate) values (?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, book.getBookID());
                statement.setString(2, book.getRollNumber());
                statement.setString(3, book.getIssueDate());
                statement.setString(4, duedate);
                int rows = statement.executeUpdate();
                return rows;
            }catch(SQLException e) {
                System.out.println(e.getErrorCode());
            }
        }
        else
            return -1;
        return -1;
    }
    
   
    
    public int insertUser(User user){
        if(con != null){
            try{
                String sql = "insert into [User] values (?, ?, ?, ?, ?)";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, user.getRollNo());
                statement.setString(2, user.getFirstName());
                statement.setString(3, user.getLastName());
                statement.setString(4, user.getUsername());
                statement.setString(5, user.getPassword());
                int rows = statement.executeUpdate();
                return rows;
            }catch(SQLException e){
                   System.out.println(e.getErrorCode());
            }
        }
        else
            return -1;
        return -1;
    }
   
    
    public ResultSet viewIssuedBooks(){
            if(con != null){
                try{
                    String query = "select * from IssuedBooks";
                    Statement statement = con.createStatement();
                    ResultSet result = statement.executeQuery(query);
                    return result;
                }catch(SQLException e){
                     JOptionPane.showMessageDialog(null, e.getMessage());
                }
            }
            return null;
    }
    
    
    public int deleteIssuedBook(BookReturn book){
        int id = Integer.parseInt(book.getBookID());
        int dueday  = getDueDay(id ,book.getRollNumber());
        if(dueday == 0)
            return -1;
        LocalDate currentDate = LocalDate.parse(book.getReturnDate());
        int returnday = currentDate.getDayOfMonth();
        int fine = FineCalculator.checkForFine(dueday, returnday);
        if(fine > 0)
             JOptionPane.showMessageDialog(null, "Fine of Rs:"+ fine +" received");
            
        if(con != null){
            try{
                String sql = "delete from IssuedBooks where BookID = ?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, book.getBookID());
                int rows = statement.executeUpdate();
                return rows;
            }catch(SQLException e){
               JOptionPane.showMessageDialog(null,  e.getMessage());
            }
        } else
            return -1;
        return -1;
    }
    
    
    public String getReturnDate(int id, String rollnum){
            try{
                CallableStatement statement = con.prepareCall("{call GetReturnDate(?, ?, ?)}");
                statement.setInt(1, id);
                statement.setString(2, rollnum);
                statement.registerOutParameter(3, Types.VARCHAR);
                statement.execute();
                String date = statement.getString(3);
                return date;
            }catch(SQLException e){
                System.out.println(e.getErrorCode());
            }
            
            return null;
    }
    
    
    
     public int getDueDay(int id, String rollnum){
        
        try{
            String date = getReturnDate(id, rollnum);
            if(date.compareTo("0") == 0)
                 throw new SQLException();
            LocalDate currentDate = LocalDate.parse(date);
            int day = currentDate.getDayOfMonth();
            return day;
        }catch(SQLException e){
             System.out.println(e.getErrorCode());
        }
        
        return 0;
    }
    
     
     private Connection connect(){
        String  url  = "jdbc:sqlserver://USAMA\\SQLEXPRESS:58579;database=LibraryProject";
	String user = "usama";
	String password = "usama";
	try {                
            Connection connection = DriverManager.getConnection(url, user, password);
            return connection;        
	}catch(SQLException e) {
            System.out.println("Error in connection\n");
            e.printStackTrace();	
        }
        return null;
    }
}
