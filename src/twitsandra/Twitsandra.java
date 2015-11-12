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

/**
 *
 * @author adwisatya
 */
public class Twitsandra {
    public static void main(String[] args) {
        Twissandra_Engine twissandra_engine = new Twissandra_Engine("server1.bangsatya.com", "mykeyspace");
        twissandra_engine.show_user("aryya");
        twissandra_engine.register_user("aryya2", "aryyaaa");
        twissandra_engine.show_user("aryya2");
        twissandra_engine.show_tweet("aryya2");
        twissandra_engine.tweet("aryya2","aryya2 tweet");
        twissandra_engine.teminate_connection();
    }
}
