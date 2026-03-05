public interface LibraryAction {
    void borrowBook(Book book) throws BookUnavailableException;
    void returnBook(Book book);
    default void printHello(){
        System.out.println("Hello world");
    }
}