package loaders;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import callers.serverCommandCaller;
import common.descriptions.CommandDescription;
import common.descriptions.LoadDescription;
import managers.AbstractLoader;
import managers.BaseTextReceiver;
import modules.InteractiveMode;

public class ConsoleLoader extends AbstractLoader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public ConsoleLoader(BaseTextReceiver textReceiver) {
        super(textReceiver);
    }

    public CommandDescription parseCommand(Map<String, CommandDescription> commandDescriptionMap, String command) {
        List<String> commandParts = List.of(command.split(" "));
        if (commandParts.size() == 0) {
            throw new RuntimeException("Command is empty!");
        }
        if (commandDescriptionMap.containsKey(commandParts.get(0))) {
            CommandDescription commandDescription = null;
            try {
                commandDescription = commandDescriptionMap.get(commandParts.get(0)).clone();
            } catch (CloneNotSupportedException e) {
                throw new RuntimeException(e);
            }
            if(commandDescription.getOneLineArguments() == null) {
                if(commandParts.size() != 1) {
                    throw new RuntimeException("Wrong number of arguments!");
                }
            }else {
                if (commandDescription.getOneLineArguments().size() != commandParts.size() - 1) {
                    throw new RuntimeException("Wrong number of arguments!");
                }
            }
            if(commandDescription.getOneLineArguments() != null) {
                CommandDescription finalCommandDescription = commandDescription;
                IntStream.range(0, commandDescription.getOneLineArguments().size())
                        .forEach(i -> finalCommandDescription.getOneLineArguments()
                                .get(i)
                                .setValue(
                                        parse(commandParts.get(i),
                                                finalCommandDescription.getOneLineArguments()
                                                        .get(i)
                                                        .getType()
                                        )
                                ));
            }
            if(commandDescription.getArguments() == null) {
                return commandDescription;
            }
            commandDescription.getArguments()
                    .stream()
                    .forEach(loadDescription -> {
                        loadDescription = enterWithMessage("Enter arguments of command according to description:\"" + loadDescription.getDescription() +  "\"",loadDescription);
                    });
            return commandDescription;
        } else {
            throw new RuntimeException("Unknown command!");
        }
    }


    public <T extends LoadDescription<Enum>> T enterEnum(String s, T t, BaseTextReceiver baseTextReceiver) {
        baseTextReceiver.print(s);
        try {
            String line = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        while (true) {
            try {
                return (T) t.setValue(Enum.valueOf((Class<Enum>) t.getType(), s));
            } catch (Exception e) {
                textReceiver.println(e.getMessage());
            }
        }
    }

    @Override
    public <T extends LoadDescription<?>> T enterWrapper(T t) {
        String s = null;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                return (T)t.setValue(parse(s, (Class<?>)t.getType()));
            } catch (Exception e) {
                textReceiver.println(e.getMessage());
            }
        }
    }

    @Override
    public LoadDescription<String> enterString(LoadDescription<String> description) {
        String s;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true){
            try {
                return description.setValue(s);
            } catch (Exception e) {
                textReceiver.println(e.getMessage());
            }
        }
    }

    @Override
    public <T extends LoadDescription<Enum>> T enterEnum(T t) {
        String s;
        try {
            s = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (true) {
            try {
                return (T) t.setValue(parse(s, (Class<Enum>) t.getType()));
            } catch (Exception e) {
                textReceiver.println(e.getMessage());
            }
        }
    }
}