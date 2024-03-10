package dataModel;

public class Spisok {

    public final Number[] Trurn;
    public  final int DigitsInPlase;

    public  final int CorrectDigits;


    public Spisok(Number[] trurn, int digitsInPlase, int correctDigits) {
        Trurn = trurn;
        DigitsInPlase = digitsInPlase;
        CorrectDigits = correctDigits;
    }
}
