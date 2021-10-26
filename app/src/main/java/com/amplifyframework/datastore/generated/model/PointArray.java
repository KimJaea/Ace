package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the PointArray type in your schema. */
public final class PointArray {
  private final String date;
  private final String point;
  public String getDate() {
      return date;
  }
  
  public String getPoint() {
      return point;
  }
  
  private PointArray(String date, String point) {
    this.date = date;
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
      return ObjectsCompat.equals(getDate(), pointArray.getDate()) &&
              ObjectsCompat.equals(getPoint(), pointArray.getPoint());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getDate())
      .append(getPoint())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(date,
      point);
  }
  public interface BuildStep {
    PointArray build();
    BuildStep date(String date);
    BuildStep point(String point);
  }
  

  public static class Builder implements BuildStep {
    private String date;
    private String point;
    @Override
     public PointArray build() {
        
        return new PointArray(
          date,
          point);
    }
    
    @Override
     public BuildStep date(String date) {
        this.date = date;
        return this;
    }
    
    @Override
     public BuildStep point(String point) {
        this.point = point;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String date, String point) {
      super.date(date)
        .point(point);
    }
    
    @Override
     public CopyOfBuilder date(String date) {
      return (CopyOfBuilder) super.date(date);
    }
    
    @Override
     public CopyOfBuilder point(String point) {
      return (CopyOfBuilder) super.point(point);
    }
  }
  
}
