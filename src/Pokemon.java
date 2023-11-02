
public class Pokemon implements Cloneable {

    private int id;
    private String name;
    private String type1;
    private String type2;
    private int total;
    private double hp;
    private int baseAttack;
    private int defense;
    private int spAtk;
    private int spDef;
    private int speed;

    private Attack firstAttack;
    private Attack secondAttack;


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType1() {
        return type1;
    }

    public void setType1(String type1) {
        this.type1 = type1;
    }

    public String getType2() {
        return type2;
    }

    public void setType2(String type2) {
        this.type2 = type2;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getBaseAttack() {
        return baseAttack;
    }

    public void setBaseAttack(int baseAttack) {
        this.baseAttack = baseAttack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpAtk() {
        return spAtk;
    }

    public void setSpAtk(int spAtk) {
        this.spAtk = spAtk;
    }

    public int getSpDef() {
        return spDef;
    }

    public void setSpDef(int spDef) {
        this.spDef = spDef;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Attack getFirstAttack() {
        return firstAttack;
    }

    public void setFirstAttack(Attack firstAttack) {
        this.firstAttack = firstAttack;
    }

    public Attack getSecondAttack() {
        return secondAttack;
    }

    public void setSecondAttack(Attack secondAttack) {
        this.secondAttack = secondAttack;
    }

    public Pokemon(int id, String name, String type1, String type2, int total, int hp, int baseAttack, int defense, int spAtk, int spDef, int speed) {
        this.id = id;
        this.name = name;
        this.type1 = type1;
        this.type2 = type2;
        this.total = total;
        this.hp = hp;
        this.baseAttack = baseAttack;
        this.defense = defense;
        this.spAtk = spAtk;
        this.spDef = spDef;
        this.speed = speed;
    }

    @Override
    public String toString() {
        return String.format("%-20s%d\n%-20s%s\n%-20s%s\n%-20s%s\n%-20s%d\n%-20s%.2f\n%-20s%d\n%-20s%d\n%-20s%d\n%-20s%d\n%-20s%d\n", "ID:", id, "Name:", name, "Typ1", type1, "Typ2", type2, "Total-Bewertung:", total, "HP:", hp, "Basis-Atk:", baseAttack, "Verteidigung:", defense, "spAtk:", spAtk, "spDef:", spDef, "speed:", speed);
    }


    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();

        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }

    }

}
