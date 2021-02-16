package schwerti.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import schwerti.woerterkettebot.class_main_woerterkettebot;


public class class_event_handler
{
    public static boolean scan_command(GuildMessageReceivedEvent event, Message message)
    {
        if(!(message.getAuthor().isBot()))
        {
            String[] args = message.getContentRaw().split("\\s+");
            if(args[0].substring(0,2).equals(class_main_woerterkettebot.prefix))
            {
                System.out.println("Command event!");
                return true;
            }
        }
        return false;
    }
}
    
