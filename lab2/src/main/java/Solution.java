import engine.Engine;

import java.io.*;

public class Solution {
    public static void main(String[] args) throws IOException {
        Engine engine = new Engine("text.txt");
        engine.modify();
        engine.sort();
    }
}
