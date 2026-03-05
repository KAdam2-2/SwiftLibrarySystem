import java.util.ArrayList;
import java.util.List;

public abstract class AbstractAccount  implements LibraryAction {
    private String memberId;
    private String name;
    private String phoneNumber;
    private String password;
    private String department;
    protected List<Book> borrowedBooks = new ArrayList<>();
     public String getMemberId() {

         return memberId;
    }

    public void setMemberId(String memberId) {

         this.memberId = memberId;
    }

    public String getName() {

         return name;
    }

    public void setName(String name) {

         this.name = name;
    }

    public String getPhoneNumber() {

         return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {

         this.phoneNumber = phoneNumber;
    }

    public String getPassword() {

         return password;
    }

    public void setPassword(String password) {

         this.password = password;
    }

    public String getDepartment() {

         return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public List<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
