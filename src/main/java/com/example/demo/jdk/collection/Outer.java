package com.example.demo.jdk.collection;

public class Outer {

    public void someOuterMethod() {
// Line 3
        new Innert();

    }


    public class Innert{
        public static void main(String[] args) {
            Outer o1 = new Outer ();

           // new Outer.Innert();
        }

    }


}
