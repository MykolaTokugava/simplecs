package com.ibm.as400.access;

import java.sql.SQLException;
import java.util.Properties;
import java.util.concurrent.Executor;

public class CustomAs400JDBCConnection extends AS400JDBCConnectionImpl {
    @Override
    public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
        synchronized (this) {
            System.out.println("CustomAs400JDBCConnection: milliseconds -> " + milliseconds);
            this.setNetworkTimeout(milliseconds);
        }
    }

    @Override
    public void setClientInfo(Properties properties) {
        try {
            super.setClientInfo(properties);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    public void setClientInfo(String s, String s1) {
        try {
            super.setClientInfo(s, s1);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }
}