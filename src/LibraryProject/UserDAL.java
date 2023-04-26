package LibraryProject;

import java.sql.*;


public class UserDAL implements UserInterface {
     Connection con = connect();    
    
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
    
    
      public int extendDate(String id, String rollnumber){
        int ID = Integer.parseInt(id);
        String date = getReturnDate(ID, rollnumber);
        DateHandler obj = new DateHandler();
        String extendeddate = obj.getReturnDate(date);
        if(con != null){
            try{
                String sql = "update IssuedBooks set DueDate = ? where BookID=? and Rollnumber=?";
                PreparedStatement statement = con.prepareStatement(sql);
                statement.setString(1, extendeddate);
                statement.setInt(2, ID);
                statement.setString(3, rollnumber);
                int rows = statement.executeUpdate();
                return rows;
            }catch(SQLException e){
                System.out.println(e.getMessage());
            }
        }
        
         return 0;
    }
    
    
    
    public ResultSet viewmybooks(String rollnum){
        ResultSet result = null;
        String query = "select title, Rollnumber, IssuedDate, DueDate from Books join IssuedBooks on Books.BookID = IssuedBooks.BookID where Rollnumber = ?";
        if(con != null){
            try{
                PreparedStatement statement = con.prepareStatement(query);
                statement.setString(1, rollnum);
                result = statement.executeQuery();
                return result;
            }catch(SQLException e){
                 System.out.println(e.getMessage());
            }
        }
        return null;
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
