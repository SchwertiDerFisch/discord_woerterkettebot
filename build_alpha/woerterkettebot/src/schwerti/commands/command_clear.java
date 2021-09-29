package schwerti.commands;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
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
        String[] args = event.getMessage().getContentRaw().split("\\s+");
        if(args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "clear"))
        {
            if(args.length < 2)
            {
                //error Message --> Not finished yet 
            }
            else
            {
                List<Message> messages = event.getChannel().getHistory().retrievePast(Integer.parseInt(args[1])).complete();
                try
                {
                    //---->output in bot channel maybe???
                    //have to fix that the clear message itself is counted with the clear amount

                    System.out.println(messages + " have / has been deleted");
                    event.getChannel().deleteMessages(messages).queue();
                }
                ///--------------------------->missing exception handling here
                catch(Exception e)
                {
                    //console output:
                        e.printStackTrace();
                        e.toString();
                        System.out.println("Error while executing clear command");

                    //idea?? maybe put into general error handle output function
                        EmbedBuilder error = new EmbedBuilder();
                        error.setColor(0xff3923);
                        error.setTitle("Error");
                        error.setDescription("Something u should not know went wrong.");
                        event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue();
                }
            }
        }
    }
}
