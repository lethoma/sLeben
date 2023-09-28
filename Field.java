public class Field {
  private final int number;
  Field (int number) {
    this.number = number;
  }
  public Field copy(){
    return new Field(number);
  }
}
