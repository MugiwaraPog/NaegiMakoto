package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Ping extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        TextChannel textChannel = event.getChannel();
        //comando ping
        if (args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild().getId()) + "ping")) {
            textChannel.sendMessage(NaegiMakoto.jda.getGatewayPing() + "ms").queue();
        }
    }
}
