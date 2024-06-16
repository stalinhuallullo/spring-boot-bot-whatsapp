package com.stalinhuallullo.base.db;

import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mchange.v2.c3p0.ComboPooledDataSource;

public class Db {

    public static String url = "jdbc:mysql://184.171.242.25:3306/reclamosdigitale_nestjs_prueba?user=reclamosdigitale_nestjs_prueba&password=nestjs_prueba&autoreconnect=true&allowMultiQueries=true&useUnicode=yes&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";

    //public static String url = "jdbc:mysql://192.168.10.30:3306/wa_ccla_dev2_sac?user=ccladb&password=0Mccla.,2021&autoreconnect=true&allowMultiQueries=true&useUnicode=yes&autoReconnect=true&failOverReadOnly=false&maxReconnects=10";
    private static String username = "root";
    private static String password = "sandermaster";
    public static ComboPooledDataSource pool;

    public String query;
    public Connection con;
    public PreparedStatement pst;
    public ResultSet res = null;


    public Db(String query) {
        this.query = query;
    }

    public PreparedStatement getPst() throws SQLException {
        con = pool.getConnection();
        pst = con.prepareStatement(query);
        return pst;
    }

    public Connection getCon() throws SQLException {
        con = pool.getConnection();
        return con;
    }

    public ResultSet ask() throws SQLException {
        con = pool.getConnection();
        pst = con.prepareStatement(query);
        res = pst.executeQuery();
        return res;
    }

    public void closeQuery() {
        try {
            if (res != null)
                res.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            if (pst != null)
                pst.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            con.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void execute() throws SQLException {
        con = pool.getConnection();
        pst = con.prepareStatement(query);
        pst.execute();
    }

    public static void open() throws PropertyVetoException {
        /*
         * pool = new ComboPooledDataSource();
         * pool.setDriverClass("com.mysql.jdbc.Driver"); pool.setJdbcUrl(url);
         *
         *
         * pool.setMinPoolSize(10); pool.setAcquireIncrement(5);
         * pool.setMaxPoolSize(70);
         *
         * pool.setIdleConnectionTestPeriod(300); pool.setTestConnectionOnCheckin(true);
         * pool.setPreferredTestQuery("select 1");
         * pool.setMaxIdleTimeExcessConnections(240);
         */

        pool = new ComboPooledDataSource();
        pool.setDriverClass("com.mysql.jdbc.Driver");
        pool.setJdbcUrl(url);

        pool.setMinPoolSize(3);
        pool.setAcquireIncrement(2);
        pool.setMaxPoolSize(8);

        pool.setMaxIdleTime(10);

//		pool.setMaxIdleTime(15);
        pool.setMaxIdleTime(10);

        pool.setIdleConnectionTestPeriod(15);
        pool.setTestConnectionOnCheckin(true);
        pool.setPreferredTestQuery("select 1");
        pool.setMaxIdleTimeExcessConnections(10);
    }


    public static int execute2(String query) {
        int resid = 0;
        try {
            Db db = new Db(query);
            Connection connection2 = db.getCon();
            PreparedStatement st = connection2.prepareStatement(query, PreparedStatement.RETURN_GENERATED_KEYS);
            st.execute();

            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                resid = rs.getInt(1);
            }
            rs.close();
            st.close();
            db.closeQuery();

            //Logger.log(query + " res = " + resid);
            System.out.println(query + " res = " + resid);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resid;
    }
}
