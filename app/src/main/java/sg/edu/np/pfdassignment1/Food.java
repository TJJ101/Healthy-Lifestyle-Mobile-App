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

    public int sodium;
    public int getSodium() {return sodium;}
    public void setSodium(int sodium) {this.sodium = sodium;}

    public double fat;
    public double getFat() {return fat;}
    public void setFat(double fat) {this.fat = fat;}

    public Food(){};
    public Food(String foodName, int calories, double sugar, int sodium, double fat) {
        this.foodName = foodName;
        this.calories = calories;
        this.sugar = sugar;
        this.sodium = sodium;
        this.fat = fat;
    }
}