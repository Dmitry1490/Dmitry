public enum Competitor {
    HUMAN1(20),
    CAT(100),
    ROBOT(30),
    HUMAN2(10);

    int index;
    int maxSpeed;
    int maxJump;


    Competitor(int index){
        this.index = index;
        this.maxSpeed = (int)(Math.random() * index);
        this.maxJump = (int)(Math.random() * index);
    }

    public int getMaxSpeed(){
        return maxSpeed;
    }

    public int getMaxJump(){
        return maxJump;
    }

}


