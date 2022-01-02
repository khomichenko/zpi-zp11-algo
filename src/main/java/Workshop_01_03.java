import java.util.Arrays;

/**
 * В одномірному масиві з n елеменів поміняти місцями максимальний та мінімальний
 */
public class Workshop_01_03 {

    private Double[] inArray;
    private Double[] outArray;

    public Workshop_01_03(Double[] inArray) {
        this.inArray = inArray;
    }

    public Workshop_01_03 swapMinMax() {
        this.outArray = new Double[inArray.length];
        int minI = 0, maxI = 0;
        for (int i = 0; i < inArray.length ; i++) {
            Double x = inArray[i];
            minI = inArray[minI]<x ? i : minI;
            maxI = inArray[maxI]>x ? i : maxI;
            outArray[i] = x;
        }
        outArray[minI] = inArray[maxI];
        outArray[maxI] = inArray[minI];
        return this;
    }

    public void show() {
        System.out.println("******");
        System.out.println("Вхідний масив: " + Arrays.deepToString(inArray));
        System.out.println("Вихідний масив: " + Arrays.deepToString(outArray));
    }

    public static void main(String[] args) {
        new Workshop_01_03(Workshop.getRandomDouble(.0, 10.0, 3)).swapMinMax().show();
        new Workshop_01_03(Workshop.getRandomDouble(-100.0, 100.0, 5)).swapMinMax().show();
        new Workshop_01_03(Workshop.getRandomDouble(-7.0, 20.0, 2)).swapMinMax().show();
    }
}
