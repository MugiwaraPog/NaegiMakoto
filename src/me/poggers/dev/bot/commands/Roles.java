package me.poggers.dev.bot.commands;

import me.poggers.dev.bot.database.CRUD;
import me.poggers.dev.bot.main.NaegiMakoto;
import me.poggers.dev.bot.messages.EmbedMessage;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import org.jetbrains.annotations.NotNull;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class Roles extends ListenerAdapter {

    private final Map<Byte, String> rolesMap = new HashMap<>();
    private final Map<String, Map<Byte, String>> guildRolesMapMap = new HashMap<>();
    private final Map<String, Boolean> isEditingAutorole = new HashMap<>();
    private final Map<String, Member> memberEditingAutoroleMap = new HashMap<>();
    private final EmbedMessage embedMessage = new EmbedMessage();

    public void sendIfPermitted(TextChannel textChannel, MessageEmbed messageEmbed){
        if(textChannel.getGuild().getSelfMember().hasPermission(Permission.MESSAGE_WRITE)) {
            textChannel.sendMessage(messageEmbed).queue();
        }else{
            embedMessage.sendPrivateCannotSendMessage(textChannel.getGuild().getSelfMember());
        }

    }


    @Override
    public void onGuildMessageReceived(@NotNull GuildMessageReceivedEvent event) {

        byte roleIndexes = 0;
        String gId = event.getGuild().getId();


        String[] args = event.getMessage().getContentRaw().split(" ");

        if (args[0].equalsIgnoreCase(NaegiMakoto.prefixMap
                .get(gId) + "autorole")) {

            if(!event.getMember().hasPermission(Permission.MANAGE_SERVER)){
                sendIfPermitted( event.getChannel(),embedMessage.noPerm(event.getMember()));
                return;
            }



            StringBuilder stringBuilder = new StringBuilder();

            for (Role role : event.getGuild().getRoles()) {
                if (!role.isPublicRole() && role.getPosition() < event.getGuild().getBotRole().getPosition()) {

                    rolesMap.put(roleIndexes, role.getId());
                    stringBuilder
                            .append("`")
                            .append(roleIndexes)
                            .append("`")
                            .append(" - ")
                            .append(role.getName())
                            .append("\n");
                    roleIndexes++;
                }
            }
            if(stringBuilder.length() == 0) {

                sendIfPermitted(event.getChannel() ,embedMessage.noLowerRoles());

                return;
            }

            guildRolesMapMap.put(gId,rolesMap);
            isEditingAutorole.put(gId, true);
            memberEditingAutoroleMap.put(gId, event.getMember());

            sendIfPermitted(event.getChannel(), embedMessage.listRolesIndexes(stringBuilder));
        } else if(args[0].matches("^[0-9]{1,3}$")){

            
            if(isEditingAutorole.get(gId) == null ||
                    !isEditingAutorole.get(gId) ||
                    !event.getMember().equals(memberEditingAutoroleMap
                    .get(event.getGuild().getId())) ||
                    event.getJDA().getSelfUser().getId().equals(event.getMember().getId()) ||
                    guildRolesMapMap.get(event.getGuild().getId()) == null
            ) return;

                if(Byte.parseByte(args[0]) >= guildRolesMapMap.get(gId).size()) {

                    sendIfPermitted(event.getChannel(), embedMessage.InvalidIndex());

                    return;
                }
       sendIfPermitted(event.getChannel(), embedMessage.defaultRoleSucess
                ("definido cargo padrão para novos membros como: " +
                        event.getGuild().getRoleById(guildRolesMapMap.get(gId)
                                        .get(Byte.parseByte(args[0])))
                                .getName()));

            try {
                CRUD.update("autorole", gId,guildRolesMapMap.get(gId).get(Byte.parseByte(args[0])));

                NaegiMakoto.autoroleMap.replace(gId, guildRolesMapMap.get(gId).get(Byte.parseByte(args[0])));
            } catch (SQLException e) {
                e.printStackTrace();
            }


            isEditingAutorole.replace(event.getGuild().getId(), false);

            }


        }
    }

