package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import schwerti.woerterkettebot.class_exeptionhandling;

/*
function to get information about the bot.
Right now only who coded it etc.
But i am thinking about writing out what the exact task of the bot is
----
todo (like other events):
Works, but we have some work here. Error Handling is not finished yet.
*/

public class class_command_info
{
    static public void output_info(GuildMessageReceivedEvent event)
    {
        try
        {
            //do some fancy stuff here -------------------work 
            EmbedBuilder info = new EmbedBuilder();
            info.setColor(0xf531d4);
            info.setTitle("Info");
            info.setDescription("This bot is a hardcore Wörterkette Nerd and will manage this server!");
            info.addField("Creator", "Schwerti", false);
            info.addField("On behalf of", "Zero, Yukio", false);
            event.getChannel().sendMessage(info.build()).queue();
        }
        //exeptionhandling, superimposition of exeption_handling function
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);
        }      
    }
  
}
