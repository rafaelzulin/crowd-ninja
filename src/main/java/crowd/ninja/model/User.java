package crowd.ninja.model;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
public class User implements Serializable {

   private static final long serialVersionUID = 1L;
   private int id;
   private String name;

   public User(){}
   
   public User(int id, String name){
      this.id = id;
      this.name = name;
   }

   public int getId() {
      return id;
   }
   @XmlElement
   public void setId(int id) {
      this.id = id;
   }
   public String getName() {
      return name;
   }
   @XmlElement
   public void setName(String name) {
      this.name = name;
   }
   @Override
   public boolean equals(Object object){
		if (object == null) {
			return false;
		} else if (!(object instanceof User)) {
			return false;
		} else {
			User user = (User) object;
			if (id == user.getId() && name.equals(user.getName())) {
				return true;
			}
		}
	      return false;
	   }	
}