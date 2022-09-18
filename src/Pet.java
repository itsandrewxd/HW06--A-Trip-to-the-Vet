public abstract class Pet {

    private String name;
    //Percent between 0.0-1.0
    private double health;
    //Ranges [1-10];
    private int painLevel;

    //Default variables
    private static final double defaultMaxHealth = 1.0;
    private static final double defaultMinHealth = 0.0;
    private static final int defaultMaxPainLevel = 10;
    private static final int defaultMinPainLevel = 1;

    // Constructor

    public Pet(String petName, double petHealth, int petPainLevel){
        name = petName;
        if (petHealth > defaultMaxHealth){
            health = defaultMaxHealth;
        }

        else if (petHealth < defaultMinHealth){
            health = defaultMinHealth;
        }
        else{
            health = petHealth;
        }

        if (petPainLevel > defaultMaxPainLevel){
            painLevel = defaultMaxPainLevel;
        }
        else if (petPainLevel < defaultMinPainLevel) {
            painLevel = defaultMinPainLevel;
        }
        else {
        painLevel = petPainLevel;
        }
    }

    //Getters Setters

    public double getHealth(){
        return health;
    }
    public int getPainLevel() {
        return painLevel;
    }

    public String getName() {
        return name;
    }

    //Methods
    public abstract int treat();

    public void speak(){
        String petMessage = "Hello! My name is ";
        if (this.painLevel > 5){
            petMessage = petMessage.toUpperCase();
        }
        System.out.println(petMessage);

    }


    public boolean equals(Object o){
        if (o instanceof Pet){
            Pet pet1 = (Pet) o;
            if( this.getName().equals(pet1.getName())){
                return true;
            }
        }
        return false;
    }

    protected void heal(){
        health = defaultMaxHealth;
        painLevel = defaultMinPainLevel;
    }

}

