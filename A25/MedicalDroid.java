package csd.uoc.gr.A25;
import java.util.Random;
public class MedicalDroid extends Droid{
    int medicalSkills;

    /**
     * Δημιουργεί ένα νέο MedicalDroid με τυχαίο medicalSkills
     */
    MedicalDroid(){
        super();
        Random random = new Random();
        this.medicalSkills = random.nextInt(3) + 1;
    }

    /**
     * Δημιουργεί ένα νέο MedicalDroid με τυχαίο medicalSkills και αναθέτει
     * σε αυτό έναν αφέντη
     * @param master Droids Master
     */
    MedicalDroid(Humanoid master){
        this();
        this.master = master;
    }

    /**
     * @return Επιστρέφει την τιμή του medicalSkills
     */
    int getMedicalSkills(){
        return this.medicalSkills;
    }

    /**
     * @return Επιστρέφει το όνομα του ρομπότ
     */
    String getName(){
        return super.getName();
    }

    /**
     * Βελτιώνει την κατάσταση του αφέντη κατά medicalSkills μονάδες.
     * Προσοχή στους περιορισμούς!
     */
    void healMaster(){
        if(this.master.isDefeated()) return;
        else {
            int newHealth = this.master.getHealth() + this.medicalSkills;
            this.master.setHealth(Math.min(newHealth, 10));
        }
    }


}
