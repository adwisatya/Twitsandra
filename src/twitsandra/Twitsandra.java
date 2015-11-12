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
//            if (ins == 1) {
//                System.out.println("Login/Register");
//                System.out.print("Username: ");
//                String username = input.nextLine();
//                System.out.print("Password: ");
//                String password = input.nextLine();
//                if (twissandra_engine.check_user(username, password) == 0) {
//                    UserName = username;
//                    ins = 0;
//                } else if (twissandra_engine.check_user(username, password) == 1) {
//                    System.out.println("Password Salah");
//                    ins = 1;
//                } else {
//                    System.out.println("User Belum Ada, dan akan di registrasi");
//                    twissandra_engine.register_user(username, password);
//                    ins = 1;
//                }
//            }else{
//                System.out.println("pilih 1 atau 0!");
//                System.out.print("Pilihan: ");
//                ins = input.nextInt();
//                input.nextLine();
//            }
//        }
//        if (UserName != null){
//            System.out.println("Selamat Datang di Twitsanda " + UserName);
//            System.out.println("");
//            System.out.print("Query: ");
//            String inputs = input.nextLine();
//            String[] query;
//            while (true){
//                if ((query = CommandRegexes.follow.match(inputs)) != null){
//                    if(twissandra_engine.follow(query[0],UserName))
//                        System.out.println("Anda Berhasil Mengikuti "+query[0]);
//                    else
//                        System.out.println("Anda Gagal Mengikuti "+query[0]);
//                }else if ((query = CommandRegexes.EXIT.match(inputs)) != null){
//                    break;
//                }
//                inputs = input.nextLine();
//            }
//        }

        twissandra_engine.show_userline("aryya2");
        twissandra_engine.teminate_connection();
    }
}
