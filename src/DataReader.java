import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;

public class DataReader {

    private BufferedReader reader;
    private HashMap<Integer, Pokemon> pokemonMap = new HashMap<>();
    private HashMap<Integer, Attack> attackMap = new HashMap<>();

    public HashMap<Integer, Pokemon> getPokemonMap() {
        return pokemonMap;
    }

    public void setPokemonMap(HashMap<Integer, Pokemon> pokemonMap) {
        this.pokemonMap = pokemonMap;
    }

    public HashMap<Integer, Attack> getAttackMap() {
        return attackMap;
    }

    public void setAttackMap(HashMap<Integer, Attack> attackMap) {
        this.attackMap = attackMap;
    }

    //    private final String POKEMON_CSV_FILE_PATH = "./data./Pokemon.csv";
    private final String POKEMON_CSV_FILE_PATH = "C:\\Users\\Codersbay\\IdeaProjects\\Ausbildung\\FileIOAndExceptions\\data\\Pokemon.csv";
    //    private final String ATTACK_CSV_FILE_PATH = "./data./Attack.csv";
    private final String ATTACK_CSV_FILE_PATH = "C:\\Users\\Codersbay\\IdeaProjects\\Ausbildung\\FileIOAndExceptions\\data\\Attacks.csv";

    public DataReader() {
        try {
            reader = new BufferedReader(new FileReader(POKEMON_CSV_FILE_PATH));
            reader.readLine();
            String currentLine;

//            currentLine = reader.readLine();
//            while(currentLine!=null){
//                //------
//                currentLine= reader.readLine();
//            }

            while ((currentLine = reader.readLine()) != null) {
                String[] components = currentLine.split(";");
                Pokemon pokemon;
                int id = Integer.parseInt(components[0]);
                String name = components[1];
                String type1 = components[2];
                String type2 = components[3];
                int total = Integer.parseInt(components[4]);
                int hp = Integer.parseInt(components[5]);
                int baseAttack = Integer.parseInt(components[6]);
                int defense = Integer.parseInt(components[7]);
                int spAtk = Integer.parseInt(components[8]);
                int spDef = Integer.parseInt(components[9]);
                int speed = Integer.parseInt(components[10]);

                pokemon = new Pokemon(id, name, type1, type2, total, hp, baseAttack, defense, spAtk, spDef, speed);
                pokemonMap.put(pokemon.getId(), pokemon);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        {
            try {
                reader = new BufferedReader(new FileReader(ATTACK_CSV_FILE_PATH));
                reader.readLine();
                String currentLine;
                while ((currentLine = reader.readLine()) != null) {
                    Attack attack;
                    String[] components = currentLine.split(";");
                    int id = Integer.parseInt(components[0]);
                    String name = components[1];
                    String effect = components[2];
                    String type = components[3];
                    String kind = components[4];
                    int power = Integer.parseInt(components[5]);
                    int accuracy = Integer.parseInt(components[6].replaceAll("%", ""));
                    int pp = Integer.parseInt(components[7]);

                    attack = new Attack(id, name, effect, type, kind, power, accuracy, pp);
                    attackMap.put(attack.getId(), attack);
                }

            } catch (IOException e) {
                throw new RuntimeException(e);
            }


        }

    }


}
