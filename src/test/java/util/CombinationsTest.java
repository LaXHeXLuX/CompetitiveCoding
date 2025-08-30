package util;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.*;

class CombinationsTest {

    @Test
    void factorialBigInteger() {
        assertThrows(
                RuntimeException.class,
                () -> Combinations.factorialBigInteger(-1)
        );
        assertEquals(
                new BigInteger("1"),
                Combinations.factorialBigInteger(1)
        );
        assertEquals(
                new BigInteger("3628800"),
                Combinations.factorialBigInteger(10)
        );
        assertEquals(
                new BigInteger("93326215443944152681699238856266700490715968264381621468592963895217599993229915608941463976156518286253697920827223758251185210916864000000000000000000000000"),
                Combinations.factorialBigInteger(100)
        );
        assertEquals(
                new BigInteger("402387260077093773543702433923003985719374864210714632543799910429938512398629020592044208486969404800479988610197196058631666872994808558901323829669944590997424504087073759918823627727188732519779505950995276120874975462497043601418278094646496291056393887437886487337119181045825783647849977012476632889835955735432513185323958463075557409114262417474349347553428646576611667797396668820291207379143853719588249808126867838374559731746136085379534524221586593201928090878297308431392844403281231558611036976801357304216168747609675871348312025478589320767169132448426236131412508780208000261683151027341827977704784635868170164365024153691398281264810213092761244896359928705114964975419909342221566832572080821333186116811553615836546984046708975602900950537616475847728421889679646244945160765353408198901385442487984959953319101723355556602139450399736280750137837615307127761926849034352625200015888535147331611702103968175921510907788019393178114194545257223865541461062892187960223838971476088506276862967146674697562911234082439208160153780889893964518263243671616762179168909779911903754031274622289988005195444414282012187361745992642956581746628302955570299024324153181617210465832036786906117260158783520751516284225540265170483304226143974286933061690897968482590125458327168226458066526769958652682272807075781391858178889652208164348344825993266043367660176999612831860788386150279465955131156552036093988180612138558600301435694527224206344631797460594682573103790084024432438465657245014402821885252470935190620929023136493273497565513958720559654228749774011413346962715422845862377387538230483865688976461927383814900140767310446640259899490222221765904339901886018566526485061799702356193897017860040811889729918311021171229845901641921068884387121855646124960798722908519296819372388642614839657382291123125024186649353143970137428531926649875337218940694281434118520158014123344828015051399694290153483077644569099073152433278288269864602789864321139083506217095002597389863554277196742822248757586765752344220207573630569498825087968928162753848863396909959826280956121450994871701244516461260379029309120889086942028510640182154399457156805941872748998094254742173582401063677404595741785160829230135358081840096996372524230560855903700624271243416909004153690105933983835777939410970027753472000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000000"),
                Combinations.factorialBigInteger(1_000)
        );
    }
    @Test
    void factorialLong() {
        assertThrows(RuntimeException.class, () -> Combinations.factorial(-1));
        assertEquals(1, Combinations.factorial(1));
        long temp = 1;
        for (int i = 2; i < 5; i++) {
            temp *= i;
            assertEquals(temp, Combinations.factorial(i));
        }
        assertEquals(2432902008176640000L, Combinations.factorial(20));
        assertThrows(RuntimeException.class, () -> Combinations.factorial(100));
    }
    @Test
    void nChooseMBigInteger() {
        for (int i = 1; i < 5; i++) {
            assertEquals(BigInteger.valueOf(1), Combinations.nChooseMBigInteger(i, i));
            assertEquals(BigInteger.valueOf(1), Combinations.nChooseMBigInteger(i, 0));
            assertEquals(BigInteger.valueOf(i), Combinations.nChooseMBigInteger(i, 1));
        }
        assertEquals(new BigInteger("100891344545564193334812497256"), Combinations.nChooseMBigInteger(100, 50));
    }
    @Test
    void nChooseMOrderMattersLong() {
        for (int i = 1; i < 5; i++) {
            assertEquals(1, Combinations.nChooseMOrderMattersLong(i, 0));
            assertEquals(i, Combinations.nChooseMOrderMattersLong(i, 1));
        }
        assertEquals(30240, Combinations.nChooseMOrderMattersLong(10, 5));
        assertEquals(3628800, Combinations.nChooseMOrderMattersLong(10, 10));
    }
    @Test
    void ChooseNElements() {
        assertArrayEquals(new int[][] {}, Combinations.chooseNElements(new int[] {1, 2, 3}, 0, false));
        assertArrayEquals(new int[][] {{1}, {2}, {3}}, Combinations.chooseNElements(new int[] {1, 2, 3}, 1, false));
        assertArrayEquals(new int[][] {{1}, {2}, {3}}, Combinations.chooseNElements(new int[] {1, 2, 3}, 1, true));
        assertArrayEquals(new int[][] {{1, 1}, {1, 2}, {1, 3}, {2, 2}, {2, 3}, {3, 3}}, Combinations.chooseNElements(new int[] {1, 2, 3}, 2, false));
        assertArrayEquals(new int[][] {{1, 2}, {1, 3}, {2, 1}, {2, 3}, {3, 1}, {3, 2}}, Combinations.chooseNElements(new int[] {1, 2, 3}, 2, true));
        assertArrayEquals(new int[][] {{1, 2, 3}, {1, 3, 2}, {2, 1, 3}, {2, 3, 1}, {3, 1, 2}, {3, 2, 1}}, Combinations.chooseNElements(new int[] {1, 2, 3}, 3, true));
    }
    @Test
    void findPermutations() {
        assertArrayEquals(new int[][] {}, Combinations.findPermutations(new int[] {}));
        assertArrayEquals(new int[][] {{1}}, Combinations.findPermutations(new int[] {1}));
        assertArrayEquals(new int[][] {{1, 2}, {2, 1}}, Combinations.findPermutations(new int[] {1, 2}));
        assertArrayEquals(new int[][] {{2, 2}}, Combinations.findPermutations(new int[] {2, 2}));
        assertArrayEquals(new int[][] {{1, 2, 2}, {2, 1, 2}, {2, 2, 1}}, Combinations.findPermutations(new int[] {1, 2, 2}));
    }
    @Test
    void isPermutationOf() {
        assertTrue(Combinations.isPermutationOf(new int[] {1, 2, 3}, new int[] {3, 1, 2}));
        assertFalse(Combinations.isPermutationOf(new int[] {1}, new int[] {1, 1, 1}));
        assertFalse(Combinations.isPermutationOf(new int[] {}, new int[] {0}));

        for (int i = 0; i < 20; i++) {
            assertTrue(Combinations.isPermutationOf(i, i));
        }
        assertTrue(Combinations.isPermutationOf(9876, 6978));
        assertFalse(Combinations.isPermutationOf(1, 111));
        assertFalse(Combinations.isPermutationOf(123, 143));
    }
    @Test
    void combinationsOfGrowingNumbers() {
        assertArrayEquals(new int[][] {{1, 1}, {1, 2}, {1, 3}, {2, 2}, {2, 3}, {3, 3}}, Combinations.combinationsOfGrowingNumbers(1, 3, 2));
        assertArrayEquals(new int[][] {{1, 1, 1, 1, 1, 1, 1, 1, 1, 1}}, Combinations.combinationsOfGrowingNumbers(1, 1, 10));
        assertArrayEquals(new int[][] {{0, 0, 0}, {0, 0, 1}, {0, 1, 1}, {1, 1, 1}}, Combinations.combinationsOfGrowingNumbers(0, 1, 3));
    }
}