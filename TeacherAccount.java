public class TeacherAccount extends AbstractAccount {
    private final int MAX_BOOKS = 10;

    @Override
    public void borrowBook(Book book) throws BookUnavailableException {
        if (book.isBorrowed()) {
            throw new BookUnavailableException("Error: This book is currently unavailable.");
        }
        if (borrowedBooks.size() >= MAX_BOOKS) {
            throw new BookUnavailableException("Limit Reached: Teachers can only borrow " + MAX_BOOKS + " books.");
        }
        book.setBorrowed(true);
        borrowedBooks.add(book);
        System.out.println("Success! Teacher borrowed: " + book.getName());
    }

    @Override
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setBorrowed(false);
            System.out.println("Book '" + book.getName() + "' returned successfully.");
        } else {
            System.out.println("Error: You do not have this book checked out.");
        }
    }
}
