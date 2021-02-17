package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import schwerti.woerterkettebot.class_exeptionhandling;


/*
This handler should give an overview of commands available.
todo:
Exeption handling has to be fixed soon.

----> thinking about: Dont like the hardcoded way of the channel the bot should be able to use
*/
public class class_command_help
{   
    public static void output_help(GuildMessageReceivedEvent event)
    {
        try
        {
            //Embed of help commands --> finished
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0xf531d4);
            info.setTitle("Help");
            info.setDescription("This should give you an overview over the bot and his/her/it commands!\n All commands do only work in the bot channel.");
            info.addField("!!help", "You are looking at it", false);
            info.addField("!!info", "Info what this bot does and who created it", false);
            info.addField("!!clear [ARG]", "Clears the amount of messages u chose. Keep in mind that the clear command it self is a message too.", false);
            info.addField("!!rules", "Shows you the current rules of the game", false);
            event.getChannel().sendMessage(info.build()).queue();
        }
        //exeptionhandling, superimposition of exeption_handling function
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);

            //embed of type unknow
                class_exeptionhandling.exeption_wrong_usage_embed(event);
        }  
    }
}