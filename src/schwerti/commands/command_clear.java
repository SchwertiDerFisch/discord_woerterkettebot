package schwerti.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import schwerti.woerterkettebot.class_exeptionhandling;
import schwerti.woerterkettebot.class_main_woerterkettebot;


/*
function to clear a specific amount of messages.
Works, but we have some work here. Error Handling is not finished yet and i am not sure
were/if i put deleted messages somewhere
*/

public class command_clear extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        try
        {
            if(class_event_handler.scan_command(event, event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete()))
            {
                Message message = event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete();
                String[] args = message.getContentRaw().split("\\s+");
                if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "clear")))
                {
                    if(args.length >= 2)
                    {
                        clear(event, args[1]);
                    }
                    else
                    {
                        class_exeptionhandling.exeption_wrong_usage_embed(event);
                    }
                }
            }
        }
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);
        }
    }
    //the currently used clear function
        private static void clear(GuildMessageReceivedEvent event, String Amount)
        {
            try
            {
                if(Integer.parseInt(Amount) <= 1)
                {
                    Amount = "2";
                }
                event.getChannel().deleteMessages(event.getChannel().getHistory().retrievePast(Integer.parseInt(Amount)).complete()).queue();
            }
            //exeptionhandling, superimposition of exeption_handling function
            catch(Exception e)
            {
                //console output:
                    class_exeptionhandling.exeption_handling(e);
            }
        }
    //maybe we need that one at some point
        private static void clear(Message message)
        {
            try
            {
            message.delete().complete();
            }
            //exeptionhandling, superimposition of exeption_handling function
            catch(Exception e)
            {
                //console output:
                    class_exeptionhandling.exeption_handling(e);
            }
        }

}
