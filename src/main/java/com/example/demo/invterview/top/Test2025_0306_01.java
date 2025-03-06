package com.example.demo.invterview.top;

public class Test2025_0306_01 {

    public static void main(String[] args) {
        Book book = new Book("水浒传", 60.5);
        System.out.println((book.name));
        System.out.println(book.price);
        System.out.println("=============================");


        Book book1 = new Book();
        //book1 = book;
        System.out.println((book1.name));
        System.out.println(book1.price);

    }
}

class Book {
    String name;
    double price;

    public Book() {
        this.name = "三国演绎";
        this.price = 70.5;

        System.out.println(" public Book(): ");

    }

    public Book(String name) {
        this.name = name;

        System.out.println(" Book(String name):");
    }

    public Book(String name, double price) {

        this(name);
        System.out.println(" Book(String name, double price):");
        this.price = price;


    }

}
