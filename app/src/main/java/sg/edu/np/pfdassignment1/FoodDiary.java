package sg.edu.np.pfdassignment1;

import java.util.Date;

public class FoodDiary {
    private Date dateTime;
    public Date getDateTime() {return dateTime;}
    public void setDateTime(Date dt) {this.dateTime = dt;}

    private Food foodItem;
    public Food getFoodItem() {return foodItem;}
    public void setFoodItem(Food f) {this.foodItem = f;}

    public String getFoodItemName() {return foodItem.getFoodName(); }
}

