package me.poggers.dev.bot.messages;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.TextChannel;

import java.awt.*;
import java.time.Instant;
import java.time.LocalDateTime;

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

    public void sendPrivateCannotSendMessage(Member bot){

       bot.getGuild().getMemberById("796901428392099860").getUser().openPrivateChannel().queue(privateChannel ->
                privateChannel.sendMessage("Não consigo enviar mensagens no servidor " +
                        bot.getGuild().getName() + " preciso da permissão: " +
                        Permission.MESSAGE_WRITE.getName()).queue());
    }
}

    /*public MessageEmbed Embedteste(Member author){
        getInstance()
        .setTitle("Poggers","https://www.google.com.br")
        .setAuthor(author.getUser().getName(),"https://www.google.com.br",
        author.getUser().getAvatarUrl())
        .setColor(new Color(0xFFFFFF))
        .setDescription("[Descrição.](https://google.com.br)\n```yaml\nDos mesmos criadores" +
                "de matador de joaninha, vem ai...\n```")
        .setImage(author.getUser().getAvatarUrl())
        .setFooter(author.getId(),author.getUser().getAvatarUrl())
        .setTimestamp(Instant.now())
        .appendDescription("Adicional")
        .addField("nome","valor",true);




        return getInstance().build();

    }

    public MessageEmbed Embedteste2(Member author){
        getInstance()
                .clear()
                .setColor(new Color(0xFF0000))
                .setDescription("Segunda mensagem Emblematica");

        return getInstance().build();

    }*/

