package LibraryProject;

import java.time.LocalDate;

class DateHandler{
    public String getReturnDate(String issuedate){
        LocalDate date = LocalDate.parse(issuedate);
        int issueday = date.getDayOfMonth();
        int month = date.getMonthValue();
        int year = date.getYear();
        int temp = issueday;
        int i = 0;
        while(i < 10){
            temp++;
            if(temp > 31){
                temp = 1;
                month++;
                if(month > 12){
                    month = 1;
                    year++;
                }
            }
            i++;
        }
        
        String duedate;
        int returnday = temp;
        if(month < 10 && returnday < 10)
            duedate = year + "-0" + month + "-0" + returnday;
        else if(month < 10)
            duedate = year + "-0" + month + "-" + returnday;
        else if(returnday < 10)
            duedate = year + "-" + month + "-0" + returnday;
        else        
            duedate = year + "-" + month + "-" + returnday;
        
        return duedate;
    }
}




class FineCalculator{
    public static int checkForFine(int dueday, int returnday) {
            if((returnday - dueday) > 0){
                int daysdiff = returnday - dueday ;
                int fine = daysdiff * 100;
                return fine;
            } 
            else
                return 0;
    }
}