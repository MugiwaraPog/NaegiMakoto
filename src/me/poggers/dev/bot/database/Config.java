package me.poggers.dev.bot.database;

import me.poggers.dev.bot.main.NaegiMakoto;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.SQLException;

public class Config {


    public static final File parentJarLocation = new File
            (NaegiMakoto.class.getProtectionDomain().getCodeSource().getLocation()
                    .getPath()).getParentFile();

    public static File databaseFile = new File(parentJarLocation +
            "/naegimakoto.db");

//criação dos arquivos e a tabela guild
    public static void createFilesAndTable() throws IOException, SQLException {
        if(Files.notExists(databaseFile.toPath())){
            Files.createFile(databaseFile.toPath());
            CRUD.createTable();
        }

    }
}
