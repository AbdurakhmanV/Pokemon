import java.util.*;

public class Battle {

    public Battle() {
    }

    private final Scanner scan = new Scanner(System.in);
    private Random random = new Random();
    private Pokemon pokemon;
    private Pokemon pokemonPC;
    private final List<Integer> possibleAttackIDList = new ArrayList<>();
    private final DataReader dataReader = new DataReader();


    public void startBattle() {

        showAllPokemon();
        pokemon = choosePokemon();
        int controller = 1;
        showAllPossibleAttacks(pokemon, possibleAttackIDList, controller);
        chooseAttack(pokemon, possibleAttackIDList);

        pokemonPC = choosePokemonPC(pokemonPC);
        possibleAttackIDList.clear();
        controller = 0;
        showAllPossibleAttacks(pokemonPC, possibleAttackIDList, controller);
        pokemonPC = addTwoRandomAttacksForPokemonPC(pokemonPC, possibleAttackIDList);

        if (pokemon.getSpeed() > pokemonPC.getSpeed()) {
            while (pokemon.getHp() > 0 && pokemonPC.getHp() > 0) {
                playerAttack(pokemon, pokemonPC);
                if (pokemonPC.getHp() > 0) {
                    pcAttack(pokemonPC, pokemon);
                }
            }
        } else {
            while (pokemon.getHp() > 0 && pokemonPC.getHp() > 0) {
                pcAttack(pokemonPC, pokemon);
                if (pokemon.getHp() > 0) {
                    playerAttack(pokemon, pokemonPC);
                }
            }
        }
    }

    public void showAllPokemon() {
        System.out.println("Liste aller Pokemons");
        for (int i = 1; i <= dataReader.getPokemonMap().size(); i++) {
            System.out.println(dataReader.getPokemonMap().get(i));
            System.out.println("---------------------------------------------------------");
        }
    }

    public Pokemon choosePokemon() {
        System.out.println("Waehle ein Pokemon aus, indem du die ID oder den Namen des Pokemons eingibst.");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();

        if (input.matches(".*\\d.*")) {
            int index = Integer.parseInt(input);
            if (index <= 0 || index > dataReader.getPokemonMap().size()) {
                System.out.println("Dieser Index existiert nicht.");
                choosePokemon();
            }
            for (int i = 1; i <= dataReader.getPokemonMap().size(); i++) {
                if (dataReader.getPokemonMap().get(i).getId() == index) {
                    System.out.println("Du hast " + dataReader.getPokemonMap().get(i).getName() + " ausgewaehlt.");

                    Pokemon pokemon = dataReader.getPokemonMap().get(i);
                    Pokemon pokemonCopy = pokemon.clone();
                    return pokemonCopy;
                }
            }
        } else {
            for (int i = 1; i <= dataReader.getPokemonMap().size(); i++) {
                if (input.equals(dataReader.getPokemonMap().get(i).getName())) {
                    System.out.println("Du hast " + dataReader.getPokemonMap().get(i).getName() + " ausgewaehlt.");
                    Pokemon pokemon = dataReader.getPokemonMap().get(i);
                    Pokemon pokemonCopy = pokemon.clone();
                    return pokemonCopy;
                } else if (i == dataReader.getPokemonMap().size() - 1 && dataReader.getPokemonMap().get(i).getName().equals(input)) {
                    System.out.println("Dieser Name fuer ein Pokemon existiert im Programm nicht.");
                    choosePokemon();
                }
            }

        }
        return null;
    }

    public void showAllPossibleAttacks(Pokemon pokemon, List<Integer> possibleAttackIDList, int controller) {
        if (controller == 1) {
            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
            System.out.println(pokemon + "\n");
            System.out.println(pokemon.getName() + " kann folgende Attacken nutzen.\n");
        }
        for (int i = 1; i < dataReader.getAttackMap().size(); i++) {
            if (dataReader.getAttackMap().get(i).getType().equals(pokemon.getType1())) {
                if (controller == 1) {
                    System.out.println(dataReader.getAttackMap().get(i) + "\n");
                    System.out.println("---------------------------------------------------------");
                }
                possibleAttackIDList.add(dataReader.getAttackMap().get(i).getId());
            }
        }
        if (pokemon.getType2() != null) {
            for (int i = 1; i < dataReader.getAttackMap().size(); i++) {
                if (dataReader.getAttackMap().get(i).getType().equals(pokemon.getType2())) {
                    if (controller == 1) {
                        System.out.println(dataReader.getAttackMap().get(i) + "\n");
                        System.out.println("---------------------------------------------------------");
                    }
                    possibleAttackIDList.add(dataReader.getAttackMap().get(i).getId());
                }
            }
        }
    }

    public void chooseAttack(Pokemon pokemon, List<Integer> possibleAttackIDList) {
        System.out.println("\nWaehle zwei Attacken aus dieser Liste aus, indem du deren ID reinschreibst.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");

        int id01;
        int id02;
        do {
            Collections.sort(possibleAttackIDList);
            System.out.println(possibleAttackIDList);
            id01 = controlNumber();
            id02 = controlNumber();
            System.out.println(id01);
            if (!(possibleAttackIDList.contains(id01)) || !(possibleAttackIDList.contains(id02))) {
                System.out.println("Waehle nur eine ID aus der vorherigen Liste der Attacken aus.");
            }
        } while (!(possibleAttackIDList.contains(id01)) || !(possibleAttackIDList.contains(id02)));
        pokemon.setFirstAttack(dataReader.getAttackMap().get(id01));
        pokemon.setSecondAttack(dataReader.getAttackMap().get(id02));
        System.out.println("Du hast " + pokemon.getFirstAttack().getName() + " und " + pokemon.getSecondAttack().getName() + " ausgewaehlt.\n");

    }



    public Pokemon choosePokemonPC(Pokemon pokemonPC) {
        pokemonPC = dataReader.getPokemonMap().get(random.nextInt(dataReader.getPokemonMap().size()) + 1).clone();
        System.out.println("Gegner");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        System.out.println(pokemonPC);
        return pokemonPC;
    }

    public Pokemon addTwoRandomAttacksForPokemonPC(Pokemon pokemon, List<Integer> possibleAttackIDList) {
        random = new Random();
        pokemon.setFirstAttack(dataReader.getAttackMap().get(possibleAttackIDList.get(random.nextInt(possibleAttackIDList.size()))));
        do {
            pokemon.setSecondAttack(dataReader.getAttackMap().get(possibleAttackIDList.get(random.nextInt(possibleAttackIDList.size()))));
        } while (pokemon.getFirstAttack() == pokemon.getSecondAttack());
        System.out.println("Die Attacken " + pokemon.getFirstAttack().getName() + " und " + pokemon.getSecondAttack().getName() + " wurden von dem Gegner ausgewaehlt.\n");
        System.out.println(pokemon.getFirstAttack() + "\n");
        System.out.println(pokemon.getSecondAttack());
        return pokemon;
    }

    public void playerAttack(Pokemon pokemon, Pokemon pokemonEnemy) {
        System.out.println("Waehle eine Attacke aus.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int control = 1;
        int chooseProgram;
        do {
            System.out.println("1. " + pokemon.getFirstAttack().getName() + "\n2. " + pokemon.getSecondAttack().getName());
            chooseProgram = scan.nextInt();
        } while (chooseProgram < 1 || chooseProgram > 2);
        double damage;
        if (chooseProgram == 1) {
            damage = damageCalculation(pokemon.getFirstAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
        } else {
            damage = damageCalculation(pokemon.getSecondAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
        }
        healthEffect(damage, pokemonEnemy, control);
    }

    public void pcAttack(Pokemon pokemon, Pokemon pokemonEnemy) {
        System.out.println("Dein Gegner greift an.");
        System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------");
        int control = 0;
        int chooseProgram = random.nextInt(2) + 1;
        if (chooseProgram == 1) {
            System.out.println(pokemon.getName() + " nutzt " + pokemon.getFirstAttack().getName() + ".");
            //TODO: damage method?
            double damage = damageCalculation(pokemon.getFirstAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
            healthEffect(damage, pokemonEnemy, control);

        } else {
            System.out.println(pokemon.getName() + " nutzt " + pokemon.getSecondAttack().getName() + ".");
            double damage = damageCalculation(pokemon.getSecondAttack().getPower(), pokemon.getBaseAttack(), pokemonEnemy.getDefense());
            healthEffect(damage, pokemonEnemy, control);
        }
    }

    public double damageCalculation(int power, int baseAttack, int enemyDefense){
        double damage = power * ((double)baseAttack/ (double) enemyDefense * (1.0 / 6.0));
        System.out.printf("Schaden: %.2f\n\n", damage);
        return damage;
    }


    public void healthEffect(double damage, Pokemon pokemon, int control) {
        pokemon.setHp(pokemon.getHp() - damage);
        if (pokemon.getHp() <= 0) {
            String text = control > 0 ? pokemon.getName() + " ist gestorben\n." : " Du bist gestorben.\n";
            System.out.println(text);
        } else {
            String text = control > 0 ? String.format("%s hat noch %.2f Lebenspunkte\n", pokemon.getName(), pokemon.getHp()) : String.format("Du hast noch %.2f Lebenspunkte.\n", pokemon.getHp());
            System.out.print(text);
        }
    }

    public int controlNumber() {
        Scanner scan = new Scanner(System.in);
        int number;
        while (true) {
            if (!scan.hasNextInt()) {
                System.out.println("Du kannst nur eine Zahl reinschreiben.");
                scan.nextInt();
            } else {
                number = scan.nextInt();
                if (number < 0) {
                    System.out.println("Du darfst nur eine positive Zahl reinschreiben");
                } else {
                    return number;
                }
            }
        }
    }


}
