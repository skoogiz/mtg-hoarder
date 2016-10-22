package in.hoarder.mtg.deckbox;

import in.hoarder.mtg.item.Item;

/**
 * 
 * Item representing an inventory item from https://deckbox.org/
 * 
 * @author skoogiz
 *
 */
public interface DeckboxItem extends Item
{
    public int getCount();

    public int getTradelistCount();

    public String getName();

    public String getEdition();

    public String getCardNumber();

    public String getCondition();

    public String getLanguage();

    public boolean isFoil();

    public boolean isSigned();

    public boolean isArtistProof();

    public boolean isAlteredArt();

    public boolean isMisprint();

    public boolean isPromo();

    public boolean isTextless();

    public String getMyPrice();

    public String getPrice();

    public String getCost();

    public String getType();

    public String getRarity();

    public String getImageURL();

    public static DeckboxItem create(final DeckboxBean bean)
    {
        return new DeckboxItem()
        {
            @Override
            public int getCount()
            {
                return bean.count;
            }

            @Override
            public int getTradelistCount()
            {
                return bean.tradelistCount;
            }

            @Override
            public String getName()
            {
                return bean.name;
            }

            @Override
            public String getEdition()
            {
                return bean.edition;
            }

            @Override
            public String getCardNumber()
            {
                return bean.cardNumber;
            }

            @Override
            public String getCondition()
            {
                return bean.condition;
            }

            @Override
            public String getLanguage()
            {
                return bean.language;
            }

            @Override
            public boolean isFoil()
            {
                return bean.foil;
            }

            @Override
            public boolean isSigned()
            {
                return bean.signed;
            }

            @Override
            public boolean isArtistProof()
            {
                return bean.artistProof;
            }

            @Override
            public boolean isAlteredArt()
            {
                return bean.alteredArt;
            }

            @Override
            public boolean isMisprint()
            {
                return bean.misprint;
            }

            @Override
            public boolean isPromo()
            {
                return bean.promo;
            }

            @Override
            public boolean isTextless()
            {
                return bean.textless;
            }

            @Override
            public String getMyPrice()
            {
                return bean.myPrice;
            }

            @Override
            public String getPrice()
            {
                return bean.price;
            }

            @Override
            public String getCost()
            {
                return bean.cost;
            }

            @Override
            public String getType()
            {
                return bean.type;
            }

            @Override
            public String getRarity()
            {
                return bean.rarity;
            }

            @Override
            public String getImageURL()
            {
                return bean.imageURL;
            }
        };
    };
}
