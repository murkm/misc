package mk.ssanta.data;

import java.util.ArrayList;
import java.util.List;

/*
 *  Encapsulates SecretSanta - recipient relationship
 */
class FamilyMember {

	private String name;
	private String recipient;
	private List<String> constraints;

	public FamilyMember(String name) {
		this.name = name;
		this.recipient = null;
		constraints = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public String getRecipient() {
		return recipient;
	}

	public void setRecipient(String name) {
		this.recipient = name;
	}

	public List<String> getConstraints() {
		return constraints;
	}

	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (obj.getClass() != getClass()) {
			return false;
		}
		FamilyMember fm = (FamilyMember) obj;

		return this.name.equals(fm.getName());

	}

	public int hashCode() {
		return this.name.hashCode();
	}

	public String toString() {
		return name + ": " + recipient;
	}

	public void addConstraint(String string) {
		constraints.add(string);		
	}
}

