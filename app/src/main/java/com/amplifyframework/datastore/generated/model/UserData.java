package com.amplifyframework.datastore.generated.model;

import com.amplifyframework.core.model.temporal.Temporal;

import java.util.List;
import java.util.UUID;
import java.util.Objects;

import androidx.core.util.ObjectsCompat;

import com.amplifyframework.core.model.AuthStrategy;
import com.amplifyframework.core.model.Model;
import com.amplifyframework.core.model.ModelOperation;
import com.amplifyframework.core.model.annotations.AuthRule;
import com.amplifyframework.core.model.annotations.Index;
import com.amplifyframework.core.model.annotations.ModelConfig;
import com.amplifyframework.core.model.annotations.ModelField;
import com.amplifyframework.core.model.query.predicate.QueryField;

import static com.amplifyframework.core.model.query.predicate.QueryField.field;

/** This is an auto generated class representing the UserData type in your schema. */
@SuppressWarnings("all")
@ModelConfig(pluralName = "UserData", authRules = {
  @AuthRule(allow = AuthStrategy.PUBLIC, operations = { ModelOperation.UPDATE })
})
public final class UserData implements Model {
  public static final QueryField ID = field("UserData", "id");
  public static final QueryField PW = field("UserData", "pw");
  public static final QueryField LIST_OBJECT = field("UserData", "list_object");
  public static final QueryField POINT = field("UserData", "point");
  private final @ModelField(targetType="ID", isRequired = true) String id;
  private final @ModelField(targetType="String") String pw;
  private final @ModelField(targetType="ObjectArray") List<ObjectArray> list_object;
  private final @ModelField(targetType="PointArray") List<PointArray> point;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime createdAt;
  private @ModelField(targetType="AWSDateTime", isReadOnly = true) Temporal.DateTime updatedAt;
  public String getId() {
      return id;
  }
  
  public String getPw() {
      return pw;
  }
  
  public List<ObjectArray> getListObject() {
      return list_object;
  }
  
  public List<PointArray> getPoint() {
      return point;
  }
  
  public Temporal.DateTime getCreatedAt() {
      return createdAt;
  }
  
  public Temporal.DateTime getUpdatedAt() {
      return updatedAt;
  }
  
  private UserData(String id, String pw, List<ObjectArray> list_object, List<PointArray> point) {
    this.id = id;
    this.pw = pw;
    this.list_object = list_object;
    this.point = point;
  }
  
  @Override
   public boolean equals(Object obj) {
      if (this == obj) {
        return true;
      } else if(obj == null || getClass() != obj.getClass()) {
        return false;
      } else {
      UserData userData = (UserData) obj;
      return ObjectsCompat.equals(getId(), userData.getId()) &&
              ObjectsCompat.equals(getPw(), userData.getPw()) &&
              ObjectsCompat.equals(getListObject(), userData.getListObject()) &&
              ObjectsCompat.equals(getPoint(), userData.getPoint()) &&
              ObjectsCompat.equals(getCreatedAt(), userData.getCreatedAt()) &&
              ObjectsCompat.equals(getUpdatedAt(), userData.getUpdatedAt());
      }
  }
  
  @Override
   public int hashCode() {
    return new StringBuilder()
      .append(getId())
      .append(getPw())
      .append(getListObject())
      .append(getPoint())
      .append(getCreatedAt())
      .append(getUpdatedAt())
      .toString()
      .hashCode();
  }
  
  @Override
   public String toString() {
    return new StringBuilder()
      .append("UserData {")
      .append("id=" + String.valueOf(getId()) + ", ")
      .append("pw=" + String.valueOf(getPw()) + ", ")
      .append("list_object=" + String.valueOf(getListObject()) + ", ")
      .append("point=" + String.valueOf(getPoint()) + ", ")
      .append("createdAt=" + String.valueOf(getCreatedAt()) + ", ")
      .append("updatedAt=" + String.valueOf(getUpdatedAt()))
      .append("}")
      .toString();
  }
  
  public static BuildStep builder() {
      return new Builder();
  }
  
  /** 
   * WARNING: This method should not be used to build an instance of this object for a CREATE mutation.
   * This is a convenience method to return an instance of the object with only its ID populated
   * to be used in the context of a parameter in a delete mutation or referencing a foreign key
   * in a relationship.
   * @param id the id of the existing item this instance will represent
   * @return an instance of this model with only ID populated
   * @throws IllegalArgumentException Checks that ID is in the proper format
   */
  public static UserData justId(String id) {
    try {
      UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
    } catch (Exception exception) {
      throw new IllegalArgumentException(
              "Model IDs must be unique in the format of UUID. This method is for creating instances " +
              "of an existing object with only its ID field for sending as a mutation parameter. When " +
              "creating a new object, use the standard builder method and leave the ID field blank."
      );
    }
    return new UserData(
      id,
      null,
      null,
      null
    );
  }
  
  public CopyOfBuilder copyOfBuilder() {
    return new CopyOfBuilder(id,
      pw,
      list_object,
      point);
  }
  public interface BuildStep {
    UserData build();
    BuildStep id(String id) throws IllegalArgumentException;
    BuildStep pw(String pw);
    BuildStep listObject(List<ObjectArray> listObject);
    BuildStep point(List<PointArray> point);
  }
  

  public static class Builder implements BuildStep {
    private String id;
    private String pw;
    private List<ObjectArray> list_object;
    private List<PointArray> point;
    @Override
     public UserData build() {
        String id = this.id != null ? this.id : UUID.randomUUID().toString();
        
        return new UserData(
          id,
          pw,
          list_object,
          point);
    }
    
    @Override
     public BuildStep pw(String pw) {
        this.pw = pw;
        return this;
    }
    
    @Override
     public BuildStep listObject(List<ObjectArray> listObject) {
        this.list_object = listObject;
        return this;
    }
    
    @Override
     public BuildStep point(List<PointArray> point) {
        this.point = point;
        return this;
    }
    
    /** 
     * WARNING: Do not set ID when creating a new object. Leave this blank and one will be auto generated for you.
     * This should only be set when referring to an already existing object.
     * @param id id
     * @return Current Builder instance, for fluent method chaining
     * @throws IllegalArgumentException Checks that ID is in the proper format
     */
    public BuildStep id(String id) throws IllegalArgumentException {
        this.id = id;
        
        try {
            UUID.fromString(id); // Check that ID is in the UUID format - if not an exception is thrown
        } catch (Exception exception) {
          throw new IllegalArgumentException("Model IDs must be unique in the format of UUID.",
                    exception);
        }
        
        return this;
    }
  }
  

  public final class CopyOfBuilder extends Builder {
    private CopyOfBuilder(String id, String pw, List<ObjectArray> listObject, List<PointArray> point) {
      super.id(id);
      super.pw(pw)
        .listObject(listObject)
        .point(point);
    }
    
    @Override
     public CopyOfBuilder pw(String pw) {
      return (CopyOfBuilder) super.pw(pw);
    }
    
    @Override
     public CopyOfBuilder listObject(List<ObjectArray> listObject) {
      return (CopyOfBuilder) super.listObject(listObject);
    }
    
    @Override
     public CopyOfBuilder point(List<PointArray> point) {
      return (CopyOfBuilder) super.point(point);
    }
  }
  
}
