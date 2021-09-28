package me.poggers.dev.bot.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;
import net.dv8tion.jda.api.entities.User;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

public class EmbedMessage {

    public EmbedBuilder instance;


    public EmbedBuilder getInstance() {
        return instance == null ? new EmbedBuilder() : instance.clear();
    }

    public MessageEmbed noPerm(Member member) {
        return getInstance()
                .setColor(new Color(0xFF0000))
                .setDescription("`" + member.getUser().getName() + "`voce não tem " +
                        "permissão para mudar o cargo padrão.")
                .build();
    }

    public MessageEmbed listRolesIndexes(StringBuilder stringBuilder) {
        return getInstance()
                .setColor(new Color(0x00FFFF))
                .setDescription(stringBuilder)
                .setTitle("Escolha o indice do cargo para ser o cargo padrão.")
                .build();

    }

    public MessageEmbed defaultRoleSucess(String s) {
        return getInstance()
                .setColor(new Color(0x00FF00))
                .setDescription(s)
                .build();
    }

    public MessageEmbed noLowerRoles() {
        return getInstance()
                .setColor(new Color(0xFFF00))
                .setDescription("Não existe cargos abaixos do Naegi Makoto, " +
                        "verifique o gerenciador de cargos. ")
                .build();
    }

    public MessageEmbed InvalidIndex() {

        return getInstance().setColor(new Color(0xFF0000))
                .setDescription("Indice invalido, digite o indice de um cargo da lista.")
                .build();
    }

    public void sendPrivateCannotSendMessage(User admin, Member bot){

       admin.openPrivateChannel().queue(privateChannel -> privateChannel.sendMessage(getInstance().setColor(new Color(0xFF0000))
                .setDescription("Não consigo enviar mensagens no servidor " +
                bot.getGuild().getName() + " preciso da permissão: " +
                Permission.MESSAGE_WRITE.getName())
               .build()).queue(message -> message.delete().queueAfter(1, TimeUnit.MINUTES)));
    }
}

