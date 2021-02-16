package schwerti.woerterkettebot;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Paths;
import java.util.Scanner;

import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.OnlineStatus;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import schwerti.commands.command_clear;
import schwerti.commands.command_help;
import schwerti.commands.command_info;
import schwerti.commands.command_rules;
import schwerti.commands.event_main;
import schwerti.commands.class_event_handler;

public class class_main_woerterkettebot {
    // constructor
    private class_main_woerterkettebot() {
        try {
            // init
            final JDABuilder builder;
            builder = JDABuilder.createDefault(
                get_token(),
                GatewayIntent.GUILD_MEMBERS,
                GatewayIntent.GUILD_MESSAGES,
                GatewayIntent.DIRECT_MESSAGES,
                GatewayIntent.GUILD_VOICE_STATES,
                GatewayIntent.GUILD_EMOJIS
            );

            // bot general config
            builder.setActivity(Activity.watching("Wörterkette"));
            builder.setStatus(OnlineStatus.DO_NOT_DISTURB);

            // handlers:
                builder.addEventListeners(new command_clear());
                builder.addEventListeners(new event_main());
                builder.addEventListeners(new command_info());
                builder.addEventListeners(new command_rules());
                builder.addEventListeners(new command_help());
            //builder.addEventListeners(new event_handler());

            builder.build();
        }
        // exeptionhandling, superimposition of exeption_handling function
        catch (Exception e) {
            // console output:
            class_exeptionhandling.exeption_handling(e);
        }
    }

    // Global variables that have to be know by Events, commands
    public final static String prefix = "!!";

    // Startpoint
    public static void main(String[] args)
    {
        new class_main_woerterkettebot();
    }

    private static String get_token()
    {
        System.out.println("get_token");
        String fileName = "./notes.txt";
        return readUsingScanner(fileName);
    }
    private static String readUsingScanner(String fileName) {
		Scanner scanner = null;
        try
        {
			scanner = new Scanner(Paths.get(fileName), StandardCharsets.UTF_8.name());
            // we can use Delimiter regex as "\\A", "\\Z" or "\\z"
			return scanner.useDelimiter("\\A").next();
        }
        catch (IOException e)
        {
            class_exeptionhandling.exeption_handling(e);
            System.out.println("The token could be loaded!");
			return null;
        }
        finally
        {
			if (scanner != null)
				scanner.close();
		}

	}

}
