package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;
import java.awt.event.KeyEvent;

public class GuitarHeroLite {
    private static final String KEYS = "1234567";
    private static final double[] FREQUENCIES = {
            261.63,  // 1 - C4
            293.66,  // 2 - D4
            329.63,  // 3 - E4
            349.23,  // 4 - F4
            392.00,  // 5 - G4
            440.00,  // 6 - A4
            493.88   // 7 - B4
    };

    private GuitarString[] strings;
    private double freqMultiplier = 1.0;

    public GuitarHeroLite() {
        rebuildStrings();
    }

    private void rebuildStrings() {
        strings = new GuitarString[KEYS.length()];
        for (int i = 0; i < KEYS.length(); i++) {
            strings[i] = new GuitarString(FREQUENCIES[i] * freqMultiplier);
        }
    }

    public void play() {
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        StdDraw.text(256, 350, "数字吉他");
        StdDraw.text(256, 250, "按 1-7 演奏 Do-Re-Mi");
        StdDraw.text(256, 150, "按 UP 升八度 | 按 DOWN 降八度");
        StdDraw.text(256, 60, "当前倍数: " + freqMultiplier + "x");
        StdDraw.show();

        while (true) {
            boolean upPressed = StdDraw.isKeyPressed(KeyEvent.VK_UP);
            boolean downPressed = StdDraw.isKeyPressed(KeyEvent.VK_DOWN);

            double nextMultiplier = 1.0;
            if (upPressed) {
                nextMultiplier = 2.0;
            } else if (downPressed) {
                nextMultiplier = 0.5;
            }
            if (nextMultiplier != freqMultiplier) {
                freqMultiplier = nextMultiplier;
                rebuildStrings();
            }

            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = KEYS.indexOf(key);
                if (idx >= 0) {
                    strings[idx].pluck();

                    StdDraw.clear();
                    StdDraw.text(256, 350, "音符: " + (idx + 1));
                    StdDraw.text(256, 250, KEYS.charAt(idx) + " = " + FREQUENCIES[idx] + " Hz");
                    StdDraw.text(256, 150, "按 UP 升八度 | 按 DOWN 降八度");
                    StdDraw.text(256, 60, "当前倍数: " + freqMultiplier + "x");
                    StdDraw.show();
                }
            }

            double sample = 0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }
            StdAudio.play(sample);

            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }

    public static void main(String[] args) {
        new GuitarHeroLite().play();
    }
}
