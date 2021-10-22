package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the PointArray type in your schema. */
public final class PointArray {
  private final String date_time;
  private final String point;
  public String getDateTime() {
      return date_time;
  }
  
  public String getPoint() {
      return point;
  }
  
  private PointArray(String date_time, String point) {
    this.date_time = date_time;
    this.point = point;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      PointArray pointArray = (PointArray) obj;
      return ObjectsCompat.equals(getDateTime(), pointArray.getDateTime()) &&
              ObjectsCompat.equals(getPoint(), pointArray.getPoint());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getDateTime())
      .append(getPoint())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(date_time,
      point);
  }
  public interface BuildStep {
    PointArray build();
    BuildStep dateTime(String dateTime);
    BuildStep point(String point);
  }
  

  public static class Builder implements BuildStep {
    private String date_time;
    private String point;
    @Override
     public PointArray build() {
        
        return new PointArray(
          date_time,
          point);
    }
    
    @Override
     public BuildStep dateTime(String dateTime) {
        this.date_time = dateTime;
        return this;
    }
    
    @Override
     public BuildStep point(String point) {
        this.point = point;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String dateTime, String point) {
      super.dateTime(dateTime)
        .point(point);
    }
    
    @Override
     public CopyOfBuilder dateTime(String dateTime) {
      return (CopyOfBuilder) super.dateTime(dateTime);
    }
    
    @Override
     public CopyOfBuilder point(String point) {
      return (CopyOfBuilder) super.point(point);
    }
  }
  
}
