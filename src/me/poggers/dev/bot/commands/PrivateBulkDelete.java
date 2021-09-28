package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.events.message.priv.PrivateMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.stream.Collectors;


public class PrivateBulkDelete extends ListenerAdapter {

    @Override
    public void onPrivateMessageReceived(@NotNull PrivateMessageReceivedEvent event) {

        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase("!" + "clear" )) {

            event.getChannel().purgeMessages(event.getChannel().getHistoryFromBeginning
                    (100).complete().getRetrievedHistory().stream()
                    .filter(message -> message.getAuthor()
                            .equals(event.getJDA().getSelfUser()))
                    .collect(Collectors.toList()));
        }
    }

}
