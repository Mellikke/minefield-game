/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package proje;

import java.util.Random;
import java.util.Scanner;

/**
 * @file Proje.java
 * @description 15x15 boyutlarında bir labirentimiz var ve bu labirentte belirli karakterler var,oyunun amacı çıkışa ulaşmak
 * @assignment 1.proje
 * @date 29.12.2023
 * @author Melike Yıldız , melike.yildiz@stu.fsm.edu.tr
 *
 */
public class Proje {

    static char[][] labirent = {{'#', '!', '.', '.', 'R', '.', '.', '.', '.', '.', '.', '#', '.', '.', '.'},
    {'.', '.', '#', '.', '.', '.', '#', '.', 'H', '.', '.', '.', '.', '.', '!'},
    {'F', '.', '.', '.', '#', '.', '!', '.', '.', 'R', '.', '.', '#', '#', '.'},
    {'.', '.', '#', '.', '.', '#', '.', '.', '.', '.', 'F', '.', '.', '.', '.'},
    {'.', '!', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '.', '.', '.'},
    {'.', '.', 'H', '.', '.', '!', '.', '.', 'H', '.', '.', 'F', '.', '.', 'R'},
    {'#', '#', '#', '#', '.', '.', '#', '.', '.', '.', 'T', '.', '.', '.', 'E'},
    {'.', '.', '#', '.', 'F', '.', '#', '#', '.', '#', '#', '#', '#', '.', '.'},
    {'.', '#', '.', '.', '.', '.', '!', '.', '#', '.', '.', '.', '#', '.', '.'},
    {'.', 'T', 'T', '.', '#', '#', '.', '.', '.', '.', 'T', '.', '.', '.', 'R'},
    {'.', '.', '.', '#', '.', '.', '.', '#', '.', '#', '.', '#', '.', 'T', '.'},
    {'B', '.', '#', '.', '.', '!', '.', '!', '.', '.', '.', '.', '.', '.', '#'},
    {'.', '.', '.', 'F', '!', '.', '.', '.', 'H', '.', '.', 'R', '.', '.', '.'},
    {'.', '.', 'H', '.', '.', '.', '!', '.', '.', '.', '#', '.', '.', '#', '.'},
    {'.', '.', '.', '#', '.', '.', '#', '.', '#', '.', '#', '.', '.', '#', '#'}};

    static int currentIndex_X;
    static int currentIndex_Y;
    static int finishIndex_X;
    static int finishIndex_Y;
    static int counterT = 0;
    static int counterR = 0;
    static int counterH = 0;
    static int counterF = 0;
    static int counterStep = 0;
    static int counterMove = 0;

    public static void FindStartEndPoint(boolean isStartOfGame) {

        int startPoint_X = 0, finishPoint_Y = 0, startPoint_Y = 0, finishPoint_X = 0;

        for (int i = 0; i < labirent.length; i++) {
            if (labirent[i][0] == 'B') {
                startPoint_X = i;
                startPoint_Y = 0;
            }

        }
        int lastCol = labirent[0].length - 1;
        for (int j = 0; j < labirent[lastCol].length; j++) {
            if (labirent[j][lastCol] == 'E') {
                finishPoint_X = j;
                finishPoint_Y = lastCol;
            }
        }

        finishIndex_X = finishPoint_X;
        finishIndex_Y = finishPoint_Y;

        if (isStartOfGame == true) {
            currentIndex_X = startPoint_X;
            currentIndex_Y = startPoint_Y;
        }
    }

    public static void CollectBonus() {

        switch (labirent[currentIndex_X][currentIndex_Y]) {
            case 'T':

                counterT++;
                break;
            case 'R':

                counterR++;
                break;
            case 'H':

                counterH++;
                break;
            case 'F':

                counterF++;
                break;
            default:
                break;
        }
    }

    public static void PrintLabyrinth() {
        for (int i = 0; i < labirent.length; i++) {

            for (int j = 0; j < labirent[0].length; j++) {
                if (i == currentIndex_X && j == currentIndex_Y) {
                    System.out.printf("%c ", 'O');
                } else {
                    System.out.printf("%c ", labirent[i][j]);
                }

            }
            System.out.printf("\n");

        }
    }

    public static void BonusCheck(String bonus) {
        Scanner input = new Scanner(System.in);
        switch (bonus) {
            case "T": {
                if (counterT > 0) {
                    System.out.println("T karakteri seçildi.Gidilmek istenen x ve y noktalarını giriniz: ");
                    String inputLine = input.nextLine();
                    String[] coordinates = inputLine.split(",");

                    int[] targetPoint = new int[2];
                    if (coordinates.length >= 2) {
                        targetPoint[0] = Integer.parseInt(coordinates[0].trim());
                        targetPoint[1] = Integer.parseInt(coordinates[1].trim());
                        System.out.println("Gidilmek istenen noktalar: (" + targetPoint[0] + ", " + targetPoint[1] + ")");

                        if (labirent[targetPoint[0]][targetPoint[1]] != '#' && labirent[targetPoint[0]][targetPoint[1]] != '!') {
                            currentIndex_X = targetPoint[0];
                            currentIndex_Y = targetPoint[1];
                            counterT--;
                        } else if (labirent[targetPoint[0]][targetPoint[1]] == '#') {
                            System.out.println("Işınlanmak istenilen noktada duvar var. Tekrar x, y noktalarını giriniz: ");
                        } else if (labirent[targetPoint[0]][targetPoint[1]] == '!') {
                            System.out.println("Işınlanmak istenilen noktada mayın var. Tekrar x, y noktalarını giriniz: ");
                        }

                    } else {
                        System.out.println("Hatalı giriş. Lütfen x ve y koordinatlarını virgülle ayrılmış bir şekilde giriniz.");

                    }
                    counterMove++;
                } else {
                    System.out.println("T bonusu dizi içerisinde yok tekrar deneyiniz.");
                }
                break;
            }
            case "R": {
                if (counterR > 0) {
                    if (labirent[currentIndex_X][currentIndex_Y] == '#') {
                        labirent[currentIndex_X][currentIndex_Y] = '.';
                        counterMove++;
                    } else {
                        System.out.println("R bonusu kullanabilmek için duvar ile karşılaşmanız gerekmektedir.");
                    }
                } else {
                    System.out.println(" R bonusu dizi içerisinde yok tekrar deneyiniz.");
                }
                break;
            }
            case "H": {
                if (counterH > 0) {
                    counterMove = counterMove - 2;

                }
                if (counterMove < 0) {
                    System.out.println("hamle sayınız daha fazla azaltılamaz.");
                }
                break;
            }
            case "F": {
                if (counterF > 0) {
                    if (labirent[currentIndex_X][currentIndex_Y] == '!') {
                        labirent[currentIndex_X][currentIndex_Y] = '.';
                        counterMove++;
                    } else {
                        System.out.println("F bonusu kullanabilmek için mayın ile karşılaşmanız gerekmektedir");

                    }
                } else {
                    System.out.println("F bonusu dizi içerisinde yok tekrar deneyiniz.");
                }
                break;
            }
            default: {
                System.out.println("sahip olunmayan bonus girişi yapıldı lütfen tekrar deneyiniz.");
                MainGame();
            }

        }
    }

    public static void LabyrinthRefresh() {

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (labirent[i][j] == 'T' || labirent[i][j] == 'R' || labirent[i][j] == 'H' || labirent[i][j] == 'F') {
                    labirent[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            boolean successOp = true;
            while (successOp) {
                Random rnd_X = new Random();
                int x = rnd_X.nextInt(labirent.length);
                Random rnd_Y = new Random();
                int y = rnd_Y.nextInt(labirent[0].length);

                if (labirent[x][y] != '#' && labirent[x][y] != '!' && labirent[x][y] != 'B' && labirent[x][y] != 'E') {
                    labirent[x][y] = 'T';
                    successOp = false;
                }

            }
        }

        for (int i = 0; i < 5; i++) {
            boolean successOp = true;
            while (successOp) {
                Random rnd_X = new Random();
                int x = rnd_X.nextInt(labirent.length);
                Random rnd_Y = new Random();
                int y = rnd_Y.nextInt(labirent[0].length);

                if (labirent[x][y] != '#' && labirent[x][y] != '!' && labirent[x][y] != 'B' && labirent[x][y] != 'E' && labirent[x][y] != 'T') {
                    labirent[x][y] = 'R';
                    successOp = false;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            boolean successOp = true;
            while (successOp) {
                Random rnd_X = new Random();
                int x = rnd_X.nextInt(labirent.length);
                Random rnd_Y = new Random();
                int y = rnd_Y.nextInt(labirent[0].length);

                if (labirent[x][y] != '#' && labirent[x][y] != '!' && labirent[x][y] != 'B' && labirent[x][y] != 'E' && labirent[x][y] != 'T' && labirent[x][y] != 'R') {
                    labirent[x][y] = 'H';
                    successOp = false;
                }
            }
        }

        for (int i = 0; i < 5; i++) {
            boolean successOp = true;
            while (successOp) {
                Random rnd_X = new Random();
                int x = rnd_X.nextInt(labirent.length);
                Random rnd_Y = new Random();
                int y = rnd_Y.nextInt(labirent[0].length);

                if (labirent[x][y] != '#' && labirent[x][y] != '!' && labirent[x][y] != 'B' && labirent[x][y] != 'E' && labirent[x][y] != 'T' && labirent[x][y] != 'R' && labirent[x][y] != 'H') {
                    labirent[x][y] = 'F';
                    successOp = false;
                }
            }
        }

        for (int i = 0; i < labirent.length; i++) {
            for (int j = 0; j < labirent[0].length; j++) {
                if (labirent[i][j] == '!') {
                    labirent[i][j] = '.';
                }
            }
        }

        for (int i = 0; i < 10; i++) {
            boolean successOp = true;
            while (successOp) {
                Random rnd_X = new Random();
                int x = rnd_X.nextInt(labirent.length);
                Random rnd_Y = new Random();
                int y = rnd_Y.nextInt(labirent[0].length);

                if (labirent[x][y] != '#' && labirent[x][y] != 'T' && labirent[x][y] != 'R' && labirent[x][y] != 'H' && labirent[x][y] != 'F' && labirent[x][y] != 'B' && labirent[x][y] != 'E') {
                    labirent[x][y] = '!';
                    successOp = false;
                }
            }
        }
    }

    public static boolean checkGameStatus() {
        if (currentIndex_X == finishIndex_X && currentIndex_Y == finishIndex_Y) {
            System.out.println("Gidilen nokta ‘E’ karakterine eşit. Çıkışa geldiniz. Oyun bitti, tebrikler!");
            return true;
        } else {
            return false;
        }

    }

    public static boolean StepControl(int Step_X, int Step_Y) {
        boolean retVal = true;
        Scanner input = new Scanner(System.in);
        if (labirent[Step_X][Step_Y] == '#') {
            System.out.println("Duvar ile karşılaşıldı. R bonusu kullanmak için 'R' yazınız.");
            String checkWall = input.nextLine().toUpperCase();

            switch (checkWall) {
                case "R":
                    if (counterR > 0) {
                        counterR--;
                        labirent[Step_X][Step_Y] = '.';
                        counterMove++;
                        retVal = true;
                    } else {
                        System.out.println("Dizi içerisinde R bonusunuz bulunmamaktadır.Lütfen başka yöne hareket ediniz.");
                        retVal = false;

                    }
                    break;
                default:
                    System.out.println("Geçersiz değer girildi.");
                    retVal = false;
                    break;

            }

        } else if (labirent[Step_X][Step_Y] == '!') {
            System.out.println("Mayın ile karşılaşıldı. F bonusu kullanmak için 'F' yazınız.");
            String checkMine = input.nextLine().toUpperCase();

            switch (checkMine) {
                case "F":
                    if (counterF > 0) {
                        counterF--;
                        labirent[Step_X][Step_Y] = '.';
                        System.out.println("F bonusu kullanıldı");
                        counterMove++;

                        retVal = true;
                    } else {
                        System.out.println("F bonusunuz bulunmadığı için mayın patladı.");
                        counterMove = counterMove + 5;
                        labirent[Step_X][Step_Y] = '.';
                        retVal = false;

                    }
                    break;
                default:
                    System.out.println("Geçersiz değer girildi.");
                    retVal = false;
                    break;
            }

        }
        return retVal;
    }

    public static boolean MainGame() {
        Scanner input = new Scanner(System.in);
        int counter = 1;
        boolean retVal = true;

        while (counter <= 5) {
            PrintLabyrinth();

            String answer = input.next().toUpperCase();

            switch (answer) {
                case "W":
                    if (currentIndex_X != 0) {
                        if (StepControl((currentIndex_X - 1), currentIndex_Y)) {
                            currentIndex_X -= 1;
                            counterStep++;
                            counterMove++;
                        }
                    }
                    break;
                case "A":
                    if (currentIndex_Y != 0) {
                        if (StepControl(currentIndex_X, (currentIndex_Y - 1))) {
                            currentIndex_Y -= 1;
                            counterStep++;
                            counterMove++;
                        }
                    }
                    break;
                case "S":
                    if (currentIndex_X != 14) {
                        if (StepControl((currentIndex_X + 1), currentIndex_Y)) {
                            currentIndex_X += 1;
                            counterStep++;
                            counterMove++;
                        }
                    }
                    break;
                case "D":
                    if (currentIndex_Y != 14) {
                        if (StepControl(currentIndex_X, (currentIndex_Y + 1))) {
                            currentIndex_Y += 1;
                            counterStep++;
                            counterMove++;
                        }
                    }
                    break;
                case "EXİST":
                    System.out.println("Oyundan çıkış yapıldı.");
                    return false;

                case "+":
                    System.out.println("kullanmak istediğiniz bonusu yazınız: ");
                    String bonus = input.next().toUpperCase();
                    BonusCheck(bonus);
                    break;
                default:
                    System.out.println("seçenekler dışında bir kullanım gerçekleştirdiniz." + "\n"
                            + "Lütfen  W, A, S, D, + tuşlarını kullanarak veya exist yazarak işlemi gerçekleştiriniz ");
                    break;
            }

            CollectBonus();

            System.out.println("Adım sayısı: " + counterStep);
            System.out.printf("yeni konumuz : (%d,%d)", currentIndex_X, currentIndex_Y);
            System.out.println("\nW, A, S, D karakterlerinden birini giriniz ya da bonus kullanmak için '+' karakterine basınız. Çıkış yapmak için  'exist' yazınız.");

            counter++;
            if (checkGameStatus()) {
                return false;
            }
            if (counterStep % 5 == 0) {
                LabyrinthRefresh();
                FindStartEndPoint(false);
            }
        }

        return retVal;

    }

    public static void main(String[] args) {
        boolean gameStatus = true;
        System.out.println("oyun başlamıştır ");
        FindStartEndPoint(true);
        System.out.println("Adım sayısı:0" + "  Bulunduğunuz konum " + "(" + currentIndex_X + "," + currentIndex_Y + ")" + "\n" + "W, A, S, D karakterlerinden birini giriniz ya da bonus kullanmak için" + " + " + " karakterine basınız.  "
                + " Çıkış yapmak için " + " exist " + " yazınız.");

        while (gameStatus) {
            gameStatus = MainGame();
        }
    }

}
