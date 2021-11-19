package sg.edu.np.pfdassignment1;

public class Food {
    private String foodName;
    public String getFoodName() {return foodName; }
    public void setFoodName(String foodName) {this.foodName = foodName; }

    private int calories;
    public int getCalories() {return calories;}
    public void setCalories(int calories) {this.calories = calories; }

    private double sugar;
    public double getSugar() {return sugar;}
    public void setSugar(double sugar) {this.sugar = sugar;}

    public double sodium;
    public double getSodium() {return sodium;}
    public void setSodium(double sodium) {this.sodium = sodium;}

    public Food(){};
    public Food(String foodName, int calories, double sugar, double sodium) {
        this.foodName = foodName;
        this.calories = calories;
        this.sugar = sugar;
        this.sodium = sodium;
    }
}