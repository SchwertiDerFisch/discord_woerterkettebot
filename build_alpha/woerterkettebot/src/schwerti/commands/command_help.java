package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
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
        if(!(event.getChannel().getName().equals("woerterkette-bot"))) //i dont think thats a beautiful way of doing thinks.
        {
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if(args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "help"))
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
                    info.addField("!!regeln", "Shows you the current rules of the game", false);
                    event.getChannel().sendMessage(info.build()).queue();
                }
                //-----yes
                catch(Exception e)
                {
                    //console output:
                        e.printStackTrace();
                        e.toString();
                        System.out.println("Error while executing help command");

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