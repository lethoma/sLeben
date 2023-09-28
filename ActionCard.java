import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

enum ActionCardType{
  BRAU_UNION, FPO, WAHLER, HASE, HERPES, PLESCHI, WILLST_WAS_COOLES_SEHEN, GLOBULI, AUFDIEPLATZE,
  TAGTRAUMEN, RADLERISTKEINALKOHOL, CRINGE, MIND_GAMES, VICTORGEOGRAFIE, LEBENSKONTROLLVERLUST,
  DARTLN, PG16, BESSERWISSER, FURDIEBIENEN, GESUNDHEIT, CHA_CHA, SAMMASCHO16, MACBETH, FILMRISS,
  KLASSIKER, FALSCHESPFERD, GEHTAUFMICH, PINGPLAYER, KRYPTO, MANGONNTSICHJASONSTNIX, KOMMINDIEGRUPPE
}

public class ActionCard extends Card{
  private final static List<ActionCardType> actionCards = new ArrayList<>();
  public ActionCard() {

  }

  static void init(){
    actionCards.addAll(Arrays.asList(ActionCardType.values()));
  }

  public ActionCardType drawActionCard(Player player) {

  return null; //unfinished
  }
}
