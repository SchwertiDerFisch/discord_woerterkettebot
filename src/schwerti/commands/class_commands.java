package schwerti.commands;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import schwerti.woerterkettebot.class_main_woerterkettebot;

public class class_commands
{
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
