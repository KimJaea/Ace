package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the Point type in your schema. */
public final class Point {
  private final String point_date;
  private final Integer point_score;
  public String getPointDate() {
      return point_date;
  }
  
  public Integer getPointScore() {
      return point_score;
  }
  
  private Point(String point_date, Integer point_score) {
    this.point_date = point_date;
    this.point_score = point_score;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      Point point = (Point) obj;
      return ObjectsCompat.equals(getPointDate(), point.getPointDate()) &&
              ObjectsCompat.equals(getPointScore(), point.getPointScore());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getPointDate())
      .append(getPointScore())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(point_date,
      point_score);
  }
  public interface BuildStep {
    Point build();
    BuildStep pointDate(String pointDate);
    BuildStep pointScore(Integer pointScore);
  }
  

  public static class Builder implements BuildStep {
    private String point_date;
    private Integer point_score;
    @Override
     public Point build() {
        
        return new Point(
          point_date,
          point_score);
    }
    
    @Override
     public BuildStep pointDate(String pointDate) {
        this.point_date = pointDate;
        return this;
    }
    
    @Override
     public BuildStep pointScore(Integer pointScore) {
        this.point_score = pointScore;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String pointDate, Integer pointScore) {
      super.pointDate(pointDate)
        .pointScore(pointScore);
    }
    
    @Override
     public CopyOfBuilder pointDate(String pointDate) {
      return (CopyOfBuilder) super.pointDate(pointDate);
    }
    
    @Override
     public CopyOfBuilder pointScore(Integer pointScore) {
      return (CopyOfBuilder) super.pointScore(pointScore);
    }
  }
  
}
