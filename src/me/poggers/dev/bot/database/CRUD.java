package me.poggers.dev.bot.database;

import me.poggers.dev.bot.main.NaegiMakoto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/*irá criar o CRUD(Create, Read, Update, Delete), irá criar,ler,autualizar e
deletar quaisquer um dos valores abaixo
*/

public class CRUD {


    //selecionara todos da tabela que forem de guild_id


    //irá criar a tabela tb_guild
    public static void createTable() throws SQLException {
        String sql = " create table tb_guild\n" +
                "                    (\n" +
                "                    id integer not null primary key autoincrement unique,   \n" +
                "                    guild_id text not null unique,\n" +
                "                    prefix   text not null,\n" +
                "                    autorole text \n" +
                "                    )";
        PreparedStatement statement = ConnectionFactory.getConn().prepareStatement(sql);
        statement.execute();
        statement.close();
    }
    public static void insert(String guildId,char prefix) throws SQLException {
        String sql = "insert or ignore into tb_guild values (null , ?, ?, null)";
        PreparedStatement statement = ConnectionFactory.getConn().prepareStatement(sql);

        statement.setString(1,guildId);
        statement.setString(2, String.valueOf(prefix));
        statement.execute();
        statement.close();

    }

    public static void select(String guildId) throws SQLException {
        String sql =  "select * from tb_guild where guild_id = ?";

        PreparedStatement statement = ConnectionFactory.getConn().prepareStatement(sql);
        statement.setString(1,guildId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()){
            NaegiMakoto.prefixMap.put(guildId, resultSet.getString("prefix"));
            NaegiMakoto.autoroleMap.put(guildId, resultSet.getString("autorole"));
        }

        statement.close();
        ConnectionFactory.getConn().close();
    }
    public static void update(String cln,String guildId,String value) throws SQLException {
        String sql = String.format("update tb_guild set %s = ? where guild_id = ?",cln);
        PreparedStatement statement = ConnectionFactory.getConn().prepareStatement(sql);

        statement.setString(1,value);
        statement.setString(2,guildId);
        statement.executeUpdate();
        statement.close();

    }


}