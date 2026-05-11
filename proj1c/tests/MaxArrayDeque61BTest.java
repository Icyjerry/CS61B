import org.junit.jupiter.api.*;

import java.util.Comparator;
import deque.MaxArrayDeque61B;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class MaxArrayDeque61BTest {
    private static class StringLengthComparator implements Comparator<String> {
        public int compare(String a, String b) {
            return a.length() - b.length();
        }
    }

    @Test
    public void basicTest1() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("");
        mad.addFirst("2");
        mad.addFirst("fury road");
        assertThat(mad.max()).isEqualTo("fury road");
    }

    @Test
    public void basicTest2() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("abc");
        mad.addFirst("vfsd");
        mad.addFirst("asdvsa");
        mad.addFirst("zsdvsa");
        mad.addFirst("fasfdvsa");
        assertThat(mad.max()).isEqualTo("fasfdvsa");
    }

    @Test
    public void basicTest3() {
        MaxArrayDeque61B<String> mad = new MaxArrayDeque61B<>(new StringLengthComparator());
        mad.addFirst("abc");
        mad.addFirst("vfsd");
        mad.addFirst("asdvsa");
        mad.addFirst("zsdvsa");
        mad.addFirst("fasfdvsa");
        Comparator<String> byFirstChar = new Comparator<>() {
            @Override
            public int compare(String a, String b) {
                return a.charAt(0) - b.charAt(0);
            }
        };
        assertThat(mad.max(byFirstChar)).isEqualTo("zsdvsa");
    }
}
