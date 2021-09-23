package me.poggers.dev.bot.events;

import me.poggers.dev.bot.database.CRUD;
import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.guild.GuildJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;

public class GuildJoin extends ListenerAdapter {

    @Override
    public void onGuildJoin(@NotNull GuildJoinEvent event) {
        try {
            CRUD.insert(event.getGuild().getId(), '$');
            CRUD.select(event.getGuild().getId());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            NaegiMakoto.setActivity(event.getJDA());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    }




