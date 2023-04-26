
package LibraryProject;

import java.sql.ResultSet;


public interface UserInterface {
    public int extendDate(String id, String rollnumber);
    public ResultSet viewmybooks(String rollnum);
}
