package schwerti.woerterkettebot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.GenericMessageEvent;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.exceptions.ErrorResponseException;

public class class_exeptionhandling
{
    //superimposition of exeption_handling function
        public static void exeption_handling(Exception e)
        {
            if(e instanceof IllegalArgumentException)
            {
                System.out.println("Exception Handling of IllegalArgumentException");
            }
            else if(e instanceof LoginException )
            {
                System.out.println("Exception Handling of LoginException");
            }
            else if(e instanceof IllegalStateException)
            {
                System.out.println("Exception Handling of IllegalStateException");
            }
            else if(e instanceof IndexOutOfBoundsException)
            {
                System.out.println("Exception Handling of IndexOutOfBoundsException");
            }
            else if(e instanceof ErrorResponseException)
            {
                System.out.println("Exception Handling of ErrorResponseException");
            }
            else
            {
                System.out.println("Exception Handling of unknown type");
                e.printStackTrace();
            }
            System.out.println("Error Handeled!");
        }
  
    //embeded
        public static void exeption_unknown_embed(GenericMessageEvent event)
        {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(0xff3923);
            error.setTitle("Error");
            error.setDescription("Something unexspected went wrong");
            event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //get function of channel is not perfect yet
        }

        public static void exeption_unknown_embed(GuildMessageReceivedEvent event)
        {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(0xff3923);
            error.setTitle("Error");
            error.setDescription("Something unexspected went wrong");
            event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //get function of channel is not perfect yet
        }

        public static void exeption_wrong_usage_embed(GuildMessageReceivedEvent event)
        {
            EmbedBuilder error = new EmbedBuilder();
            error.setColor(0xff3923);
            error.setTitle("Error");
            error.setDescription("This is not how the command works!");
            event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //get function of channel is not perfect yet
        }


    
}
