package server.model.persistance;

import model.Family;
import model.FamilyList;

import java.util.LinkedList;

public class DBMainTest {
    public static void main(String[] args) {
        DBPersistence adapter = new DBAdapter("org.postgresql.Driver","jdbc:postgresql://207.154.237.196:5432/ente?currentSchema=test","ente","ente");
//        LinkedList<Family> families = adapter.getFamilies();
//        for (Family e:families) {
//            System.out.println(e.getId());
//        }
//        System.out.println(adapter.getFamilies());
    }
}
