// Created by Dayu Wang (dwang@stchas.edu) on 2022-02-18

// Last updated by Dayu Wang (dwang@stchas.edu) on 2022-02-18


package hybrid.lecture_4;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

public class NQueenPuzzleSolver {
    public static void main(String[] args) throws IOException {
        // Open the output file.
        FileOutputStream outputFile = new FileOutputStream("./src/hybrid/lecture_4/solutions.txt");
        PrintWriter writer = new PrintWriter(outputFile);

        // Solve an Eight-Queen puzzle.
        NQueenPuzzle puzzle = new NQueenPuzzle(8);
        writer.write(puzzle.solve());

        // Close the file.
        writer.close();
        outputFile.close();
    }
}