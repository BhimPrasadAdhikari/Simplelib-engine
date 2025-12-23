package com.luminalib.model;

public class DigitalMedia extends AbstractResource implements Searchable {
    private String format;

    public DigitalMedia(String title, String format) {
        super(title);
        this.format = format;
    }

    @Override
    public int getLoanPeriodInDays() {
        return 7;
    }

    @Override
    public boolean containsKeyword(String keyword) {
        return getTitle().toLowerCase().contains(keyword.toLowerCase()) ||
                format.toLowerCase().contains(keyword.toLowerCase());
    }

}