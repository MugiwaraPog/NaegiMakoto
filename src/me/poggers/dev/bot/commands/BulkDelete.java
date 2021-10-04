package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

public class BulkDelete extends ListenerAdapter{

    private final Map<String, Boolean> isDeletingAndCreatingChannel = new HashMap<>();


    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {
        String[] args = event.getMessage().getContentRaw().split(" ");

        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(event.getGuild().getId())
                + "clear" )){

            event.getChannel().deleteMessages(event.getChannel().getHistory()
                            .retrievePast(30).complete()).complete();
        }

        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(
                event.getGuild().getId()) + "clearall")){

            if(event.getMember().hasPermission(Permission.MANAGE_SERVER)){
                event.getChannel().sendMessage("Voce não tem permissão para limpar este canal.")
                        .queue();
                return;
            }


            isDeletingAndCreatingChannel.put(event.getGuild().getId(), true);


        }
        if(args[0].equalsIgnoreCase(NaegiMakoto.prefixMap.get(
                event.getGuild().getId()) + "confirm")){
            if(isDeletingAndCreatingChannel.get(event.getGuild().getId())){
                event.getChannel().createCopy().queue();
                event.getChannel().delete().queue();

                isDeletingAndCreatingChannel.replace(event.getGuild().getId(), false);
            }

        }

    }
}
