package com.javax0.blog;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Arrays;

public class GraphvizTest1 {

    private int counter;
    private int loopSize;
    private int sumLoopSize;
    private int minLoopSize;
    private int maxLoopSize;

    @DisplayName("create the graph for N elements")
    @Test
    void createGraphvizForNElements() throws IOException {
        final var sb = new StringBuilder();
        for (int N = 2; N < 10000; N++) {
            final var wasMoved = new Boolean[2 * N - 1];
            Arrays.fill(wasMoved, false);
            int i = 1;
            wasMoved[0] = true;
            counter = 1;
            loopSize = 0;
            sumLoopSize = 0;
            minLoopSize = Integer.MAX_VALUE;
            maxLoopSize = Integer.MIN_VALUE;
            while (Arrays.stream(wasMoved).anyMatch(a -> !a)) {
                if (wasMoved[i]) {
                    counter++;
                    calculateLoopSizes();
                    //noinspection StatementWithEmptyBody
                    for (i = 0; wasMoved[i]; i++)
                        ;
                } else {
                    final int j = (i < N) ? (2 * i) : 2 * (i - N) + 1;
                    wasMoved[i] = true;
                    loopSize++;
                    i = j;
                }
            }
            calculateLoopSizes();
            sb.append(N).append(",").append(counter).append(",").append(sumLoopSize/maxLoopSize).append("\n");
            System.out.println(N);
            Assertions.assertEquals(2*N-2,sumLoopSize);
        }
        Files.writeString(Paths.get("graph.csv"), sb.toString(), StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING);
    }

    private void calculateLoopSizes() {
        if (maxLoopSize < loopSize) {
            maxLoopSize = loopSize;
        }
        if (minLoopSize > loopSize) {
            minLoopSize = loopSize;
        }
        sumLoopSize += loopSize;
        loopSize = 0;
    }
}
