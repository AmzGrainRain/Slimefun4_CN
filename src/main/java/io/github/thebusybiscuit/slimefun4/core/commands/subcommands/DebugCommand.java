package io.github.thebusybiscuit.slimefun4.core.commands.subcommands;

import io.github.thebusybiscuit.slimefun4.core.commands.SlimefunCommand;
import io.github.thebusybiscuit.slimefun4.core.commands.SubCommand;
import io.github.thebusybiscuit.slimefun4.core.debug.Debug;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import org.bukkit.command.CommandSender;

import javax.annotation.Nonnull;
import java.util.Collections;
import java.util.List;

/**
 * The debug command will allow server owners to get information for us developers.
 * We can put debug messages in the code and they can trigger it for us to see what exactly is going on.
 *
 * @author WalshyDev
 */
public class DebugCommand extends SubCommand {

    public static final List<String> tabCompletions = Collections.singletonList("disable");

    protected DebugCommand(@Nonnull Slimefun plugin, @Nonnull SlimefunCommand cmd) {
        super(plugin, cmd, "debug", true);
    }

    @Override
    protected @Nonnull String getDescription() {
        return "commands.debug.description";
    }

    @Override
    public void onExecute(@Nonnull CommandSender sender, @Nonnull String[] args) {
        if (!sender.hasPermission("slimefun.command.debug")) {
            Slimefun.getLocalization().sendMessage(sender, "messages.no-permission", true);
            return;
        }

        if (args.length == 1) {
            Slimefun.getLocalization().sendMessage(sender, "commands.debug.current", true,
                msg -> msg.replace("%test_case%", Debug.getTestCase() != null ? Debug.getTestCase() : "None")
            );
            return;
        }

        String test = args[1];

        if (test.equalsIgnoreCase("disable") || test.equalsIgnoreCase("off")) {
            Debug.setTestCase(null);
            Slimefun.getLocalization().sendMessage(sender, "commands.debug.disabled");
        } else {
            Debug.setTestCase(test);
            Slimefun.getLocalization().sendMessage(sender, "commands.debug.running",
                msg -> msg.replace("%test%", test));
        }
    }
}
