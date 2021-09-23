package me.poggers.dev.bot.events;

import me.poggers.dev.bot.main.NaegiMakoto;
import net.dv8tion.jda.api.entities.Guild;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.events.guild.member.GuildMemberJoinEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

public class MemberJoin extends ListenerAdapter {

    @Override
    public void onGuildMemberJoin(@NotNull GuildMemberJoinEvent event) {
        //quando for botar um GTCBI lembre botar o L no final, só o id n funciona!
        /* member são os membros do servidor, User é o usuario, você!*/
        TextChannel textChannel = event.getGuild().getTextChannelById(800348461684883487L);
        String userName = event.getUser().getName();

        Member joined = event.getMember();
        Guild guild = event.getGuild();
        guild.addRoleToMember(joined.getId(), guild.getRoleById(NaegiMakoto.autoroleMap.get
                (guild.getId()))).queue();
        textChannel.sendMessage(userName + " Entrou no servidor!").queue();

    }
}
