package schwerti.commands;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

/*
This is the heart of the bot.
We want to scan, if the next word is right according to rules and the policy of the game.
For example:
Is the first letter of the newest word equal to the last letter of the word before?
Has this word already been used in the last 100 words.
Is the word a "nomen" for example?
*/


public class event_DeleteSecond extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(event.getChannel().equals(event.getGuild().getTextChannelsByName("woerterkette-bot",true).get(0))) //i need a better solution here
        {
            String newest = "";
            String tmp = "";
            try
            {
                List<Message> history = event.getChannel().getHistory().retrievePast(100).complete(); //maybe i should get more words here, for debug reasons only 100
                
                //here i am thinking about adding a function to distinguish bot messages and wörterketten messages
                //->then we dont need a second channel but not sure if i even want this (maybe the channel gets spammed then)
                newest = history.get(0).getContentRaw();
                tmp = history.get(1).getContentRaw();

                checkString(newest, tmp, history, event);

            }
            //ive put some effort in the execption handling of this part. Testing will /will not confirm if this is enough.
            catch(IndexOutOfBoundsException e)
            {
                //console output:
                    e.printStackTrace();
                    e.toString();
                    System.out.println("IndexOutOfBoundsException in the main application");

                //idea?? maybe put into general error handle output function
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(0xff3923);
                    error.setTitle("Error");
                    error.setDescription("Something u should not know went wrong.");
                    event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue();
            }
            catch(Exception e)
            {
                //console output:
                    e.printStackTrace();
                    e.toString();
                    System.out.println("Something went wrong with the main event of the application");
                    
                //idea?? maybe put into general error handle output function
                    EmbedBuilder error = new EmbedBuilder();
                    error.setColor(0xff3923);
                    error.setTitle("Error");
                    error.setDescription("Something u should not know went wrong.");
                    event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue();
            }
        }
    }

    /*
    checking all the conditions
    */
    public static boolean checkString (String word1, String word2, List<Message> history, GuildMessageReceivedEvent event)
    {
        EmbedBuilder error = new EmbedBuilder();
        error.setColor(0xff3923);
        error.setTitle("Error");
        try
        {
            //upper case bec people can not write ---> maybe we have to change that one
                word1 = word1.toUpperCase();
                word2 = word2.toUpperCase();
            //get last and first letter:
                int len2 = word2.length();
                char firstLetter = word1.charAt(0);
                char lastLetter = word2.charAt(len2 - 1);
            //debug
                System.out.print(firstLetter);
                System.out.println(lastLetter);

            //debug, final build swap if condition (invert condition and del the output in console)
            if(firstLetter == lastLetter)
            {
                System.out.println("First_Last_Letter true");
            }
            else
            {
                System.out.println("First_Last_Letter false");

                history.get(0).delete().queue();
                error.setDescription(word1 + " does not start with the last letter of " + word2);
                event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //again thinking of better way to get channel
                return false;
            }
            
            for (Message message : history.subList(1, history.size())) //skipping the message itself here with subList
            {
                if(message.getContentRaw().toUpperCase().equals(word1))
                {
                    System.out.println("Word has already been used in the last 100");

                    history.get(0).delete().queue();
                    error.setDescription(word1 + " has already been used in the last 100");
                    event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //again thinking of better way to get channel
                    return false;
                }
            }

            System.out.println("Word is right and not used in the last 100");
            return true;
          
        }
        //i am thinking of some kind of message that breaks everthing, so if that message contains something that we can not predict
        //here we go
        catch(Exception e)
        {
            history.get(0).delete().queue();
            history.get(1).delete().queue();
            System.out.println("Error! This is not something i can handle");
            error.setColor(0xff3923);
            error.setTitle("Error");
            error.setDescription("Error! This is not something i can handle");
            event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue(); //yes
            return false;
        }
    }
        
}
