import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.InputMismatchException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Library{

    String bookId;
    String title;
    String author;
    String language;
    int pageCount;
    String genre;
    boolean available;

    public Library(String bookId,String title,String author,String language,int pageCount,String genre,boolean available){
        this.bookId=bookId;
        this.title=title;
        this.author=author;
        this.language=language;
        this.pageCount=pageCount;
        this.genre=genre;
        this.available=available;

    }
    public void showInfo(){

        System.out.println("Book ID: "+bookId);
        System.out.println("Book Title: "+title);
        System.out.println("Author: "+author);
        System.out.println("Book's Language: "+language);
        System.out.println("Page Count: "+pageCount);
        System.out.println("Genre: "+genre);
        System.out.println("Available: "+available);

    }
}
class User{

    String userName;
    String userId;
    int userPassword;

    public User(String userName,String userId,int userPassword){
        this.userName=userName;
        this.userId=userId;
        this.userPassword=userPassword;

    }
}
class Admin{

    String adminName;
    String adminId;
    int adminPassword;

    public Admin(String adminName,String adminId,int adminPassword){
        this.adminName=adminName;
        this.adminId=adminId;
        this.adminPassword=adminPassword;

    }
}
class homeMenu{
    public static void show(boolean isAdmin){
        while(true){
            System.out.println();
            System.out.println("===============LIBRARY SYSTEM===============");

            if(isAdmin){

                System.out.println("1.Add Book");
                System.out.println("2.Delete Book");
                System.out.println("3.Change Information");
                System.out.println("4.List Book");
                System.out.println("5.Borrow Book");
                System.out.println("6.Return Book");
                System.out.println("7.Search Book By ID");
                System.out.println("8.Search Book By Author");
                System.out.println("9.Search Book By Genre");
                System.out.println("10.Search Book By Language");
                System.out.println("11.Logout");

                System.out.print("PLEASE SELLECT AN OPERATION:");

            }else{

                System.out.println("1.List Book");
                System.out.println("2.Borrow Book");
                System.out.println("3.Return Book");
                System.out.println("4.Search Book By ID");
                System.out.println("5.Search Book By Author");
                System.out.println("6.Search Book By Genre");
                System.out.println("7.Search book By Language");
                System.out.println("8.Logout");

                System.out.print("PLEASE SELLECT AN OPERATION");
                
            }

            int choice=Main.sc.nextInt();
            Main.sc.nextLine();

            if(isAdmin){
                switch(choice){

                    case 1 -> Main.addBook();
                    case 2 -> Main.deleteBook();
                    case 3 -> Main.changeInfo();
                    case 4 -> Main.listBook();
                    case 5 -> Main.borrowBook();
                    case 6 -> Main.returnBook();
                    case 7 -> Main.searchById();
                    case 8 -> Main.searchByAuthor();
                    case 9 -> Main.searchByGenre();
                    case 10 -> Main.searchByLanguage();
                    case 11 -> {
                        System.out.println("Logging Out...");
                        return;
                    }
                    default -> System.out.println("Invalid Choice!");
                }
            }else{
                switch(choice){

                    case 1 -> Main.listBook();
                    case 2 -> Main.borrowBook();
                    case 3 -> Main.returnBook();
                    case 4 -> Main.searchById();
                    case 5 -> Main.searchByAuthor();
                    case 6 -> Main.searchByGenre();
                    case 7 -> Main.searchByLanguage();
                    case 8 -> {
                        System.out.println("Logging Out...");
                        return;
                    }

                    default -> System.out.println("Invalid Choice");

                }
            }
        }
    }
}

class Main{
    static ArrayList<Library> libraryl=new ArrayList<>();
    static ArrayList<User> users =new ArrayList<>();
    static ArrayList<Admin> admins=new ArrayList<>();

    static Scanner sc = new Scanner(System.in, StandardCharsets.UTF_8);

    public static void main(String[]args){
        loadBooks();

        admins.add(new Admin("Hatice","001",1234));
        users.add(new User("Esma","001",4321));

        loginMenu();
    }

            public static boolean adminLogin(){

            System.out.println("=====ADMIN LOGIN=====");

            System.out.print("Admin Name:");
            String name=sc.nextLine();

            System.out.print("Admin Password:");
            int password=sc.nextInt();
            sc.nextLine();

            for(Admin admin:admins){

                if(admin.adminName.equalsIgnoreCase(name) && admin.adminPassword==password){

                    System.out.println("Admin Login Succesful!");

                    return true;
                }
            }
            System.out.println("Wrong Admin Name or Password!");

            return false;

        }

        public static boolean userLogin(){

            System.out.print("=====USER LOGİN=====");

            System.out.print("User Name:");
            String name=sc.nextLine();

            System.out.print("User Password:");
            int password=sc.nextInt();
            sc.nextLine();

            for(User user:users){
                if(user.userName.equalsIgnoreCase(name) && user.userPassword==password){

                    System.out.println("User Login Succesful!");

                    return true;
                }

            }
            
            System.out.println("Wrong User Name or Password!");

            return false;

        }

    
    public static void loginMenu(){

        while(true){

            System.out.println();
            System.out.print("====================");
            System.out.print("LIBRARY MANAGEMENT");
            System.out.println("====================");
            System.out.println("1:Admin Login");
            System.out.println("2:User Login");
            System.out.println("3:Exit");
            System.out.print("Choose:");

            int choice=sc.nextInt();
            sc.nextLine();

            switch(choice){

                case 1 -> {
                    if(adminLogin()){
                        homeMenu.show(true);
                        return;
                    }
                }
                case 2 -> {
                    if(userLogin()){
                        homeMenu.show(false);
                        return;
                    }
                }
                case 3 -> {
                    saveBooks();
                    System.out.println("Exiting...");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
    public static void addBook(){

        String Id;
        while(true){
            System.out.print("Book ID (5 digits): ");
            Id=sc.nextLine();

            if(Id.matches("\\d{5}")){
            }else{
                System.out.println("Invalid Book ID! (Please Enter Exactly 5 Digits!)");
                continue;
            }

            boolean exists=false;

            for(Library book:libraryl){
                if(book.bookId.equals(Id)){
                    exists=true;
                    break;
                }
            }
            if(exists){
                System.out.println("This Book ID Already Exists.Please Enter Another ID!");
            }else{
                break;
            }

        }

        String title;
        while(true){

        System.out.print("Title: ");
        title=sc.nextLine();

        if(title.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ0-9.'  -]+")){
            break;
        }else{
            System.out.println("Invalid Title!");
        }

        }

        String author;
        while(true){

        System.out.print("Author: ");
        author=sc.nextLine();

        if(author.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ.'  -]+")){
            break;
        }else{
            System.out.println("Invalid Author!");
        }

        }

        String language;
        while(true){

        System.out.print("Language: ");
        language=sc.nextLine();

        if(language.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ]+")){
            break;
        }else{
            System.out.println("Invalid Language!");
        }

        }

        int pageCount;
            
        System.out.print("Page Count: ");
        pageCount=sc.nextInt();
        sc.nextLine();

        String genre;
        while(true){

        System.out.print("Genre: ");
        genre=sc.nextLine();

        if(genre.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ]+")){
            break;
        }else{
            System.out.println("Invalid Genre");
        }

        }

        Library book=new Library(Id,title,author,language,pageCount,genre,true);
        libraryl.add(book);
        saveBooks();

        System.out.println("Book Added Succesfully!");

    }
    public static void listBook(){
        if(libraryl.isEmpty()){
            System.out.println("Library is Empty!");
            return;
        }for(Library book:libraryl){
            book.showInfo();
            System.out.println("--------------------------------");
        }
    }
    public static void searchById(){
        System.out.print("Enter Book ID to Find Book: ");
        String ID=sc.nextLine();

        for(Library book:libraryl){

            if(book.bookId.equals(ID)){
                book.showInfo();
                return;
            }
        }
        System.out.print("Book Not Found!");
    }
    public static void deleteBook(){
        System.out.println("Enter Book ID to Delete Book: ");
        String ID=sc.nextLine();

        for(int i=0;i<libraryl.size();i++){

            if(libraryl.get(i).bookId.equals(ID)){
                libraryl.remove(i);
                saveBooks();
                System.out.println("Book Deleted!");
                return;
            }
        }
        System.out.println("Book Not Found!");
    }
    public static void changeInfo(){
        System.out.println("Enter ID to Change Info: ");
        String ID=sc.nextLine();

        for(Library book:libraryl){
            if(book.bookId.equals(ID)){

                while(true){
                System.out.print("New Title:");
                String newTitle=sc.nextLine();

                if(newTitle.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ0-9.'  -]+")){
                    book.title=newTitle;
                    break;
                }else{
                    System.out.println("Invalid Title!");
                }
                }

                while(true){

                System.out.print("New Author:");
                String newAuthor=sc.nextLine();

                if(newAuthor.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ]+")){
                    book.author=newAuthor;
                    break;
                }else{
                    System.out.println("Invalid Author!");
                }
                }

                while(true){

                System.out.print("New Language:");
                String newLanguage=sc.nextLine();

                if(newLanguage.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ]+")){
                    book.language=newLanguage;
                    break;
                }else{
                    System.out.println("Invalid Language!");
                }
                }

                while(true){
                    try{
                        System.out.print("New Page Count:");
                        int pages=sc.nextInt();
                        sc.nextLine();

                        if(pages>0){
                            book.pageCount=pages;
                            break;
                        }else{
                            System.out.println("Page Count Must Be Greater Than 0!");
                        }
                    }catch(InputMismatchException e){
                        System.out.println("Please Enter a Valid Number.");
                        sc.nextLine();
                    }
                }
                

                while(true){

                System.out.print("New Genre:");
                String newGenre=sc.nextLine();

                if(newGenre.matches("[a-zA-ZüÜşŞıIiİöÖçÇğĞ]+")){
                    book.genre=newGenre;
                    break;
                }else{
                    System.out.println("Invalid Genre!");
                }
                }

                while(true){
                    System.out.println("Availability(yes/no): ");
                    String input=sc.nextLine().toLowerCase();

                    if(input.equals("yes")){
                        book.available=true;
                        break;
                    }else if(input.equals("no")){
                        book.available=false;
                        break;
                    }else{
                        System.out.println("Please Enter Yes or No.");
                    }
                }
              

            }
        }
        saveBooks();
        System.out.println("Book Information Changed Succesfully!");
    }
    public static void borrowBook(){

        System.out.print("Enter Book ID:");
        String Id=sc.nextLine();

        for(Library book:libraryl){
            if(book.bookId.equals(Id)){
                if(book.available){
                    book.available=false;
                    saveBooks();
                    System.out.println("Book Borrowed Succesfully!");
                }else{
                    System.out.println("This Book is Already Borrowed!");
                }
                return;
            }
        }
        System.out.println("Book Not Found!");
    }
    public static void returnBook(){
        
        System.out.print("Enter Book ID:");
        String Id=sc.nextLine();

        for(Library book:libraryl){
            if(book.bookId.equals(Id)){
                if(!book.available){
                    book.available=true;
                    saveBooks();
                    System.out.println("Book Returned Succesfully!");
                }else{
                    System.out.println("This Book is Already in the Library!");
                }
                return;
            }
        }
        System.out.println("Book Not Found!");
    }
    public static void searchByAuthor(){

        System.out.print("Enter Author Name:");
        String author=sc.nextLine();

        boolean found=false;

        for(Library book:libraryl){
            if(book.author.equalsIgnoreCase(author)){
                book.showInfo();
                System.out.println("--------------------------------------");
                found=true;
            }
        }
        if(!found){
            System.out.println("Author Not Found!");
        }
    }
    public static void searchByGenre(){

        System.out.print("Enter Genre:");
        String genre=sc.nextLine();

        boolean found=false;

        for(Library book:libraryl){
            if(book.genre.equalsIgnoreCase(genre)){
                book.showInfo();
                System.out.println("----------------------------------------");
                found=true;
            }
        }
        if(!found){
            System.out.println("Genre Not Found!");
        }
    }
    public static void searchByLanguage(){
        System.out.print("Enter Language:");
        String language=sc.nextLine();

        boolean found=false;

        for(Library book:libraryl){
            if(book.language.equalsIgnoreCase(language)){
                book.showInfo();
                System.out.println("--------------------------------------");
                found=true;
            }
        }
        if(!found){
            System.out.println("No Books Found in This Language ");
        }
    }
    public static void saveBooks(){
        try(BufferedWriter writer = new BufferedWriter(new FileWriter("books.txt"))){
            for(Library book:libraryl){
                writer.write(book.bookId + "," +
                    book.title + "," +
                    book.author + "," +
                    book.language + "," +
                    book.pageCount + "," +
                    book.genre + "," +
                    book.available);

                writer.newLine();
            }

        }catch(IOException e){
            System.out.println("Error While Saving Books!");
        }
    }
    public static void loadBooks(){
        try(BufferedReader read = new BufferedReader (new FileReader("books.txt"))){

            String line;

            while((line = read.readLine())!=null){
                String[] data=line.split(",");
                Library book=new Library(
                    data[0],
                    data[1],
                    data[2],
                    data[3],
                    Integer.parseInt(data[4]),
                    data[5],
                    Boolean.parseBoolean(data[6])
                );

                libraryl.add(book);

            }
        }catch(IOException e){
            System.out.println("No Saved Books Found!");
        }
    }
}














