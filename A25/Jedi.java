package csd.uoc.gr.A25;

import java.util.Random;

public class Jedi extends Humanoid implements Combatant {

    private final String rank;
    private int force_level; // Gets values from [1,100]

    /**
     * Δημιουργεί ένα νέο Jedi, με το δοθέν ονοματεπώνυμο και υγεία 10,
     * αναθέτει μια τυχαία τιμή στο force_level και αναθέτει την τιμή του
     * rank, σύμφωνα με την εξής αντιστοίχιση με το force_level:
     * YOUNGLING: [1-10), PADAWAN: [10-30), KNIGHT: [30-70),
     * MASTER: [70-90), GRAND_MASTER: [90-100]
     *
     * @param firstName FirstName
     * @param lastName  LastName
     */
    Jedi(String firstName, String lastName) {
        super(firstName, lastName);

        Random random = new Random();

        this.force_level = random.nextInt(100) + 1;
        int key = this.force_level;
        if (key <= 10) {
            this.rank = "YOUNGLING";  // Set the rank accordingly
        } else if (key <= 30) {
            this.rank = "PADAWAN";
        } else if (key <= 70) {
            this.rank = "KNIGHT";
        } else if (key <= 90) {
            this.rank = "MASTER";
        } else {
            this.rank = "GRAND_MASTER";
        }
    }

    /**
     * @return Επιστρέφουν τις τιμές του force_level
     */
    public int getForceLevel() {
        return this.force_level;
    }

    /**
     * @return Επιστρέφει το rank του Jedi
     */
    public String getRank() {
        return this.rank;
    }


    /**
     * The rank damage depends from Jedis rank
     *
     * @return Returns Rank damage
     */
    private int getRankDamage() {
        int rank_damage = 0;
        switch (getRank()){
            case "YOUNGLING":
                rank_damage = 1;
                break;
            case "PADAWAN":
                rank_damage = 2;
                break;
            case "KNIGHT":
                rank_damage = 3;
                break;
            case "MASTER":
                rank_damage = 4;
                break;
            case "GRAND_MASTER":
                rank_damage = 5;
                break;
            default:
                System.out.println("Error : Jedi.java in switch statement");
                break;
        }
        return rank_damage;
    }


    /**
     * Επιτίθεται σε έναν αντίπαλο (ο οποίος δεν επιτρέπεται να είναι Jedi),
     * μειώνοντας την κατάσταση του αντιπάλου. Αν ο Jedi διαθέτει όπλο,
     * τότε η μείωση των πόντων του αντιπάλου είναι όση η δύναμη του
     * όπλου συν όση η ιεραρχία του Jedi (0 για YOUNGLING, 1 για
     * PADAWAN, 2 για KNIGHT, …). Αν ο Jedi δεν έχει όπλο, τότε η
     * μείωση των πόντων του αντιπάλου είναι όση η ιεραρχία του Jedi+1.
     * @param adversary Jedi or Sith
     */
    @Override
    public void attack(Object adversary) {
        int damage_dealt = 0;
        if(adversary instanceof Jedi){
                System.out.println("Cant attack ALLAY!");
                return;
        }

        if(this.hasWeapon()){
            damage_dealt = this.getWeapon().getPower() + this.getRankDamage();

        }else {
            damage_dealt = getRankDamage() + 1;
        }

        if(adversary instanceof Sith){
            ((Sith) adversary).setHealth(((Sith) adversary).getHealth() - damage_dealt);
            System.out.println( this.getCallSign() + " attacked " +
                    ((Sith) adversary).getCallSign() + " , damage dealt : " + damage_dealt);
        }else if(adversary instanceof BattleDroid){
            ((BattleDroid) adversary).setCondition(((BattleDroid) adversary).getCondition() - damage_dealt);
            System.out.println(this.getCallSign() + " attacked BattleDroid : " +
                    ((BattleDroid) adversary).getCallSign() + " , damage dealt : " + damage_dealt);
        }

    }


    @Override
    public boolean isDefeated() {
        return this.getHealth() <= 0;
    }

    @Override
    public int getCondition() {
        return this.getHealth();
    }

    @Override
    public void setCondition(int condition) {
        this.setHealth(condition);
    }



    @Override
    public String getCallSign() {
        return this.firstName;
    }

    @Override
    public String toString() {
        return this.firstName+" , "+this.lastName +" , Rank : "+this.getRank()+
                " , Weapon : "+ this.getWeapon().color+" " +this.getWeapon().getPower()+ " , Health : "+ this.getHealth();
    }
}


