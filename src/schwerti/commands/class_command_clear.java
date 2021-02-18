package schwerti.commands;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

import schwerti.woerterkettebot.class_exeptionhandling;



/*
function to clear a specific amount of messages.
Works, but we have some work here. Error Handling is not finished yet and i am not sure
were/if i put deleted messages somewhere
*/

public class class_command_clear
{
    public static void clear(GuildMessageReceivedEvent event, String Amount)
    {
        try
        {
            if(Integer.parseInt(Amount) < 1)
            {
                Amount = "1";
            }
            event.getChannel().deleteMessages(event.getChannel().getHistory().retrievePast(Integer.parseInt(Amount)+1).complete()).queue();
        }
        //exeptionhandling, superimposition of exeption_handling function
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);
        }
    }
}
