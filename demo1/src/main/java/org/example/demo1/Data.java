package org.example.demo1;
import java.util.ArrayList;

public class Data {
    static String name="Tonay";
    static String password="1";
   static String Email;
   static String sub ;

   static int marks;


    static int q_no;

   String[] q={"what is the extension of python?",".python",".p",".py",".pt",".py"};
  static ArrayList<String[]> arr=new ArrayList<>();
    Data(String name,String password,String email){
        this.name=name;
        this.password=password;
        this.Email=email;
        arr.add(q);

    }
}
