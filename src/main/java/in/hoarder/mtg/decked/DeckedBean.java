package in.hoarder.mtg.decked;

import com.opencsv.bean.CsvBindByName;

import in.hoarder.mtg.csv.CsvBean;

/**
 * 
 * Item representing an inventory row in a CSV export from Decked Builder.
 * 
 * @author skoogiz
 *
 */
public class DeckedBean implements CsvBean
{
    @CsvBindByName(column = "Total Qty")
    public int totalQuantity;

    @CsvBindByName(column = "Reg Qty")
    public int regularQuantity;

    @CsvBindByName(column = "Foil Qty")
    public int foilQuantity;

    @CsvBindByName(column = "Card")
    public String card;

    @CsvBindByName(column = "Set")
    public String set;

    @CsvBindByName(column = "Mana Cost")
    public String manaCost;

    @CsvBindByName(column = "Card Type")
    public String cardType;

    @CsvBindByName(column = "Color")
    public String color;

    @CsvBindByName(column = "Rarity")
    public String rarity;

    @CsvBindByName(column = "Mvid")
    public String multiverseId;

    @CsvBindByName(column = "Single Price")
    public Double singlePrice;

    @CsvBindByName(column = "Single Foil Price")
    public Double singleFoilPrice;

    @CsvBindByName(column = "Total Price")
    public Double totalPrice;

    @CsvBindByName(column = "")
    public String priceSource;

    @CsvBindByName(column = "Notes")
    public String notes;
}
