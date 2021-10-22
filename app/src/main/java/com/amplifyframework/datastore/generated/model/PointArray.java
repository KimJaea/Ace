package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the PointArray type in your schema. */
public final class PointArray {
  private final Temporal.DateTime date_time;
  private final Integer point;
  public Temporal.DateTime getDateTime() {
      return date_time;
  }
  
  public Integer getPoint() {
      return point;
  }
  
  private PointArray(Temporal.DateTime date_time, Integer point) {
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
    BuildStep dateTime(Temporal.DateTime dateTime);
    BuildStep point(Integer point);
  }
  

  public static class Builder implements BuildStep {
    private Temporal.DateTime date_time;
    private Integer point;
    @Override
     public PointArray build() {
        
        return new PointArray(
          date_time,
          point);
    }
    
    @Override
     public BuildStep dateTime(Temporal.DateTime dateTime) {
        this.date_time = dateTime;
        return this;
    }
    
    @Override
     public BuildStep point(Integer point) {
        this.point = point;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(Temporal.DateTime dateTime, Integer point) {
      super.dateTime(dateTime)
        .point(point);
    }
    
    @Override
     public CopyOfBuilder dateTime(Temporal.DateTime dateTime) {
      return (CopyOfBuilder) super.dateTime(dateTime);
    }
    
    @Override
     public CopyOfBuilder point(Integer point) {
      return (CopyOfBuilder) super.point(point);
    }
  }
  
}
