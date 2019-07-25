public class Main_JavaLvl2_lsn1 {

    public static void main(String[] args) {

        Competitor[] competitors = new Competitor[4]; // Формирую список участников;
        competitors[0] = Competitor.HUMAN1;
        competitors[1] = Competitor.CAT;
        competitors[2] = Competitor.ROBOT;
        competitors[3] = Competitor.HUMAN2;

        Obstacles[] obstacles = new Obstacles[3]; // Формирую трассу с препятствиями;
        obstacles[0] = Obstacles.WALL1;
        obstacles[1] = Obstacles.RUNNINGTRACK;
        obstacles[2] = Obstacles.WALL2;

        printResult(result(competitors, obstacles)); // Результат: выводится список участников, добравшихся до финиша;

    }

    private static Competitor[] result(Competitor[] competitors, Obstacles[] obstacles) {

        int q = 0;
        Competitor[] result = new Competitor[4];

        for (Competitor j : competitors) {
            int k = 0;
            for (Obstacles i : obstacles) {
                switch (i.getIndex()) {
                    case 0: {
                        if (i.getObstacles(i.getIndex()) <= j.getMaxJump()) {
                            k++;}
                        if (k == obstacles.length){
                            result[q] = j;
                            q++;
                        } break;
                    }
                    case 1: {
                        if (i.getObstacles(i.getIndex()) <= j.getMaxSpeed()) {
                            k++;}
                        if (k == obstacles.length){
                            result[q] = j;
                            q++;
                        } break;
                    }
                    default:
                        throw new RuntimeException("Unknown index:" + i.getIndex());
                }

            }
        }
         return result;
    }

    private static void printResult(Competitor[] result){
        for (Competitor i: result) {
            System.out.println(i);

        }
    }
}
