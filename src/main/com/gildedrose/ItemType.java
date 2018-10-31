package com.gildedrose;

import java.util.Arrays;

public enum ItemType {
    AGED_BRIE("Aged Brie") {
        @Override
        public void calculateQuality(Item item) {
            if (item.quality < GildedRose.QUALITY_CEILING) {
                item.quality++;
            }
            if (item.sellIn < 0) {
                if (item.quality < GildedRose.QUALITY_CEILING) {
                    item.quality++;
                }
            }
        }
    },
    BACKSTAGE_PASSES("Backstage passes to a TAFKAL80ETC concert") {
        @Override
        public void calculateQuality(Item item) {
            if (item.quality < GildedRose.QUALITY_CEILING) {
                item.quality++;

                if (item.sellIn < 11) {
                    if (item.quality < GildedRose.QUALITY_CEILING) {
                        item.quality++;
                    }
                }

                if (item.sellIn < 6) {
                    if (item.quality < GildedRose.QUALITY_CEILING) {
                        item.quality++;
                    }
                }
            }
            if (item.sellIn < 0) {
                item.quality = item.quality - item.quality;
            }
        }
    },
    SULFURAS("Sulfuras, Hand of Ragnaros") {
        @Override
        public void calculateQuality(Item item) {
            // Nothing todo
        }
        @Override
        public void decreaseSellIn(Item item) {
            // Nothing todo
        }
    },
    CONJURED("Conjured") {
        @Override
        public void calculateQuality(Item item) {
            item.quality = item.quality - 2;
        }
    },
    CASUAL("") {
        @Override
        public void calculateQuality(Item item) {
            if (item.quality > 0) {
                item.quality --;
            }
            if (item.sellIn < 0) {
                if (item.quality > 0) {
                    item.quality --;
                }
            }
        }
    };

    private String label;

    ItemType(String label) {
        this.label = label;
    }

    public static ItemType findByLabel(String label) {
        return Arrays.stream(ItemType.values())
                .filter(itemType -> itemType.label.equals(label))
                .findFirst()
                .orElse(CASUAL);
    }

    public abstract void calculateQuality(Item item);

    public void decreaseSellIn(Item item) {
        item.sellIn --;
    }
}
