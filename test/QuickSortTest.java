import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class QuickSortTest
{
    Class[] constructorParameterSignature = { int[].class };
    Constructor[] constructors;

    @BeforeAll
    public void setup() {
        try {
            constructors = new Constructor[]{
                    QuickSortEndPivot.class.getConstructor(constructorParameterSignature),
                    QuickSortMedianPivot.class.getConstructor(constructorParameterSignature),
                    QuickSortRandomPivot.class.getConstructor(constructorParameterSignature),
                    QuickSortFisherPivot.class.getConstructor(constructorParameterSignature)
            };
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
    }
    public long timeTrial(QuickSort qs) {
        long start = System.nanoTime();
        qs.sort();
        long end = System.nanoTime();
        return end - start;
    }

    @Test
    public void sortingTest() throws NoSuchMethodException {
        Stream.of(
                Stream.of(
                        IntStream.of(11, 45, 1, 7, 3, 4, 5, 10),
                        IntStream.of(1, 3, 4, 5, 7, 10, 11, 45)
                ),
                Stream.of(
                        IntStream.of(90, 15, 61, 17, 153, 40, 15, 10),
                        IntStream.of(10, 15, 15, 17, 40, 61, 90, 153)
                ),
                Stream.of(
                        IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9),
                        IntStream.of(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
                )
        ).forEach(testStream -> {
            int[] originalTestArr;
            int[] testArr;
            int[] resultArr;

            List<IntStream> tests = testStream.toList();

            originalTestArr = tests.get(0).toArray();
            resultArr = tests.get(1).toArray();

            for (Constructor constructor : constructors)  {
                testArr = Arrays.copyOf(originalTestArr, originalTestArr.length);
                try {
                    ((QuickSort) constructor.newInstance(testArr)).sort();
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
                assertArrayEquals(testArr, resultArr);
            }
        });
    }

    @Test
    public void timeTrials()
    {
        int[] testSizes = {
                10000,
                100000,
                1000000,
                10000000,
                100000000
        };

        System.out.println("                     : Comparisons       Time Delta");
        for (int n : testSizes) {
            int[] inputArray = ArrayUtil.randomIntArray(n, n);
            int[] inputArrayCopy;

            System.out.printf("---------------------: %-9s ------------------%n", n);
            for (Constructor constructor : constructors)  {
                inputArrayCopy = Arrays.copyOf(inputArray, inputArray.length);
                try {
                    QuickSort qs = (QuickSort) constructor.newInstance(inputArrayCopy);

                    long runTimeEndPivot = timeTrial(qs);
                    System.out.printf("%-20s : %-16s  - %6sms%n", qs.getClass().getName(), qs.getComparisons(), runTimeEndPivot / 1000000);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
