package csd.uoc.gr.A25;

public class Sith extends Humanoid implements Combatant{

    /**
     * Δημιουργεί ένα νέο Sith, με μικρό όνομα «Darth», το δοθέν επώνυμο
     * και υγεία 10
     * @param lastName LastName
     */
    Sith(String lastName) {
        super("Darth", lastName);
        this.weapon = new Lightsaber();
    }

    /**
     * @return Επιστρέφει το ονοματεπώνυμο και το όπλο του Sith
     */
    @Override
    public String toString() {
        return this.firstName+" , "+this.lastName+" , Weapon : "+this.weapon.color+" "+this.getWeapon().getPower()+" , health : "+this.getHealth();
    }

    /**
     * Επιτίθεται σε έναν αντίπαλο μειώνοντας την κατάσταση του
     * αντιπάλου. Αν ο Sith διαθέτει όπλο, τότε η μείωση των πόντων του
     * αντιπάλου είναι όση η δύναμη του όπλου. Αν ο Sith δεν έχει όπλο,
     * τότε η μείωση των πόντων του αντιπάλου είναι 1.
     * @param adversary Jedi or Sith
     */
    @Override
    public void attack(Object adversary) {
        int damage_dealt = 0;
        if (this.hasWeapon()){
            damage_dealt = this.getWeapon().getPower();
        }else damage_dealt = 1;

        if(adversary instanceof Jedi){
            ((Jedi) adversary).setHealth(((Jedi) adversary).getHealth() - damage_dealt);
            System.out.println(this.getCallSign() + " attacked " +
                    ((Jedi) adversary).getCallSign() + " , damage dealt : " + damage_dealt);
        }else if(adversary instanceof BattleDroid){
            ((BattleDroid) adversary).setCondition(((BattleDroid) adversary).getCondition() - damage_dealt);
            System.out.println(this.getCallSign() + " attacked " +
                    ((BattleDroid) adversary).getCallSign() + " , damage dealt : " + damage_dealt);
        }

    }

    @Override
    public boolean isDefeated() {
        return this.getHealth()<=0;
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
}
