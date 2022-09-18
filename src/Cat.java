public class Cat extends Pet{

    private int miceCaught;
    private final static int defaultMiceCaught = 0;

    //Constructors, default miceCaught 0
    public Cat(String catName, double catHealth, int catPainLevel, int catMiceCaught) {
        super(catName, catHealth, catPainLevel);
        if (catMiceCaught < 0) {
            miceCaught = defaultMiceCaught;
        } else {
            miceCaught = catMiceCaught;
        }
    }

    public Cat(String catName, double catHealth, int catPainLevel){
        super(catName,catHealth,catPainLevel);
        this.miceCaught = defaultMiceCaught;
    }

    //Treat is repeated code with dog.treat() try and consolidate

    //Methods
    @Override
    public int treat() {
        int treatTime;
        if (miceCaught < 3.5){
            treatTime = (int) Math.ceil((getPainLevel() *2) /getHealth());
        } else if (miceCaught <= 7.5) {
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
        String catMessage = "meow ";
        String output="";
        if(getPainLevel() > 5){
            catMessage = (catMessage.toUpperCase());
        }
        for(int i=0; i<=miceCaught; i++ ){
            output += catMessage;
        }
        System.out.println(output.trim());

    }

    @Override
    public boolean equals(Object o) {
        return (super.equals(o) && (((Cat) o).miceCaught ==miceCaught));
    }

    //Getters
    @Override
    public int getPainLevel() {
        return super.getPainLevel();
    }

    @Override
    public double getHealth() {
        return super.getHealth();
    }

    @Override
    public String getName() {
        return super.getName();
    }

    public int getMiceCaught() {
        return miceCaught;
    }
}


