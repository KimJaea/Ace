package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the ObjectArray type in your schema. */
public final class ObjectArray {
  private final String nam;
  private final Integer buy_place;
  private final List<Integer> recycle_place;
  private final List<Boolean> recycle_element;
  public String getNam() {
      return nam;
  }
  
  public Integer getBuyPlace() {
      return buy_place;
  }
  
  public List<Integer> getRecyclePlace() {
      return recycle_place;
  }
  
  public List<Boolean> getRecycleElement() {
      return recycle_element;
  }
  
  private ObjectArray(String nam, Integer buy_place, List<Integer> recycle_place, List<Boolean> recycle_element) {
    this.nam = nam;
    this.buy_place = buy_place;
    this.recycle_place = recycle_place;
    this.recycle_element = recycle_element;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ObjectArray objectArray = (ObjectArray) obj;
      return ObjectsCompat.equals(getNam(), objectArray.getNam()) &&
              ObjectsCompat.equals(getBuyPlace(), objectArray.getBuyPlace()) &&
              ObjectsCompat.equals(getRecyclePlace(), objectArray.getRecyclePlace()) &&
              ObjectsCompat.equals(getRecycleElement(), objectArray.getRecycleElement());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getNam())
      .append(getBuyPlace())
      .append(getRecyclePlace())
      .append(getRecycleElement())
      .toString()
      .hashCode();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(nam,
      buy_place,
      recycle_place,
      recycle_element);
  }
  public interface BuildStep {
    ObjectArray build();
    BuildStep nam(String nam);
    BuildStep buyPlace(Integer buyPlace);
    BuildStep recyclePlace(List<Integer> recyclePlace);
    BuildStep recycleElement(List<Boolean> recycleElement);
  }
  

  public static class Builder implements BuildStep {
    private String nam;
    private Integer buy_place;
    private List<Integer> recycle_place;
    private List<Boolean> recycle_element;
    @Override
     public ObjectArray build() {
        
        return new ObjectArray(
          nam,
          buy_place,
          recycle_place,
          recycle_element);
    }
    
    @Override
     public BuildStep nam(String nam) {
        this.nam = nam;
        return this;
    }
    
    @Override
     public BuildStep buyPlace(Integer buyPlace) {
        this.buy_place = buyPlace;
        return this;
    }
    
    @Override
     public BuildStep recyclePlace(List<Integer> recyclePlace) {
        this.recycle_place = recyclePlace;
        return this;
    }
    
    @Override
     public BuildStep recycleElement(List<Boolean> recycleElement) {
        this.recycle_element = recycleElement;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String nam, Integer buyPlace, List<Integer> recyclePlace, List<Boolean> recycleElement) {
      super.nam(nam)
        .buyPlace(buyPlace)
        .recyclePlace(recyclePlace)
        .recycleElement(recycleElement);
    }
    
    @Override
     public CopyOfBuilder nam(String nam) {
      return (CopyOfBuilder) super.nam(nam);
    }
    
    @Override
     public CopyOfBuilder buyPlace(Integer buyPlace) {
      return (CopyOfBuilder) super.buyPlace(buyPlace);
    }
    
    @Override
     public CopyOfBuilder recyclePlace(List<Integer> recyclePlace) {
      return (CopyOfBuilder) super.recyclePlace(recyclePlace);
    }
    
    @Override
     public CopyOfBuilder recycleElement(List<Boolean> recycleElement) {
      return (CopyOfBuilder) super.recycleElement(recycleElement);
    }
  }
  
}
