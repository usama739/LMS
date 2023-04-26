package LibraryProject;

import java.sql.ResultSet;


public interface AdminInterface {
    public int addBookToDatabase(BookItem book);
    public ResultSet viewBooks();
    public int insertRecord(BookIssue book);
    public int insertUser(User user);
    public ResultSet viewIssuedBooks();
    public int deleteIssuedBook(BookReturn book);
}
