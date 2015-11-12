/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package twitsandra;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.ResultSet;
import com.datastax.driver.core.Row;
import com.datastax.driver.core.Session;

import java.util.Scanner;

/**
 *
 * @author adwisatya
 */
public class Twitsandra {
    private static String UserName = null;

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Twissandra_Engine twissandra_engine = new Twissandra_Engine("server1.bangsatya.com", "mykeyspace");
//        System.out.println("Selamat datang di Tweet Sandra");
//        System.out.println("1. Login");
//        System.out.println("0. keluar");
//        System.out.print("Pilihan: ");
//        int ins = input.nextInt();
//        input.nextLine();
//        while (ins != 0){
//            System.out.println("Login/Register");
//            System.out.print("Username: ");
//            String username = input.nextLine();
//            System.out.print("Password: ");
//            String password = input.nextLine();
//            if (ins == 1){
//                if (twissandra_engine.check_user(username,password) == 0){
//                    UserName = username;
//                    ins = 0;
//                }else if (twissandra_engine.check_user(username,password) == 1){
//                    System.out.println("Password Salah");
//                    ins = 1;
//                }else {
//                    System.out.println("User Belum Ada, dan akan di registrasi");
//                    twissandra_engine.register_user(username, password);
//                    ins = 1;
//                }
//            }else{
//                System.out.println("pilih 1 atau 0!");
//                System.out.print("Pilihan: ");
//                ins = input.nextInt();
//            }
//        }
//        if (UserName != null){
//            System.out.println("jalan");
//        }
        //twissandra_engine.show_all_user();
        //twissandra_engine.show_user("aryya");
        //twissandra_engine.register_user("aryya2", "aryyaaa");
        //twissandra_engine.show_user("aryya2");
        twissandra_engine.tweet("aryya", "testing tweet aryya 1");
        twissandra_engine.tweet("aryya", "testing tweet aryya 2");
        twissandra_engine.show_tweet("aryya");
        twissandra_engine.teminate_connection();
    }
}
