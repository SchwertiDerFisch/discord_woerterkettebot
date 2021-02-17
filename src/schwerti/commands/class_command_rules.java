package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import schwerti.woerterkettebot.class_exeptionhandling;

/*
Outputs the the current active rules.
At this point we have to believe that people do respect rules like:
only Nomen etc. but we have to think about a way to scan for the compliance with at least some rules.
Dont know a way to differentiate for example Nomen right now tho.
*/

public class class_command_rules
{
    public static void output_rules(GuildMessageReceivedEvent event)
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
        //exeptionhandling, superimposition of exeption_handling function
        catch(Exception e)
        {
            //console output:
                class_exeptionhandling.exeption_handling(e);

            //embed of type unknow
                //class_exeptionhandling.exeption_unknown_embed(event); 
        }
    }
}
