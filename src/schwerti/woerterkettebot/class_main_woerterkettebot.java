package schwerti.woerterkettebot;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.AccountType;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;

import schwerti.commands.command_clear;
import schwerti.commands.command_help;
import schwerti.commands.command_info;
import schwerti.commands.command_rules;
import schwerti.commands.event_DeleteSecond;

public class class_main_woerterkettebot
{
    //Global variables that have to be know by Events, commands
        public final static String prefix = "!!";

    //functions
        public static JDABuilder connect()
        {
            //maybe i let a file reading methode read the token out of a file
            //bec writing it here like this.....not the best way
            final String token = "";    //i deleted it out of here bec of github
            final JDABuilder builder;
            builder = new JDABuilder(AccountType.BOT);  //maybe we have some issues here, -> "the constructor JDABuilder(AccountType) is deprecated"

            //bot general config
                builder.setToken(token);
                builder.setActivity(Activity.watching("Wörterkette"));
                builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

            //handlers:
                builder.addEventListeners(new command_clear());
                builder.addEventListeners(new event_DeleteSecond());
                builder.addEventListeners(new command_info());
                builder.addEventListeners(new command_rules());
                builder.addEventListeners(new command_help());

            try
            {
                builder.build();
            }
            //exeptionhandling, superimposition of exeption_handling function
            catch(LoginException e)
            {
                class_exeptionhandling.exeption_handling(e);
            }
            catch(IllegalStateException e)
            {
                class_exeptionhandling.exeption_handling(e);
            }
            catch(Exception e)
            {
                //console output:
                class_exeptionhandling.exeption_handling(e);

                //embed of type unknow
                    //class_exeptionhandling.exeption_unknown_embed(event);
            }

            return builder;
        }

    //Startpoint
        public static void main(String[] args)
        {
            JDABuilder builder = connect();
        }

}
