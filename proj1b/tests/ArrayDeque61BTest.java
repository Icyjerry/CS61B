import jh61b.utils.Reflection;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Field;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static com.google.common.truth.Truth.assertWithMessage;

public class ArrayDeque61BTest {

     @Test
     @DisplayName("ArrayDeque61B has no fields besides backing array and primitives")
     void noNonTrivialFields() {
         List<Field> badFields = Reflection.getFields(ArrayDeque61B.class)
                 .filter(f -> !(f.getType().isPrimitive() || f.getType().equals(Object[].class) || f.isSynthetic()))
                 .toList();

         assertWithMessage("Found fields that are not array or primitives").that(badFields).isEmpty();
     }

     @Test
     public void addFirstAndAddLastTest() {
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         Deque61B<Integer> lld2 = new ArrayDeque61B<>();


         /* I've decided to add in comments the state after each call for the convenience of the
            person reading this test. Some programmers might consider this excessively verbose. */
         lld1.addLast(0);   // [0]
         lld1.addLast(1);   // [0, 1]
         lld1.addFirst(-1); // [-1, 0, 1]
         lld1.addLast(2);   // [-1, 0, 1, 2]
         lld1.addFirst(-2); // [-2, -1, 0, 1, 2]

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2).inOrder();

         lld1.addLast(0);
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         lld1.addLast(4);
         lld1.addLast(5);

         assertThat(lld1.toList()).containsExactly(-2, -1, 0, 1, 2, 0, 1, 2, 3, 4, 5).inOrder();

         lld2.addFirst(8);
         lld2.addFirst(7);
         lld2.addFirst(6);
         lld2.addFirst(5);
         lld2.addFirst(4);
         lld2.addFirst(3);
         lld2.addFirst(2);
         lld2.addFirst(1);
         lld2.addFirst(0);
         assertThat(lld2.toList()).containsExactly(0, 1, 2, 3, 4, 5, 6, 7, 8).inOrder();

     }

     @Test
     public void testGetfunction(){
         Deque61B<Integer> lld1 = new ArrayDeque61B<>();
         lld1.addLast(0);
         lld1.addLast(1);
         lld1.addLast(2);
         lld1.addLast(3);
         lld1.addLast(4);
         lld1.addLast(5);
         assertThat(lld1.get(0)).isEqualTo(0);
         assertThat(lld1.get(1)).isEqualTo(1);
         assertThat(lld1.get(2)).isEqualTo(2);
         assertThat(lld1.get(8)).isEqualTo(null);
         assertThat(lld1.get(-1)).isEqualTo(null);
     }

    @Test
    public void testGetRecursivefunction(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        assertThat(lld1.getRecursive(0)).isEqualTo(0);
        assertThat(lld1.getRecursive(1)).isEqualTo(1);
        assertThat(lld1.getRecursive(2)).isEqualTo(2);
        assertThat(lld1.getRecursive(8)).isEqualTo(null);
        assertThat(lld1.getRecursive(-1)).isEqualTo(null);
    }

    @Test
    public void testRemoveFunction(){
        Deque61B<Integer> lld1 = new ArrayDeque61B<>();
        lld1.addLast(0);
        lld1.addLast(1);
        lld1.addLast(2);
        lld1.addLast(3);
        lld1.addLast(4);
        lld1.addLast(5);
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4, 5).inOrder();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3, 4).inOrder();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(0, 1, 2, 3).inOrder();
        lld1.removeLast();
        assertThat(lld1.toList()).containsExactly(0, 1, 2).inOrder();
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(1, 2).inOrder();
        lld1.removeFirst();
        assertThat(lld1.toList()).containsExactly(2).inOrder();
        lld1.removeLast();
        assertThat(lld1.isEmpty()).isTrue();
        assertThat(lld1.removeLast()).isEqualTo(null);
    }

}
