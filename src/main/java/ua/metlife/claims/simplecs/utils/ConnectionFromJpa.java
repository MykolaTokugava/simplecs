package ua.metlife.claims.simplecs.utils;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;

import javax.persistence.EntityManager;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFromJpa {

    public static Connection getConnection(EntityManager entityManager) {
        Session session = entityManager.unwrap(Session.class);
        MyWork myWork = new MyWork();
        session.doWork(myWork);
        return myWork.getConnection();
    }


    private static class MyWork implements Work {

        Connection conn;

        @Override
        public void execute(Connection arg0) throws SQLException {
            this.conn = arg0;
        }

        Connection getConnection() {
            return conn;
        }

    }

}
