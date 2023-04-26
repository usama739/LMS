package LibraryProject;


public class Admin {
    public boolean AddBook(BookItem book){
        AdminInterface server = new AdminDAL();
        int rows = server.addBookToDatabase(book);
        if(rows > 0)
            return true;
        else
            return false;
    }
    
    public boolean IssueBook(BookIssue book){
        AdminInterface server = new AdminDAL();
        int rows = server.insertRecord(book);
        if(rows > 0)
            return true;
        else
            return false;   
    }
    
    
    public boolean addUser(User user){
        AdminInterface obj = new AdminDAL();
        int rows = obj.insertUser(user);
        if(rows > 0)
            return true;
        else
            return false;   
    }
    
    
    public boolean ReturnBook(BookReturn book){
        AdminInterface obj = new AdminDAL();
        int rows = obj.deleteIssuedBook(book);
        if(rows > 0)
            return true;
        else
            return false;   
    }
}
