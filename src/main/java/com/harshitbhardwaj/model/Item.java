package com.harshitbhardwaj.model;

/**
 * @author Harshit Bhardwaj
 */
public class Item {

    private String itemName;
    private String itemTitle;
    private String itemSize;
    private String itemSeller;

    private int itemPrice;
    private int itemMRP;

    public Item() {
    }

    public Item(String itemName, String itemTitle, String itemSize, String itemSeller,
                int itemPrice, int itemMRP) {
        this.itemName = itemName;
        this.itemTitle = itemTitle;
        this.itemSize = itemSize;
        this.itemSeller = itemSeller;
        this.itemPrice = itemPrice;
        this.itemMRP = itemMRP;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemTitle() {
        return itemTitle;
    }

    public void setItemTitle(String itemTitle) {
        this.itemTitle = itemTitle;
    }

    public String getItemSize() {
        return itemSize;
    }

    public void setItemSize(String itemSize) {
        this.itemSize = itemSize;
    }

    public String getItemSeller() {
        return itemSeller;
    }

    public void setItemSeller(String itemSeller) {
        this.itemSeller = itemSeller;
    }

    public int getItemPrice() {
        return itemPrice;
    }

    public void setItemPrice(int itemPrice) {
        this.itemPrice = itemPrice;
    }

    public int getItemMRP() {
        return itemMRP;
    }

    public void setItemMRP(int itemMRP) {
        this.itemMRP = itemMRP;
    }

    @Override
    public String toString() {
        return "Item{" +
                "itemName='" + itemName + '\'' +
                ", itemTitle='" + itemTitle + '\'' +
                ", itemSize='" + itemSize + '\'' +
                ", itemSeller='" + itemSeller + '\'' +
                ", itemPrice=" + itemPrice +
                ", itemMRP=" + itemMRP +
                '}';
    }
}
