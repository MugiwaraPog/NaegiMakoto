package me.poggers.dev.bot.events;

import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberRemoveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberLeave extends ListenerAdapter {

    @Override
    public void onGuildMemberRemove(@NotNull GuildMemberRemoveEvent e) {
        TextChannel textChannel = e.getGuild().getTextChannelById(875436661629124608L);
        String userName = e.getUser().getName();
        textChannel.sendMessage(userName + " saiu do Servidor!").queue();
    }
}
