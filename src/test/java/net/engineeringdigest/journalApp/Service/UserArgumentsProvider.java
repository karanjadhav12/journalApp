package net.engineeringdigest.journalApp.Service;

import org.junit.jupiter.api.extension.ExtensionContext;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.ArgumentsProvider;

import java.util.stream.Stream;

public class UserArgumentsProvider implements ArgumentsProvider {

    @Override
    public Stream<? extends Arguments> provideArguments(ExtensionContext extensionContext) throws Exception {
        return Stream.of(
                Arguments.of(net.engineeringdigest.journalApp.entity.User.builder().username("RAM").password("RAM").build()),
                Arguments.of(net.engineeringdigest.journalApp.entity.User.builder().username("SHYAM").password("SHYAM").build())
        );
    }
}
