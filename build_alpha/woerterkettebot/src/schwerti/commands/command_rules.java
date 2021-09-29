package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import schwerti.woerterkettebot.class_main_woerterkettebot;

/*
Outputs the the current active rules.
At this point we have to believe that people do respect rules like:
only Nomen etc. but we have to think about a way to scan for the compliance with at least some rules.
Dont know a way to differentiate for example Nomen right now tho.
*/

public class command_rules extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(!(event.getChannel().getName().equals("woerterkette-bot"))) //everywhere, work on a better way
        {
            String[] args = event.getMessage().getContentRaw().split("\\s+");
            if(args[0].equalsIgnoreCase(class_main_woerterkettebot.prefix + "rules"))
            {

                try
                {
                    EmbedBuilder info = new EmbedBuilder();
                    info.setColor(0xf531d4);
                    info.setTitle("Regeln");
                    info.setDescription("These are the UP TO DATE rules of the game.\nDont let the bot get mad!");
                    info.addField("NoNames", "Get creative! Dont just use names. Ok: Amazon; NOT ok: Alex", false);
                    info.addField("Morethen3", "You shouldnt use 'words' with less then 3 letters. Come on, 'ähm' is not a word. \nOk: Elf; NOT ok: hmm", false);
                    event.getChannel().sendMessage(info.build()).queue();
                }
                catch(Exception e)
                {
                    //console output:
                        e.printStackTrace();
                        e.toString();
                        System.out.println("Error while executing rules command");

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
