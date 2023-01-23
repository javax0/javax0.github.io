package com.javax0.blog.tenjava;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.Stream;

import static com.javax0.blog.tenjava.Setout.resetout;
import static com.javax0.blog.tenjava.Setout.setout;

public class StreamIsMutating {

    @Test
    void streamMutates() throws Exception {
        setout("STREAM.TXT");
        // snippet stream
        final Stream<Object> stream = Stream.of("a", 2, 3, new Object[2]);
        final var stringStream1 = stream.map(Object::toString);
        try {
            final var stream2 = stream.map(obj -> " " + obj);
        } catch (IllegalStateException e) {
            System.out.println(e);
        }
        final var string = stringStream1.collect(Collectors.joining("\n"));
        // end snippet
        resetout();
    }

}
