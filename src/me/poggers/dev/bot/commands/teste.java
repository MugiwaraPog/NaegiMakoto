/*package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.main.NaegiMakoto;
import me.poggers.dev.bot.messages.EmbedMessage;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class teste extends ListenerAdapter {

    EmbedMessage embedMessage = new EmbedMessage();

    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

       String[] args = event.getMessage().getContentRaw().split(" ");



        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild()
                .getId())+ "teste")){

            event.getChannel().sendMessage(embedMessage.Embedteste(event.getMember())).queue();
        }else if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild()
                .getId())+ "teste2")){
            event.getChannel().sendMessage(embedMessage.Embedteste2(event.getMember())).queue();

        }
    }
}*/
