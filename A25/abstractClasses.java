// ABSTRACT CLASSES

package csd.uoc.gr.A25;


import java.util.Random;

abstract class Weapon {
    protected int power;
    WeaponCarrier holder;
    protected String color;

    /**
     * Δημιουργεί ένα νέο όπλο με τυχαία δύναμη, χωρίς κάτοχο
     */
    protected Weapon() {

        Random random = new Random();
        this.power = random.nextInt(5) + 1;
    }

    /**
     * @return Επιστρέφει τη δύναμη του όπλου
     */
    public int getPower() {
        return this.power;
    }

    /**
     * Αναθέτει τον κάτοχο του όπλου, κάνοντας τους κατάλληλους
     * ελέγχους
     *
     * @param holder Instance of Jedi or Sith
     */
    protected abstract void setHolder(Object holder);

    /**
     * @return επιστρέφει τον κάτοχο του όπλου
     */
    WeaponCarrier getHolder() {
        return this.holder;
    }

    /**
     * @return Επιστρέφει τις πληροφορίες για ένα όπλο
     */
    public abstract String toString();

}


abstract class WeaponCarrier{
    Weapon weapon;

    /**
     * @return Επιστρέφει το όπλο του WeaponCarrier
     */
    Weapon getWeapon(){
        return this.weapon;
    }

    /**
     * @return Επιστρέφει true αν ο WeaponCarrier έχει όπλο
     */
    Boolean hasWeapon(){
        return weapon != null;
    }

    /**
     * Αναθέτει στον WeaponCarrier ένα όπλο
     * @param weapon Weapon Instance
     */
    void setWeapon(Weapon weapon){
        if(weapon != null){
            this.weapon = weapon;
        }
        if(this instanceof Jedi){
            this.weapon.color = "BLUE";
        }else if(this instanceof Sith){
            this.weapon.color = "RED";
        }
    }
}

abstract class Humanoid extends csd.uoc.gr.A25.WeaponCarrier {
    protected String firstName;
    protected String lastName;
    private int health;

    /**
     * Δημιουργεί ένα στιγμιότυπο ενός Humanoid με ονοματεπώνυμο και
     * υγεία
     * @param firstName First Name
     * @param lastName Last Name
     * @param health Health
     */
    Humanoid (String firstName, String lastName, int health){
        this.firstName = firstName;
        this.lastName = lastName;
        if(health<=10 && health >=0) this.health = health;
        else System.out.println("Error : Illegal Health in Humanoid Constructor!!!");
    }

    Humanoid (String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
        this.health = 10;
    }
    /**
     * Θέτει την τιμή του health
     * @param health Health [0,10]
     */
    void setHealth(int health) {
        if (health <= 10 && health >= 0) this.health = health;
        else if(health  < 0) this.health = 0;
        else System.out.println("Error : Illegal Health in setHealth() !!!");
    }

    /**
     * @return Επιστρέφει την τιμή του health
     */
    int getHealth(){
        return this.health;
    }

    /**
     * @return Επιστρέφει false αν το ανθρωποειδές είναι ζωντανό, αλλιώς true
     */
    boolean isDefeated(){
        return this.health <= 0 ;
    }

    /**
     * @return Επιστρέφει το ονοματεπώνυμο του ανθρωποειδούς
     */
    String getCallSign(){
        return this.firstName +" , "+ this.lastName;
    }

    /**
     * @return Επιστρέφει όλες τις πληροφορίες για ένα ανθρωποειδές
     */

    @Override
    public abstract String toString();


}
abstract class Droid{
    protected final String name;
    protected Humanoid master;



    /**
     * Δημιουργεί ένα νέο ρομπότ, με όνομα αποτελούμενο από 4 τυχαίους
     * αλφαριθμητικούς χαρακτήρες [A-Z,0-9], χωρίς αφέντη
     */
    Droid(){
        Random random = new Random();
        char Letter_1 = (char)(random.nextInt(26) + 'A');
        char Letter_2 = (char)(random.nextInt(26) + 'A');

        int num1 = random.nextInt(9) + 1;
        int num2 = random.nextInt(9) + 1;

        this.name = String.valueOf(Letter_1) + Letter_2 + num1 + num2;

    }

    /**
     * Καλεί τον παραπάνω κονστράκτορα και επιπλέον αναθέτει έναν
     * αφέντη στο ρομπότ
     * @param master Droids Master
     */
    Droid(Humanoid master){
        this();
        this.master = master;
    }

    /**
     * Αναθέτει στο droid έναν αφέντη (ο οποίος δε μπορεί να είναι Droid)
     * @param master Droids Master
     */
    void setMaster(Object master){
        if(master instanceof Droid){
            System.out.println("Error : Cant set a Droid as Master");
        }
        if (master instanceof Humanoid) {
            this.master = (Humanoid) master;
        }
    }

    /**
     * @return Επιστρέφει τον αφέντη του ρομπότ
     */
    Humanoid getMaster(){
        return this.master;
    }

    /**
     * @return Επιστρέφει το όνομα του ρομπότ
     */
    String getName(){
        return this.name;
    }

    /**
     * @return Επιστρέφει true αν το ρομπότ δεν έχει αφέντη, ή αν ο αφέντης του
     * έχει ηττηθεί, διαφορετικά false
     */
    boolean isFree(){
        if(this.master == null) return true;
        else return this.master.isDefeated();
    }

    /**
     * @return Επιστρέφει το όνομα του ρομπότ και το όνομα του αφέντη του
     */
    public String toString(){
        return "Droid : "+this.getName()+" , Master : "+ this.getMaster().firstName;
    }
}

