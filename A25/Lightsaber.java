package csd.uoc.gr.A25;




public class Lightsaber extends Weapon{

    /**
     * Δημιουργεί ένα νέο φωτόσπαθο με τυχαία δύναμη, χωρίς κάτοχο και
     * χωρίς χρώμα
     */
    Lightsaber(){
        super();
    }

    /**
     * Αναθέτει τον κάτοχο του όπλου, κάνοντας τους κατάλληλους
     * ελέγχους
     * @param Holder Instance of Jedi or Sith
     */
    public void setHolder(Object Holder){
        if(Holder == null){
            System.out.println("Error : LightSaber.java . Holder == null");
            return;
        }
        if(Holder instanceof Jedi){
            this.color = "BLUE";
        }else if(Holder instanceof Sith){
            this.color = "RED";
        }
    }

    String getColor(){
        return this.color;
    }

    /**
     * @return Επιστρέφει τo χρώμα και τη δύναμη του φωτόσπαθου
     */
    @Override
    public String toString() {
        return "Color : "+this.getColor()+", Power : "+this.getPower();
    }
}
