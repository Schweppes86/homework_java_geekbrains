package com.geekbrains.part2.lesson6;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherResponse {
    Headline HeadlineObject;
    ArrayList< Object > DailyForecasts = new ArrayList < Object > ();


    // Getter Methods

    public Headline getHeadline() {
        return HeadlineObject;
    }

    // Setter Methods

    public void setHeadline(Headline HeadlineObject) {
        this.HeadlineObject = HeadlineObject;
    }
}
@JsonIgnoreProperties(ignoreUnknown = true)
class Headline {
    private String EffectiveDate;
    private float EffectiveEpochDate;
    private float Severity;
    private String Text;
    private String Category;
    private String EndDate;
    private float EndEpochDate;
    private String MobileLink;
    private String Link;


    // Getter Methods

    public String getEffectiveDate() {
        return EffectiveDate;
    }

    public float getEffectiveEpochDate() {
        return EffectiveEpochDate;
    }

    public float getSeverity() {
        return Severity;
    }

    public String getText() {
        return Text;
    }

    public String getCategory() {
        return Category;
    }

    public String getEndDate() {
        return EndDate;
    }

    public float getEndEpochDate() {
        return EndEpochDate;
    }

    public String getMobileLink() {
        return MobileLink;
    }

    public String getLink() {
        return Link;
    }

    // Setter Methods

    public void setEffectiveDate(String EffectiveDate) {
        this.EffectiveDate = EffectiveDate;
    }

    public void setEffectiveEpochDate(float EffectiveEpochDate) {
        this.EffectiveEpochDate = EffectiveEpochDate;
    }

    public void setSeverity(float Severity) {
        this.Severity = Severity;
    }

    public void setText(String Text) {
        this.Text = Text;
    }

    public void setCategory(String Category) {
        this.Category = Category;
    }

    public void setEndDate(String EndDate) {
        this.EndDate = EndDate;
    }

    public void setEndEpochDate(float EndEpochDate) {
        this.EndEpochDate = EndEpochDate;
    }

    public void setMobileLink(String MobileLink) {
        this.MobileLink = MobileLink;
    }
}

