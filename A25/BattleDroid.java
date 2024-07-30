package csd.uoc.gr.A25;

public class BattleDroid extends Droid implements Combatant{

    private int condition;

    /**
     * Δημιουργεί ένα νέο BattleDroid με condition 10
     */
    BattleDroid(){
        this.condition = 10;
    }

    /**
     * Δημιουργεί ένα νέο BattleDroid με condition 10, στο οποίο αναθέτει
     * έναν αφέντη
     * @param master Battledroids Master
     */
    BattleDroid(Humanoid master){
        this();
        this.master = master;
    }

    /**
     * Επιτίθεται σε έναν αντίπαλο μειώνοντας την κατάσταση του
     * αντιπάλου κατά 1
     * @param adversary Jedi or Sith
     */
    @Override
    public void attack(Object adversary) {
        int damage_dealt = 1;
        if(adversary instanceof Jedi){
            ((Jedi) adversary).setCondition(((Jedi) adversary).getCondition() - damage_dealt);
            System.out.println("BattleDroid : "+this.getCallSign()+" attacked "+
                    ((Jedi) adversary).getCallSign()+" , damage dealt : " + damage_dealt);
        } else if (adversary instanceof Sith) {
            ((Sith) adversary).setCondition(((Sith) adversary).getCondition() - damage_dealt);
            System.out.println("BattleDroid : "+this.getCallSign()+" attacked "+
                    ((Sith) adversary).getCallSign()+" , damage dealt : " + damage_dealt);
        }
    }

    /**
     * @return Επιστρέφει true αν η τιμή του condition είναι 0, διαφορετικά false
     */
    @Override
    public boolean isDefeated() {
        return this.condition <= 0;
    }

    /**
     * @return Επιστρέφει την τιμή του condition
     */
    @Override
    public int getCondition() {
        return this.condition;
    }

    /**
     * Αναθέτει την τιμή του condition
     * @param condition Combatant condition
     */
    @Override
    public void setCondition(int condition) {
        if (condition <=10 && condition >=0) this.condition = condition;
        else if (condition < 0 ) this.condition = condition;
        else System.out.println("Invalid setCondition to BattleDroid");
    }

    /**
     * @return Επιστρέφει το όνομα του BattleDroid
     */
    @Override
    public String getCallSign() {
        return this.name;
    }

    /**
     * Επιτίθεται στον adversary. Προϋποθέτει ότι:
     * - ο αφέντης δεν έχει ηττηθεί
     * -o adversary μπορεί να επιτεθεί στον αφέντη
     * @param adversary Adversary
     */
    void protectMasterFrom(Object adversary){
        if(!this.master.isDefeated())
            if(adversary instanceof Jedi && !((Jedi) adversary).isDefeated()){
                attack(adversary);
            }else if(adversary instanceof Sith && !((Sith) adversary).isDefeated()){
                attack(adversary);
            }
    }
}
