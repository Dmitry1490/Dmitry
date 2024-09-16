public enum Obstacles {

    WALL1(0),
    RUNNINGTRACK(1),
    WALL2(0);

    private int index;
    private int height;
    private int length;


    Obstacles(int index) {
        this.index = index;
        this.height = (int) (Math.random() * 5);
        this.length = (int) (Math.random() * 10);
    }

    public int getObstacles(int index) {
        switch (index) {
            case 0:
                return height;
            case 1:
                return length;
            default:
                throw new RuntimeException("Unknown index:" + index);
        }
    }

    public int getIndex() {
        return index;
    }
}
