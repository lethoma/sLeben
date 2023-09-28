import java.util.AbstractMap.SimpleImmutableEntry;
import java.util.*;

enum PlayerName {
  BERNI, DAMZA, EMIL, ERWIN, JAN, JORDAN, LOHN, NALI, PATRICK, PEPI, SABRINA, SNUSI, THORSTEN, WILLI
}

public class Player {
  private static final Map<PlayerName, Map<String, Integer>> INIT_VALUES = Map.ofEntries(
          new SimpleImmutableEntry<>(PlayerName.BERNI, Map.of(
                  "health", 3,
                  "knowledge", 4,
                  "social", 3,
                  "alcohol", 5,
                  "finances", 2
          )),
          new SimpleImmutableEntry<>(PlayerName.DAMZA, Map.of(
                  "health", 4,
                  "knowledge", 2,
                  "social", 2,
                  "alcohol", 2,
                  "finances", 5
          )),
          new SimpleImmutableEntry<>(PlayerName.EMIL, Map.of(
                  "health", 3,
                  "knowledge", 4,
                  "social", 2,
                  "alcohol", 3,
                  "finances", 3
          )),
          new SimpleImmutableEntry<>(PlayerName.ERWIN, Map.of(
                  "health", 2,
                  "knowledge", 4,
                  "social", 5,
                  "alcohol", 4,
                  "finances", 2
          )),
          new SimpleImmutableEntry<>(PlayerName.JAN, Map.of(
                  "health", 2,
                  "knowledge", 3,
                  "social", 5,
                  "alcohol", 2,
                  "finances", 3
          )),
          new SimpleImmutableEntry<>(PlayerName.JORDAN, Map.of(
                  "health", 3,
                  "knowledge", 2,
                  "social", 4,
                  "alcohol", 5,
                  "finances", 3
          )),
          new SimpleImmutableEntry<>(PlayerName.LOHN, Map.of(
                  "health", 3,
                  "knowledge", 5,
                  "social", 3,
                  "alcohol", 4,
                  "finances", 3
          )),
          new SimpleImmutableEntry<>(PlayerName.NALI, Map.of(
                  "health", 4,
                  "knowledge", 5,
                  "social", 3,
                  "alcohol", 2,
                  "finances", 2
          )),
          new SimpleImmutableEntry<>(PlayerName.PATRICK, Map.of(
                  "health", 3,
                  "knowledge", 2,
                  "social", 4,
                  "alcohol", 4,
                  "finances", 2
          )),
          new SimpleImmutableEntry<>(PlayerName.PEPI, Map.of(
                  "health", 5,
                  "knowledge", 3,
                  "social", 3,
                  "alcohol", 3,
                  "finances", 4
          )),
          new SimpleImmutableEntry<>(PlayerName.SABRINA, Map.of(
                  "health", 3,
                  "knowledge", 3,
                  "social", 3,
                  "alcohol", 5,
                  "finances", 4
          )),
          new SimpleImmutableEntry<>(PlayerName.SNUSI, Map.of(
                  "health", 4,
                  "knowledge", 3,
                  "social", 3,
                  "alcohol", 3,
                  "finances", 5
          )),
          new SimpleImmutableEntry<>(PlayerName.THORSTEN, Map.of(
                  "health", 3,
                  "knowledge", 5,
                  "social", 3,
                  "alcohol", 2,
                  "finances", 2
          )),
          new SimpleImmutableEntry<>(PlayerName.WILLI, Map.of(
                  "health", 3,
                  "knowledge", 2,
                  "social", 3,
                  "alcohol", 4,
                  "finances", 4
          )
          ));
  private final PlayerName name;
  private int field;
  private int money;
  private int health;
  private int knowledge;
  private int social;
  private int alcohol;
  private int finances;
  private final List<Card> cards = new ArrayList<>();


  public Player(PlayerName name) {
    this.name = name;
    this.field = 0;
    this.money = 10;
    //switch for the different player names, that gives each player different starting values
    Map<String, Integer> initialValues = INIT_VALUES.get(name);
    if (initialValues == null) {
      throw new IllegalArgumentException("Invalid player name: " + name);
    }
    this.health = initialValues.get("health");
    this.knowledge = initialValues.get("knowledge");
    this.social = initialValues.get("social");
    this.alcohol = initialValues.get("alcohol");
    this.finances = initialValues.get("finances");
  }



  public PlayerName getName() {
    return name;
  }

  public int getMoney() {
    return money;
  }

  public void setMoney(int money) {
    this.money = money;
  }

  public void addMoney(int money) {
    this.money += money;
  }

  public int getHealth() {
    return health;
  }

  public void setHealth(int health) {
    this.health = health;
  }

  public int getKnowledge() {
    return knowledge;
  }

  public void setKnowledge(int knowledge) {
    this.knowledge = knowledge;
  }

  public int getSocial() {
    return social;
  }

  public void setSocial(int social) {
    this.social = social;
  }

  public int getAlcohol() {
    return alcohol;
  }

  public void setAlcohol(int alcohol) {
    this.alcohol = alcohol;
  }

  public int getFinances() {
    return finances;
  }

  public void setFinances(int finances) {
    this.finances = finances;
  }

  public void move(int steps) {
    if (this.field + steps > 31) {
      this.field = this.field + steps - 32;
    } else {
      this.field += steps;
    }
  }

  @Override
  public String toString() {
    return "name=" + name;
  }

  public int getField() {
    return field;
  }

  public void addToLowestAttribute(int n) {
    int lowest = Collections.min(Arrays.asList(this.health, this.knowledge, this.social, this.alcohol, this.finances));
    addToAttributeWithAmount(lowest, n);
  }

  public void addToAttributeRandom(int n) {
    int random = (int) (Math.random() * 5);
    switch (random) {
      case 0 -> this.health += n;
      case 1 -> this.knowledge += n;
      case 2 -> this.social += n;
      case 3 -> this.alcohol += n;
      case 4 -> this.finances += n;
    }
  }

  public void addToAttributeSemiRandom(int n) {
    List<Integer> attributes = Arrays.asList(this.health, this.knowledge, this.social, this.alcohol, this.finances);

    int highest = Collections.max(Arrays.asList(this.health, this.knowledge, this.social, this.alcohol, this.finances));
    int lowest = Collections.min(Arrays.asList(this.health, this.knowledge, this.social, this.alcohol, this.finances));

    if (highest == lowest) {
      addToAttributeRandom(n);
      return;
    }

    List<Integer> list = new ArrayList<>();

    for (Integer attribute : attributes) {
      list.addAll(Collections.nCopies(20, attribute));
    }

    int bonusCount = list.size() / 10;
    list.addAll(Collections.nCopies(bonusCount, highest));
    list.addAll(Collections.nCopies(bonusCount, lowest));

    Collections.shuffle(list);
    if (Board.TESTING) {
      System.out.println(attributesToString());
    }
    String attributeToIncrease = getAttributeNameWithAmount(list.get(0));
    addToAttributeWithAmount(list.get(0), n);

    if (Board.TESTING) {
      System.out.println(this.toString() + " increased " + attributeToIncrease);
      System.out.println();
    }
  }

  private String getAttributeNameWithAmount(int n) {
    if (n == this.health) return "health";
    if (n == this.knowledge) return "knowledge";
    if (n == this.social) return "social";
    if (n == this.alcohol) return "alcohol";
    return "finances";
  }

  private int getAttributeAmountWithName(String s) {
    switch (s) {
      case "health" -> {
        return this.health;
      }
      case "knowledge" -> {
        return this.knowledge;
      }
      case "social" -> {
        return this.social;
      }
      case "alcohol" -> {
        return this.alcohol;
      }
      case "finances" -> {
        return this.finances;
      }
      default -> throw new IllegalArgumentException("name not in switch" + s);
    }
  }

  public void addToAttributeWithAmount(int amount, int points) {
    Map<String, Integer> attributes = new HashMap<>(Map.of(
            "health", this.health,
            "knowledge", this.knowledge,
            "social", this.social,
            "alcohol", this.alcohol,
            "finances", this.finances
    ));
    attributes.entrySet().removeIf(entry -> !entry.getValue().equals(amount));
    if (attributes.isEmpty()) {
      throw new IllegalArgumentException("no attribute with amount " + amount);
    }
    List<String> keys = new ArrayList<>(attributes.keySet());
    Random random = new Random();
    String randomKey = keys.get(random.nextInt(keys.size()));
    switch (randomKey) {
      case "health" -> this.health += points;
      case "knowledge" -> this.knowledge += points;
      case "social" -> this.social += points;
      case "alcohol" -> this.alcohol += points;
      case "finances" -> this.finances += points;
    }
  }


  public String attributesToString() {
    return "health=" + health +
            "\nknowledge=" + knowledge +
            "\nsocial=" + social +
            "\nalcohol=" + alcohol +
            "\nfinances=" + finances;
  }
}
