package me.poggers.dev.bot.commands;


import me.poggers.dev.bot.database.CRUD;
import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;


public class Prefix extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        TextChannel textChannel = event.getChannel();

        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild()
       .getId())+ "prefix")){

            textChannel.sendMessage("```fix\nO prefixo para esse servidor é: " +
                    NaegiMakoto.prefixMap.get(event.getGuild().getId()) + "```").queue();
        }



        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event
                .getGuild().getId()) + "setprefix")){

            if(!event.getMember().hasPermission(Permission.MANAGE_PERMISSIONS)){
                textChannel.sendMessage("Você não tem permissão para fazer isso").queue();
                return;

            }

            NaegiMakoto.prefixMap.replace(event.getGuild().getId(),args[1]);
            try {
                CRUD.update("prefix",event.getGuild().getId(),(args[1]));
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
            textChannel.sendMessage("O prefixo foi alterado para: " +
                    NaegiMakoto.prefixMap.get(event.getGuild().getId())).queue();
        }
    }
}


