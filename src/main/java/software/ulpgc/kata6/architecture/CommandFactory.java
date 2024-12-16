package software.ulpgc.kata6.architecture;

import software.ulpgc.kata6.architecture.control.Command;
import software.ulpgc.kata6.architecture.io.Input;
import software.ulpgc.kata6.architecture.io.Output;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {
    private final Map<String, CommandBuilder> commandBuilderMap;

    public CommandFactory() {
        commandBuilderMap = new HashMap<>();
    }

    public CommandFactory register(String key, CommandBuilder value) {
        commandBuilderMap.put(key, value);
        return this;
    }

    public Command get(String key, Input input, Output output) {
        return commandBuilderMap.get(key).build(input, output);
    }

    public interface CommandBuilder {
        Command build(Input input, Output output);
    }
}