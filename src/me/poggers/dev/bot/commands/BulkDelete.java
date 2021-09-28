package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class BulkDelete extends ListenerAdapter{


    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild().getId())
                + "clear" )){

            event.getChannel().deleteMessages(event.getChannel().getHistory()
                            .retrievePast(5).complete()).complete();

        }
    }
}
