package LibraryProject;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;


public class LoginDAL implements LoginInterface {
    Connection con = connect();
     
    public int checkAdmin(String name, String password){
        try{
            CallableStatement statement = con.prepareCall("{call SearchAdmin(?, ?, ?)}");
            statement.setString(1, name);
            statement.setString(2, password);
            statement.registerOutParameter(3, Types.INTEGER);
            statement.execute();
            int found = statement.getInt(3);
            return found;
        }catch(SQLException e){
             System.out.println(e.getErrorCode());
        }
        
        return 0;
    }
    
    
    
    public int checkUser(String name, String password){
         try{
            CallableStatement statement = con.prepareCall("{call SearchUser(?, ?, ?)}");
            statement.setString(1, name);
            statement.setString(2, password);
            statement.registerOutParameter(3, Types.INTEGER);
            statement.execute();
            int found = statement.getInt(3);
            return found;
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
