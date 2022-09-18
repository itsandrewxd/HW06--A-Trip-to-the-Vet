public class InvalidPetException extends RuntimeException{
    public InvalidPetException(){
        this("Your Pet is invalid!");
    }

    public InvalidPetException(String s){
        super(s);
    }
}
