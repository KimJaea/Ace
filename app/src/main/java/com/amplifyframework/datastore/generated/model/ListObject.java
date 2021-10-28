package com.amplifyframework.datastore.generated.model;

import androidx.core.util.ObjectsCompat;
import com.amplifyframework.datastore.generated.model.AmplifyModelProvider;

import java.util.Objects;
import java.util.List;

/** This is an auto generated class representing the ListObject type in your schema. */
public final class ListObject {
  private final String name;
  private final Integer buy_place;
  private final List<Integer> recycle_element;
  private final List<Integer> recycle_place;
  public String getName() {
      return name;
  }

  public Integer getBuyPlace() {
      return buy_place;
  }

  public List<Integer> getRecycleElement() {
      return recycle_element;
  }

  public List<Integer> getRecyclePlace() {
      return recycle_place;
  }

  private ListObject(String name, Integer buy_place, List<Integer> recycle_element, List<Integer> recycle_place) {
    this.name = name;
    this.buy_place = buy_place;
    this.recycle_element = recycle_element;
    this.recycle_place = recycle_place;
  }

  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      ListObject listObject = (ListObject) obj;
      return ObjectsCompat.equals(getName(), listObject.getName()) &&
              ObjectsCompat.equals(getBuyPlace(), listObject.getBuyPlace()) &&
              ObjectsCompat.equals(getRecycleElement(), listObject.getRecycleElement()) &&
              ObjectsCompat.equals(getRecyclePlace(), listObject.getRecyclePlace());
      }
  }

  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getName())
      .append(getBuyPlace())
      .append(getRecycleElement())
      .append(getRecyclePlace())
      .toString()
      .hashCode();
  }

  public static BuildStep builder() {
      return new Builder();
  }

  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(name,
      buy_place,
      recycle_element,
      recycle_place);
  }
  public interface RecycleElementStep {
    BuildStep recycleElement(List<Integer> recycleElement);
  }


  public interface BuildStep {
    ListObject build();
    BuildStep name(String name);
    BuildStep buyPlace(Integer buyPlace);
    BuildStep recyclePlace(List<Integer> recyclePlace);
  }


  public static class Builder implements RecycleElementStep, BuildStep {
    private List<Integer> recycle_element;
    private String name;
    private Integer buy_place;
    private List<Integer> recycle_place;
    @Override
     public ListObject build() {

        return new ListObject(
          name,
          buy_place,
          recycle_element,
          recycle_place);
    }

    @Override
     public BuildStep recycleElement(List<Integer> recycleElement) {
        Objects.requireNonNull(recycleElement);
        this.recycle_element = recycleElement;
        return this;
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
  }


  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String name, Integer buyPlace, List<Integer> recycleElement, List<Integer> recyclePlace) {
      AmplifyModelProvider.getInstance();
      super.name(name)
        .buyPlace(buyPlace)
        .recycleElement(recycleElement)
        .recyclePlace(recyclePlace);
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
     public CopyOfBuilder recycleElement(List<Integer> recycleElement) {
      return (CopyOfBuilder) super.recycleElement(recycleElement);
    }

    @Override
     public CopyOfBuilder recyclePlace(List<Integer> recyclePlace) {
      return (CopyOfBuilder) super.recyclePlace(recyclePlace);
    }
  }

}
