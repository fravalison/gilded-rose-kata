package com.gildedrose;

import java.util.Arrays;

class GildedRose {

    public static final int QUALITY_CEILING = 50;

    Item[] items;

    public GildedRose(Item[] items) {
        this.items = items;
    }

    public void updateQuality() {
        Arrays.stream(items)
                .forEach(this::updateItem);
    }

    private void updateItem(Item item) {
        final ItemType itemType = ItemType.findByLabel(item.name);
        itemType.decreaseSellIn(item);
        itemType.calculateQuality(item);
    }

}