package schwerti.woerterkettebot;

import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import schwerti.commands.class_command_clear;
import schwerti.commands.class_command_help;
import schwerti.commands.class_command_info;
import schwerti.commands.class_command_rules;
import schwerti.commands.class_event_main;

public class event_handler extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        try
        {
            if(!((event.getChannel().getName().equals("bot") || event.getChannel().getName().equals("woerterkette-bot")) && !(event.getAuthor().isBot())))
                return;
                
            if(event.getMessage().getContentRaw().length() < 3)
            {
                event.getMessage().delete().queue();
                return;
            }
            List <Message> history = event.getChannel().getHistory().retrievePast(100).complete();

            if(event_handler.scan_command(event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete()))
            {
                Message message = event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete();
                String[] args = message.getContentRaw().split("\\s+");

                check_command(event, args);
            }
            else
            {
                class_event_main.main_event(event, history);
            }
        }
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);   
        }
    }

    public static boolean scan_command(Message message)
    {
        String[] args = message.getContentRaw().split("\\s+");
        if(args[0].substring(0,2).equals(class_main_woerterkettebot.prefix))
        {
            System.out.println("Command event!");
            return true;
        }
        return false;
    }

    public static boolean check_command(GuildMessageReceivedEvent event, String[] args)
    {
        if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "clear")))
        {
            class_command_clear.clear(event, args[1]);
        }
        else if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "info")))
        {
            if(!event.getChannel().getName().equals("bot"))
            {
                event.getMessage().delete().queue();
                return false;
            }
            else
            {
                class_command_info.output_info(event);
                return true;
            }
            
        }
        else if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "rules")))
        {
            if(!event.getChannel().getName().equals("bot"))
            {
                event.getMessage().delete().queue();
                return false;
            }
            else
            {
                class_command_rules.output_rules(event);
                return true;
            }
            
        }
        else if((args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "help")))
        {
            if(!event.getChannel().getName().equals("bot"))
            {
                event.getMessage().delete().queue();
                return false;
            }
            else
            {
                class_command_help.output_help(event);
                return true;
            }
            
        }
        return false;
    }
}
    
