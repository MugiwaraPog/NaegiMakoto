package me.poggers.dev.bot.database;

import me.poggers.dev.bot.main.NaegiMakoto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionFactory {

    //onde a conexão irá ocorrer no seu pc

    public static Connection getConn() throws SQLException{
        String url = "jdbc:sqlite:" + Config.parentJarLocation +
                "/naegimakoto.db";

        try {
            return DriverManager.getConnection(url);
        }catch (SQLException sqlException){
            sqlException.printStackTrace();
        }
        return null;
    }
}
