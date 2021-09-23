package me.poggers.dev.bot.events;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.events.guild.GuildLeaveEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class GuildLeave extends ListenerAdapter {

    @Override
    public void onGuildLeave(@NotNull GuildLeaveEvent event) {

        try {
            NaegiMakoto.setActivity(event.getJDA());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
