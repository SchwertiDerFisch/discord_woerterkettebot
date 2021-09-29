package schwerti.commands;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import schwerti.woerterkettebot.class_exeptionhandling;
import schwerti.woerterkettebot.class_main_woerterkettebot;


/*
This is the heart of the bot.
We want to scan, if the next word is right according to rules and the policy of the game.
For example:
Is the first letter of the newest word equal to the last letter of the word before?
Has this word already been used in the last 100 words.
Is the word a "nomen" for example?
*/


public class class_event_main
{
    /*
    checking all the conditions
    */
    public static boolean main_event (GuildMessageReceivedEvent event)
    {

        //get past 100 messages -> more is kinda buggy?
        try
        {
            //upper case bec people can not write ---> maybe we have to change that one
                //Message m_word1 = history.get(0);
                //Message m_word2 = history.get(1);
                
                class_main_woerterkettebot.words.word1 = class_main_woerterkettebot.words.history.get(0).getContentRaw().toUpperCase();
                class_main_woerterkettebot.words.word2 = class_main_woerterkettebot.words.history.get(1).getContentRaw().toUpperCase();
                /*                         
                int i = 0;
                int a = 1;
                for(i = 0; (m_word1.getAuthor().isBot() || event_handler.scan_command(m_word1)); i = i + 1)
                {
                    word1 = history.get(i+1).getContentRaw().toUpperCase();
                    m_word1 = history.get(i+1);
                    a = i + 1;
                }
                //PLS DONT LOOK AT THE NEXT LINE OMG WHAT IS THIS a=a WHY DONT I KNOW ANOTHER WAY AHHHHHHHHHHHHHHHHHH
                for(a = a; (m_word2.getAuthor().isBot() || event_handler.scan_command(m_word2)); a = a + 1)
                {
                    word2 = history.get(a+1).getContentRaw().toUpperCase();
                    m_word2 = history.get(a+1);
                    System.out.println("a in for: " + a);
                }
                System.out.println("Word 1: " + word1);
                System.out.println(word2);
                */
                
            //checks for sentences, wrong first letter, characters that i can not read and if the word has already been used in last 100
            // || !(check_sentence(event))
                if(!(check_character_ideologic()) || !(check_first_last()) || !(check_repeat()))
                {
                    class_main_woerterkettebot.words.history.remove(0);
                    return true;
                }     
        }

        //i am thinking of some kind of message that breaks everthing, so if that message contains something that we can not predict
        catch(Exception e)
        {
            //console error
                System.out.println("Error! This is not something i can handle");
                class_exeptionhandling.exeption_handling(e);
            //del messages
                event.getMessage().delete().queue();
                class_main_woerterkettebot.words.history.remove(0);
        }
            
        return false;
    }

    //The embed builder gets called so many times, that a function is worth i think.
        private static void error_embed(String description)
        {
            //conf
                EmbedBuilder error = new EmbedBuilder();
                error.setColor(0xff3923);
                error.setTitle("Error");
                error.setDescription(description);
            //send
                class_main_woerterkettebot.bot.sendMessage(error.build()).queue();
        }

    //chinese characFalschter and stuff that i can not read
        private static boolean check_character_ideologic()
        {
                //^[öÖäÄüÜßa-zA-Z]*$        [a-zA-Z]+
                if(!(class_main_woerterkettebot.words.history.get(0).getContentRaw().matches("^[öÖäÄüÜßa-zA-Z]*$")))
                {
                    //console output
                        System.out.println("Symbol Error");
                    class_main_woerterkettebot.words.history.get(0).delete().queue();
                    return false;
                }
            //} 
            return true;
        }

    //if the message has more then word
        /* 
        private static boolean check_sentence(GuildMessageReceivedEvent event)
        {
            if(event.getMessage().getContentRaw().split("\\s+").length > 1)
            {
                //del message
                    event.getMessage().delete().queue();
                return false;
            }
            return true;
        } 
        */

    //check if the word has already been used in the last 100
        private static boolean check_repeat()
        {
            
            for (Message message : class_main_woerterkettebot.words.history.subList(1, class_main_woerterkettebot.words.history.size())) //skipping the message itself here with subList
            {
                if(message.getContentRaw().toUpperCase().equals(class_main_woerterkettebot.words.word1))
                {
                    //console output
                        System.out.println("Word 100");
                    //Embed
                        error_embed("This word has already been used");
                    //delete message
                        class_main_woerterkettebot.words.history.get(0).delete().queue();
                    return false;
                }
            }
            return true;
        }
        
    //firstletter of new message == last letter of last message
        private static boolean check_first_last()
        {
            //get last and first letter:
                if(class_main_woerterkettebot.words.word1.charAt(0) != class_main_woerterkettebot.words.word2.charAt(class_main_woerterkettebot.words.word2.length() - 1))
                {
                    //console output
                        System.out.println("First_Last_Letter false");
                    //delete message
                        class_main_woerterkettebot.words.history.get(0).delete().queue();  
                    return false;
                }
            return true;
        }
}
