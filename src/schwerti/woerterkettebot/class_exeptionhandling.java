package schwerti.woerterkettebot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public class class_exeptionhandling
{

    class_exeptionhandling()
    {
        System.out.println("Exeptionhandling class is running");
    }

    //superimposition of exeption_handling function
        public static void exeption_handling(Exception e)
        {
            System.out.println("Exception Handling of unknown type");
            e.printStackTrace();
        }
        public static void exeption_handling(LoginException e)
        {
            System.out.println("Exception Handling of LoginException");
            e.printStackTrace();
        }
        public static void exeption_handling(IllegalStateException e)
        {
            System.out.println("Exception Handling of IllegalStateException");
            e.printStackTrace();
        }
        public static void exeption_handling(IndexOutOfBoundsException e)
        {
            System.out.println("Exception Handling of IndexOutOfBoundsException");
            e.printStackTrace();
        }
    //embeded
        public static void exeption_unknown_embed(GuildMessageReceivedEvent event)
        {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(0xff3923);
            error.setTitle("Error");
            error.setDescription("Something unexspected went wrong");
            event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //get function of channel is not perfect yet
        }


    
}
