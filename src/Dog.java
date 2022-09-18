public class Dog extends Pet{

    public double droolRate;
    private final static double defaultDroolRate = 5.0;
    //Constructor



    public Dog(String dogName, double dogHealth, int dogPainLevel, double dogDroolRate) {
        super(dogName, dogHealth, dogPainLevel);
        if (dogDroolRate <= 0) {
            droolRate = defaultDroolRate;
        } else {
            droolRate = dogDroolRate;
        }
    }
    public Dog(String dogName, double dogHealth, int dogPainLevel){
        super(dogName, dogHealth, dogPainLevel);
        this.droolRate = defaultDroolRate;
    }

    @Override
    public double getHealth() {
        return super.getHealth();
    }

    @Override
    public int getPainLevel() {
        return super.getPainLevel();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public double getDroolRate() {
        return droolRate;
    }

    // Heals dog and finds treat time, this.health +0.5 to round up when casting to int
    @Override
    public int treat() {
        int treatTime;
        if (droolRate < 3.5){
            treatTime = (int) Math.ceil((getPainLevel() *2) /getHealth());
        } else if (droolRate <= 7.5) {
            treatTime = (int) Math.ceil(getPainLevel()/getHealth());
        }
        else {
            treatTime = (int) Math.ceil(getPainLevel()/(getHealth()*2));
        }
        heal();
        return treatTime;
    }

    @Override
    public void speak(){
        super.speak();
        String dogMessage = "bark ";
        String output="";
        if(getPainLevel() > 5){
            dogMessage = (dogMessage.toUpperCase());
        }
        for(int i=0; i<=getPainLevel(); i++ ){
            output += dogMessage;
        }
        System.out.println(output.trim());

    }

    @Override
    public boolean equals(Object o) {
        return (super.equals(o) && (((Dog) o).droolRate ==droolRate));
    }
}
