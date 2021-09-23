package me.poggers.dev.bot.main;

import me.poggers.dev.bot.commands.Ping;
import me.poggers.dev.bot.commands.Prefix;
import me.poggers.dev.bot.commands.Roles;
/*import me.poggers.dev.bot.commands.teste;*/
import me.poggers.dev.bot.database.CRUD;
import me.poggers.dev.bot.database.Config;
import me.poggers.dev.bot.events.*;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.requests.GatewayIntent;

import javax.security.auth.login.LoginException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

public class NaegiMakoto {

    public static JDA jda;
    //prefixMap seria o simbolo necessario para o comando
    public static Map<String, String> prefixMap = new HashMap<>();
    public static Map<String, String> autoroleMap = new HashMap<>();


    public static void main(String[] args) throws LoginException, InterruptedException, SQLException, IOException {

        Config.createFilesAndTable();

        //aqui foi ligado o bot
        jda = JDABuilder.create(System.getenv("BOT_TOKEN"),
                EnumSet.allOf(GatewayIntent.class)).build();
        //aqui entrara os listeners de comando
        jda.addEventListener(
                new Ping(),
                new MemberJoin(),
                new Teste2(),
                new Teste(),
                new GuildJoin(),
                new MemberJoin(),
                new Prefix(),
                new Roles(),
                new GuildLeave()                /*new teste()*/);







        //aqui configura o prefixmap para qual o simbolo sera colocado
        for(Guild guild: jda.awaitReady().getGuilds()) {
            CRUD.insert(guild.getId(), '$');
        }
           for(Guild guild: jda.awaitReady().getGuilds()) {
               CRUD.select(guild.getId());
        }

setActivity(jda);

    }
    public static void setActivity(JDA jda) throws InterruptedException {
        jda.getPresence().setPresence(OnlineStatus.DO_NOT_DISTURB ,Activity.playing("em " +
                jda.awaitReady().getGuilds().size() + " servidores"));
}
}
