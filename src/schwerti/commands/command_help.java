package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import schwerti.woerterkettebot.class_exeptionhandling;
import schwerti.woerterkettebot.class_main_woerterkettebot;

/*
This handler should give an overview of commands available.
todo:
Exeption handling has to be fixed soon.

----> thinking about: Dont like the hardcoded way of the channel the bot should be able to use
*/
public class command_help extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        try
        {
            if(class_event_handler.scan_command(event, event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete()))
            {
                Message message = event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete();
                String[] args = message.getContentRaw().split("\\s+");
                if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "help")) && (event.getChannel().getName().equals("bot")))
                {
                    output_help(event);
                }
            }
        }
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);
        }
    }
    
    public void output_help(GuildMessageReceivedEvent event)
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