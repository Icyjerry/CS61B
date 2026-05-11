package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

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

    public GuitarHeroLite() {
        strings = new GuitarString[KEYS.length()];
        for (int i = 0; i < KEYS.length(); i++) {
            strings[i] = new GuitarString(FREQUENCIES[i]);
        }
    }

    public void play() {
        // 初始化画布
        StdDraw.setCanvasSize(512, 512);
        StdDraw.setXscale(0, 512);
        StdDraw.setYscale(0, 512);
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.05);
        StdDraw.text(256, 300, "数字吉他");
        StdDraw.text(256, 200, "按 1-7 演奏 Do-Re-Mi");
        StdDraw.show();

        while (true) {
            // 检测按键
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int idx = KEYS.indexOf(key);
                if (idx >= 0) {
                    strings[idx].pluck();

                    // 显示当前音符
                    StdDraw.clear();
                    StdDraw.text(256, 300, "音符: " + (idx + 1));
                    StdDraw.text(256, 200, KEYS.charAt(idx) + " = " + FREQUENCIES[idx] + " Hz");
                    StdDraw.show();
                }
            }

            // 叠加所有弦的声音
            double sample = 0;
            for (GuitarString s : strings) {
                sample += s.sample();
            }
            StdAudio.play(sample);

            // 推进所有弦
            for (GuitarString s : strings) {
                s.tic();
            }
        }
    }

    public static void main(String[] args) {
        new GuitarHeroLite().play();
    }
}
