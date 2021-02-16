package schwerti.commands;

import java.util.List;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import schwerti.woerterkettebot.class_exeptionhandling;

/*
This is the heart of the bot.
We want to scan, if the next word is right according to rules and the policy of the game.
For example:
Is the first letter of the newest word equal to the last letter of the word before?
Has this word already been used in the last 100 words.
Is the word a "nomen" for example?
*/


public class event_main extends ListenerAdapter
{
    public void onGuildMessageReceived(GuildMessageReceivedEvent event)
    {
        if(event.getChannel().equals(event.getGuild().getTextChannelsByName("woerterkette-bot",true).get(0)))
        {
            try
            {
                //everthing gets checked here
                    main_event(event);
            }


            //exeptionhandling, superimposition of exeption_handling function
            catch(Exception e)
                {
                    class_exeptionhandling.exeption_handling(e);
                }
        }
    }

    /*
    checking all the conditions
    */
    public static boolean main_event (GuildMessageReceivedEvent event)
    {
        //is the message a command if so -> dont do anthing
            if(!(class_event_handler.scan_command(event, event.getMessage())) && !(event.getAuthor().isBot()))
            {
                //get past 100 messages -> more is kinda buggy??
                    List <Message> history = event.getChannel().getHistory().retrievePast(100).complete();
                try
                {
                    //upper case bec people can not write ---> maybe we have to change that one
                        String word1 = history.get(0).getContentRaw().toUpperCase();
                        String word2 = history.get(1).getContentRaw().toUpperCase();
                    //checks for sentences, wrong first letter, characters that i can not read and if the word has already been used in last 100
                        if(check_character_ideologic(event, history) && check_first_last(event, history, word1, word2) && check_repeat(event, history, word1, word2) && check_sentence(event, history))
                        {
                            return true;
                        }
                }


                //i am thinking of some kind of message that breaks everthing, so if that message contains something that we can not predict
                catch(Exception e)
                {
                    //console error
                        System.out.println("Error! This is not something i can handle");
                    //Embed
                    error_embed(event, "This is not something i can handle!"); 
                    //del messages
                        history.get(0).delete().queue();
                        history.get(1).delete().queue();
                }
            }
        return false;
    }

    //The embed builder gets called so many times, that a function is worth i think.
        private static void error_embed(GuildMessageReceivedEvent event, String description)
        {
            //conf
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(0xff3923);
                error.setTitle("Error");
                error.setDescription(description);
            //send
                event.getGuild().getTextChannelsByName("bot",true).get(0).sendMessage(error.build()).queue();
        }

    //chinese character and stuff that i can not read
        private static boolean check_character_ideologic(GuildMessageReceivedEvent event, List<Message> history)
        {
            for(int c_count = 0; c_count < history.get(0).getContentRaw().length(); c_count = c_count + 1)
            {
                if (Character.isIdeographic(history.get(0).getContentRaw().charAt(c_count)))
                {
                    //console output
                        System.out.println("not normal character!");
                        System.out.println(history.get(0).getContentRaw().charAt(c_count));
                    //embed
                        error_embed(event, "Use characters i can read!");
                    //del message
                        history.get(0).delete().queue();
                    return false;
                }
            } 
            return true;
        }

    //if the message has more then word
        private static boolean check_sentence(GuildMessageReceivedEvent event, List<Message> history)
        {
            if(history.get(0).getContentRaw().split("\\s+").length > 1)
            {
                //Embed
                    error_embed(event, "More then one word is not allowed in this game!");
                //del message
                    history.get(0).delete().queue();
                return false;
            }
            return true;
        }

    //check if the word has already been used in the last 100
        private static boolean check_repeat(GuildMessageReceivedEvent event ,List<Message> history, String word1, String word2)
        {
            
            for (Message message : history.subList(1, history.size())) //skipping the message itself here with subList
            {
                if(message.getContentRaw().toUpperCase().equals(word1))
                {
                    System.out.println("Word has already been used in the last 100");
                    //Embed
                        error_embed(event, "This word has already been used");
                    //delete message
                        history.get(0).delete().queue();
                    return false;
                }
            }
            return true;
        }
        
    //firstletter of new message == last letter of last message
        private static boolean check_first_last(GuildMessageReceivedEvent event ,List<Message> history, String word1, String word2)
        {
            //get last and first letter:
                int len2 = word2.length();
                char firstLetter = word1.charAt(0);
                char lastLetter = word2.charAt(len2 - 1);
            //debug
                System.out.print(firstLetter);
                System.out.println(lastLetter);
            if(firstLetter == lastLetter)
            {
                System.out.println("First_Last_Letter true");
            }
            else
            {
                //console output
                    System.out.println("First_Last_Letter false");
                //Embed
                    error_embed(event, (word1 + " does not start with the last letter of " + word2));
                //delete message
                    history.get(0).delete().queue();
                return false;
            }
            return true;
        }
}
