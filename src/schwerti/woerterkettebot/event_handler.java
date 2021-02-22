package schwerti.woerterkettebot;

import java.util.List;

import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageHistory;
import net.dv8tion.jda.api.events.ReadyEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import schwerti.commands.class_commands;
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

            if(class_commands.scan_command(event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete()))
            {
                String[] args = event.getChannel().retrieveMessageById(event.getMessageIdLong()).complete().getContentRaw().split("\\s+");
                class_commands.check_command(event, args);
            }
            else
            {
                class_main_woerterkettebot.words.history.add(0, event.getMessage());
                class_event_main.main_event(event);
            }
        }
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);   
        }
    }
    public void onReady(ReadyEvent event)
    {
        
        System.out.println("Jda is now ready! Bot is alive!");
        class_main_woerterkettebot.bot = event.getJDA().getGuilds().get(0).getTextChannelsByName("bot", false).get(0);
        class_main_woerterkettebot.woerterkettebot = event.getJDA().getGuilds().get(0).getTextChannelsByName("woerterkette-bot", false).get(0);
        //class_main_woerterkettebot.bot.sendMessage("I am A L I V E !! This is the bot Channel.").queue();
        //class_main_woerterkettebot.bot.sendMessage("I am A L I V E !! This is the woerterkette-bot Channel.").queue();
        class_main_woerterkettebot.words.history = class_main_woerterkettebot.woerterkettebot.getHistory().retrievePast(100).complete();

    }
}
    
