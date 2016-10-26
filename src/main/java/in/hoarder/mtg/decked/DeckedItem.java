package in.hoarder.mtg.decked;

import in.hoarder.mtg.item.Item;

/**
 * 
 * Item representing an inventory item from Decked Builder.
 * 
 * @author skoogiz
 *
 */
public interface DeckedItem extends Item
{
    public int getTotalQuantity();

    public int getRegularQuantity();

    public int getFoilQuantity();

    public String getCard();

    public String getSet();

    public String getManaCost();

    public String getCardType();

    public String getColor();

    public String getRarity();

    public String getMultiverseId();

    public Double getSinglePrice();

    public Double getSingleFoilPrice();

    public Double getTotalPrice();

    public String getPriceSource();

    public String getNotes();

    public static DeckedItem create(final DeckedBean bean)
    {
        return new Builder()
            .totalQuantity(bean.totalQuantity)
            .regularQuantity(bean.regularQuantity)
            .foilQuantity(bean.foilQuantity)
            .card(bean.card)
            .set(bean.set)
            .multiverseId(bean.multiverseId)
            .manaCost(bean.manaCost)
            .cardType(bean.cardType)
            .color(bean.color)
            .rarity(bean.rarity)
            .singlePrice(bean.singlePrice)
            .singleFoilPrice(bean.singleFoilPrice)
            .totalPrice(bean.totalPrice)
            .priceSource(bean.priceSource)
            .notes(bean.notes)
            .build();
    }

    public static class Builder
    {
        private int totalQuantity = 0;
        private int regularQuantity = 0;
        private int foilQuantity = 0;
        private String card = "";
        private String set = "";
        private String multiverseId = "";
        private String manaCost = "";
        private String cardType = "";
        private String color = "";
        private String rarity = "";
        private double singlePrice = 0.0;
        private double singleFoilPrice = 0.0;
        private double totalPrice = 0.0;
        private String priceSource = "";
        private String notes = "";

        public Builder()
        {
        }

        public Builder totalQuantity(Integer totalQuantity)
        {
            if (totalQuantity != null)
                this.totalQuantity = totalQuantity;
            return this;
        }

        public Builder regularQuantity(Integer regularQuantity)
        {
            if (regularQuantity != null)
                this.regularQuantity = regularQuantity;
            return this;
        }

        public Builder foilQuantity(Integer foilQuantity)
        {
            if (foilQuantity != null)
                this.foilQuantity = foilQuantity;
            return this;
        }

        public Builder card(String card)
        {
            this.card = card;
            return this;
        }

        public Builder set(String set)
        {
            this.set = set;
            return this;
        }

        public Builder multiverseId(String multiverseId)
        {
            this.multiverseId = multiverseId;
            return this;
        }

        public Builder manaCost(String manaCost)
        {
            this.manaCost = manaCost;
            return this;
        }

        public Builder cardType(String cardType)
        {
            this.cardType = cardType;
            return this;
        }

        public Builder color(String color)
        {
            this.color = color;
            return this;
        }

        public Builder rarity(String rarity)
        {
            this.rarity = rarity;
            return this;
        }

        public Builder priceSource(String priceSource)
        {
            this.priceSource = priceSource;
            return this;
        }

        public Builder notes(String notes)
        {
            this.notes = notes;
            return this;
        }

        public Builder singlePrice(Double singlePrice)
        {
            if (singlePrice != null)
                this.singlePrice = singlePrice;
            return this;
        }

        public Builder singleFoilPrice(Double singleFoilPrice)
        {
            if (singleFoilPrice != null)
                this.singleFoilPrice = singleFoilPrice;
            return this;
        }

        public Builder totalPrice(Double totalPrice)
        {
            if (totalPrice != null)
                this.totalPrice = totalPrice;
            return this;
        }

        public DeckedItem build()
        {
            return new DeckedItem()
            {
                @Override
                public int getTotalQuantity()
                {
                    return totalQuantity;
                }

                @Override
                public Double getTotalPrice()
                {
                    return totalPrice;
                }

                @Override
                public Double getSinglePrice()
                {
                    return singlePrice;
                }

                @Override
                public Double getSingleFoilPrice()
                {
                    return singleFoilPrice;
                }

                @Override
                public String getSet()
                {
                    return set;
                }

                @Override
                public int getRegularQuantity()
                {
                    return regularQuantity;
                }

                @Override
                public String getRarity()
                {
                    return rarity;
                }

                @Override
                public String getPriceSource()
                {
                    return priceSource;
                }

                @Override
                public String getNotes()
                {
                    return notes;
                }

                @Override
                public String getMultiverseId()
                {
                    return multiverseId;
                }

                @Override
                public String getManaCost()
                {
                    return manaCost;
                }

                @Override
                public int getFoilQuantity()
                {
                    return foilQuantity;
                }

                @Override
                public String getColor()
                {
                    return color;
                }

                @Override
                public String getCardType()
                {
                    return cardType;
                }

                @Override
                public String getCard()
                {
                    return card;
                }
            };
        }
    }
}
