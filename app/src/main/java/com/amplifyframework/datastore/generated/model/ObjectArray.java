package com.amplifyframework.datastore.generated.model;


import androidx.core.util.ObjectsCompat;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the ObjectArray type in your schema. */
public final class ObjectArray {
  private final String name;
  private final Integer buy_place;
  private final List<Integer> recycle_place;
  private final List<String> recycle_element;
  public String getName() {
      return name;
  }
  
  public Integer getBuyPlace() {
      return buy_place;
  }
  
  public List<Integer> getRecyclePlace() {
      return recycle_place;
  }
  
  public List<String> getRecycleElement() {
      return recycle_element;
  }
  
  private ObjectArray(String name, Integer buy_place, List<Integer> recycle_place, List<String> recycle_element) {
    this.name = name;
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
      return ObjectsCompat.equals(getName(), objectArray.getName()) &&
              ObjectsCompat.equals(getBuyPlace(), objectArray.getBuyPlace()) &&
              ObjectsCompat.equals(getRecyclePlace(), objectArray.getRecyclePlace()) &&
              ObjectsCompat.equals(getRecycleElement(), objectArray.getRecycleElement());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getName())
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
    return new CopyOfBuilder(name,
      buy_place,
      recycle_place,
      recycle_element);
  }
  public interface BuildStep {
    ObjectArray build();
    BuildStep name(String name);
    BuildStep buyPlace(Integer buyPlace);
    BuildStep recyclePlace(List<Integer> recyclePlace);
    BuildStep recycleElement(List<String> recycleElement);
  }
  

  public static class Builder implements BuildStep {
    private String name;
    private Integer buy_place;
    private List<Integer> recycle_place;
    private List<String> recycle_element;
    @Override
     public ObjectArray build() {
        
        return new ObjectArray(
          name,
          buy_place,
          recycle_place,
          recycle_element);
    }
    
    @Override
     public BuildStep name(String name) {
        this.name = name;
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
     public BuildStep recycleElement(List<String> recycleElement) {
        this.recycle_element = recycleElement;
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String name, Integer buyPlace, List<Integer> recyclePlace, List<String> recycleElement) {
      super.name(name)
        .buyPlace(buyPlace)
        .recyclePlace(recyclePlace)
        .recycleElement(recycleElement);
    }
    
    @Override
     public CopyOfBuilder name(String name) {
      return (CopyOfBuilder) super.name(name);
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
     public CopyOfBuilder recycleElement(List<String> recycleElement) {
      return (CopyOfBuilder) super.recycleElement(recycleElement);
    }
  }
  
}
