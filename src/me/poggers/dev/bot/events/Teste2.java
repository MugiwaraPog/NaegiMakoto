package me.poggers.dev.bot.events;


import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class Teste2 extends ListenerAdapter {

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");
        if(args[0].equalsIgnoreCase("poggers")){
                event.getChannel().sendMessage("poggers").queue();

        }

    }
}
