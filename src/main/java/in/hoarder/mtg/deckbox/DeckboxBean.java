package in.hoarder.mtg.deckbox;

import com.opencsv.bean.CsvBindByName;
import com.opencsv.bean.CsvCustomBindByName;

import in.hoarder.mtg.csv.CsvBean;
import in.hoarder.mtg.csv.FlagBeanField;

/**
 * 
 * Item representing an inventory row in a CSV export from https://deckbox.org/
 * 
 * @author skoogiz
 *
 */
public class DeckboxBean implements CsvBean<DeckboxItem>
{
    @CsvBindByName()
    public int count;

    @CsvBindByName(column = "Tradelist Count")
    public int tradelistCount;

    @CsvBindByName
    public String name;

    @CsvBindByName
    public String edition;

    @CsvBindByName(column = "Card Number")
    public String cardNumber;

    @CsvBindByName
    public String condition;

    @CsvBindByName
    public String language;

    @CsvCustomBindByName(column = "Foil", converter = FlagBeanField.class)
    public boolean foil;

    @CsvCustomBindByName(column = "Signed", converter = FlagBeanField.class)
    public boolean signed;

    @CsvCustomBindByName(column = "Artist Proof", converter = FlagBeanField.class)
    public boolean artistProof;

    @CsvCustomBindByName(column = "Altered Art", converter = FlagBeanField.class)
    public boolean alteredArt;

    @CsvCustomBindByName(column = "Misprint", converter = FlagBeanField.class)
    public boolean misprint;

    @CsvCustomBindByName(column = "Promo", converter = FlagBeanField.class)
    public boolean promo;

    @CsvCustomBindByName(column = "Textless", converter = FlagBeanField.class)
    public boolean textless;

    @CsvBindByName(column = "My Price")
    public String myPrice;

    @CsvBindByName
    public String price;

    @CsvBindByName
    public String cost;

    @CsvBindByName
    public String type;

    @CsvBindByName
    public String rarity;

    @CsvBindByName(column = "Image URL")
    public String imageURL;

    @Override
    public DeckboxItem toItem()
    {
        return DeckboxItem.create(this);
    }
}
