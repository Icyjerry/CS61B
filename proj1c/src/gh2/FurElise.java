package gh2;

import edu.princeton.cs.algs4.StdAudio;
import edu.princeton.cs.algs4.StdDraw;

import java.awt.Color;
import java.awt.Font;
import java.util.HashMap;
import java.util.Map;

/**
 * Beethoven - Für Elise
 *
 * 更完整的 3/8 拍版本
 * 严格基于：
 * - eighth note
 * - sixteenth note
 * - dotted quarter note
 *
 * 使用 Karplus-Strong GuitarString 合成
 */
public class FurElise {

    /* =========================================================
       MIDI -> Frequency
       ========================================================= */

    private static double midiToFreq(int midi) {
        return 440.0 * Math.pow(2.0, (midi - 69.0) / 12.0);
    }

    /* =========================================================
       播放单音
       ========================================================= */

    private static void playNote(GuitarString gs, double duration) {

        int samples =
                (int) (StdAudio.SAMPLE_RATE * duration);

        for (int i = 0; i < samples; i++) {

            StdAudio.play(gs.sample());

            gs.tic();
        }
    }

    /* =========================================================
       UI
       ========================================================= */

    private static void drawUI(
            String note,
            double freq,
            int current,
            int total,
            int measure) {

        StdDraw.clear(new Color(245, 245, 245));

        /* ---------- title ---------- */

        StdDraw.setPenColor(new Color(30, 30, 30));

        StdDraw.setFont(new Font("Serif", Font.BOLD, 30));

        StdDraw.text(400, 320,
                "Für Elise");

        StdDraw.setFont(new Font("Serif", Font.PLAIN, 18));

        StdDraw.text(400, 290,
                "Ludwig van Beethoven");

        /* ---------- note info ---------- */

        StdDraw.setFont(new Font("Monaco", Font.PLAIN, 20));

        StdDraw.text(400, 220,
                "Current Note : " + note);

        StdDraw.text(400, 185,
                String.format("Frequency   : %.2f Hz", freq));

        StdDraw.text(400, 150,
                "Measure     : " + measure);

        StdDraw.text(400, 115,
                "Progress    : "
                        + current
                        + " / "
                        + total);

        /* ---------- progress bar ---------- */

        double progress =
                (double) current / total;

        StdDraw.setPenColor(Color.LIGHT_GRAY);

        StdDraw.filledRectangle(
                400,
                60,
                250,
                12);

        StdDraw.setPenColor(
                new Color(60, 60, 60));

        StdDraw.filledRectangle(
                150 + progress * 250,
                60,
                progress * 250,
                12);

        StdDraw.show();
    }

    /* =========================================================
       Main
       ========================================================= */

    public static void main(String[] args) {

        /* =====================================================
           Tempo
           ===================================================== */

        // Beethoven 常见演奏速度：
        // 72~84 BPM

        double BPM = 78;

        // quarter note

        double quarter =
                60.0 / BPM;

        // 3/8 拍核心单位

        double eighth =
                quarter / 2.0;

        double sixteenth =
                quarter / 4.0;

        double dottedQuarter =
                quarter * 1.5;

        /* =====================================================
           UI Setup
           ===================================================== */

        StdDraw.setCanvasSize(800, 400);

        StdDraw.setXscale(0, 800);

        StdDraw.setYscale(0, 400);

        StdDraw.enableDoubleBuffering();

        /* =====================================================
           Melody
           ===================================================== */

        String[] notes = {

                /* =========================
                   A SECTION
                   ========================= */

                // m1
                "E5", "D#5", "E5",
                "D#5", "E5", "B4",

                // m2
                "D5", "C5", "A4",

                // m3
                "C4", "E4", "A4", "B4",

                // m4
                "E4", "G#4", "B4", "C5",

                // m5
                "E5", "D#5", "E5",
                "D#5", "E5", "B4",

                // m6
                "D5", "C5", "A4",

                // m7
                "C4", "E4", "A4", "B4",

                // m8
                "E4", "C5", "B4", "A4",

                /* =========================
                   B SECTION
                   ========================= */

                // m9
                "B4", "C5", "D5", "E5",

                // m10
                "G5", "F5", "E5", "D5",

                // m11
                "F5", "E5", "D5", "C5",

                // m12
                "E5", "D5", "C5", "B4",

                /* =========================
                   RETURN
                   ========================= */

                // m13
                "E5", "D#5", "E5",
                "D#5", "E5", "B4",

                // m14
                "D5", "C5", "A4",

                // m15
                "C4", "E4", "A4", "B4",

                // m16
                "E4", "G#4", "B4", "C5",

                // m17
                "E5", "D#5", "E5",
                "D#5", "E5", "B4",

                // m18
                "D5", "C5", "A4",

                // m19
                "C4", "E4", "A4", "B4",

                // m20
                "E4", "C5", "B4", "A4"
        };

        /* =====================================================
           Rhythm
           ===================================================== */

        double[] durations = {

                /* ===== A ===== */

                // m1
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,

                // m2
                sixteenth,
                eighth,
                dottedQuarter,

                // m3
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m4
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m5
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,

                // m6
                sixteenth,
                eighth,
                dottedQuarter,

                // m7
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m8
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                /* ===== B ===== */

                // m9
                eighth,
                eighth,
                eighth,
                eighth,

                // m10
                eighth,
                eighth,
                eighth,
                eighth,

                // m11
                eighth,
                eighth,
                eighth,
                eighth,

                // m12
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                /* ===== RETURN ===== */

                // m13
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,

                // m14
                sixteenth,
                eighth,
                dottedQuarter,

                // m15
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m16
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m17
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,
                sixteenth,

                // m18
                sixteenth,
                eighth,
                dottedQuarter,

                // m19
                eighth,
                eighth,
                eighth,
                dottedQuarter,

                // m20
                eighth,
                eighth,
                eighth,
                dottedQuarter
        };

        /* =====================================================
           MIDI Map
           ===================================================== */

        Map<String, Integer> midi =
                new HashMap<>();

        midi.put("C4", 60);
        midi.put("E4", 64);
        midi.put("G#4", 68);
        midi.put("A4", 69);
        midi.put("B4", 71);

        midi.put("C5", 72);
        midi.put("D5", 74);
        midi.put("D#5", 75);
        midi.put("E5", 76);
        midi.put("F5", 77);
        midi.put("G5", 79);

        /* =====================================================
           Guitar Strings
           ===================================================== */

        Map<String, GuitarString> strings =
                new HashMap<>();

        for (String note : midi.keySet()) {

            double freq =
                    midiToFreq(midi.get(note));

            strings.put(
                    note,
                    new GuitarString(freq));
        }

        /* =====================================================
           Playback
           ===================================================== */

        int total = notes.length;

        for (int i = 0; i < total; i++) {

            String note = notes[i];

            double duration = durations[i];

            int measure = i / 4 + 1;

            GuitarString gs =
                    strings.get(note);

            double freq =
                    midiToFreq(midi.get(note));

            // 拨弦
            gs.pluck();

            // UI
            drawUI(
                    note,
                    freq,
                    i + 1,
                    total,
                    measure);

            // 播放
            playNote(gs, duration);

            // 微小停顿
            try {
                Thread.sleep(12);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        /* =====================================================
           Ending Screen
           ===================================================== */

        StdDraw.clear(new Color(245, 245, 245));

        StdDraw.setPenColor(Color.BLACK);

        StdDraw.setFont(
                new Font("Serif",
                        Font.BOLD,
                        36));

        StdDraw.text(
                400,
                220,
                "♪ End of Für Elise ♪");

        StdDraw.setFont(
                new Font("Serif",
                        Font.PLAIN,
                        22));

        StdDraw.text(
                400,
                170,
                "Ludwig van Beethoven");

        StdDraw.show();
    }
}