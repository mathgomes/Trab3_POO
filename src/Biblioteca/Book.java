package Biblioteca;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Book {

    public String name;
    public String type;
    protected long borrowedDate;
    protected boolean isTaken;
    protected Date d;

    public Book(String name, String type) {
        this.name = name;
        this.type = type;
    }
    public String getName() {
        return this.name;
    }
    public String getType() {
        return this.type;
    }

    public long datetoSeconds(Date d) {
        this.borrowedDate = d.getTime()/1000;
        return borrowedDate;
    }

    public void setBorrowedDate(long date) {
        this.borrowedDate = date;
    }
    public long getBorrowedDate() {
        return this.borrowedDate;
    }
    public void setStatus(boolean status) {
        this.isTaken = status;
    }
    public boolean getStatus() {
        return this.isTaken;
    }
}
