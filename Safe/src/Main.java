import dataModel.TurnResult;

import java.util.ArrayList;
import java.util.List;

public class Main  implements Runnable{

    private static  int TARGET_GAMES_COUNT = 100;
    private static int _gamesCount =0;
    private  static List<Number> _gamesStatistics = new ArrayList<>();

    public static void main(String[] args) throws InterruptedException {
        ArrayList<Thread> threads = new ArrayList<>();
        for (int i = 0; i < TARGET_GAMES_COUNT; i++) {
            Thread thread = new Thread(new Main());
            threads.add(thread);
        }

        var startTime = System.currentTimeMillis();

        for (int count = 0; count < TARGET_GAMES_COUNT; count++) {
            threads.get(count).start();
        }
        for (int count = 0; count < TARGET_GAMES_COUNT; count++) {
            threads.get(count).join();
        }

        var endTime = System.currentTimeMillis();
        var elapsedTime = endTime - startTime;

        System.out.println("-----------------------------------------------");
        System.out.println("-------------------Statistic-------------------");
        System.out.println("-----------------------------------------------");
        System.out.println("Threads count = " + TARGET_GAMES_COUNT);
        System.out.println("Average turn win  = " +
                (_gamesStatistics
                        .stream()
                        .reduce(0,(acc,el) -> acc.intValue() + el.intValue()))
                        .intValue()
                    / _gamesCount
        );
        System.out.println("Time takes to get Statistics : " + elapsedTime/1000.0 + " second" );
        System.out.println("-----------------------------------------------");
    }

    @Override
    public void run() {
        _gamesCount++;
        Game game = new Game();
        String secret = game.getSecret();
        System.out.println("secret = " + secret);

        Bot bot = new Bot();
        bot.InitGameStart();

        TurnResult result = null;
        int popitka=0;

        while (result == null || result.DigitsInPlase < 4){
            popitka++;
            System.out.println("popitka - " + popitka);
            String turn = bot.getTurn();
            System.out.println(" hod bota: " + turn);
            result = game.Turn(turn);
            bot.setTurnResult(result);
            System.out.println("Resultati: ugadano = " + result.CorrectDigits +
                    " na meste = "+ result.DigitsInPlase);

        }
        System.out.println("Вы угодали за " + popitka + " попыток");
        _gamesStatistics.add(popitka);
    }
}