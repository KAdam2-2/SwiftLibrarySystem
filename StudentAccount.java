public class StudentAccount extends AbstractAccount {
    private final int MAX_BOOKS = 3;

    @Override
    public void borrowBook(Book book) throws BookUnavailableException {
        // Check 1: Is the book already out?
        if (book.isBorrowed()) {
            throw new BookUnavailableException("Error: '" + book.getName() + "' is already borrowed by someone else.");
        }
        // Check 2: Limit reached?
        if (borrowedBooks.size() >= MAX_BOOKS) {
            throw new BookUnavailableException("Limit Reached: Students can only borrow " + MAX_BOOKS + " books.");
        }

        // Logic to borrow
        book.setBorrowed(true);
        borrowedBooks.add(book);
        System.out.println("Success! Student borrowed: " + book.getName());
    }

    @Override
    public void returnBook(Book book) {
        if (borrowedBooks.remove(book)) {
            book.setBorrowed(false);
            System.out.println("Book returned successfully.");
        }
    }
}