package seedu.address.model.person;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.ToStringBuilder;
import seedu.address.model.tag.Tag;

/**
 * Represents a Person in the address book.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public class Person {

    // Identity fields
    private final Name name;
    private final Phone phone;
    private final Email email;

    // Data fields
    private final Address address;
    private final Set<Tag> tags = new HashSet<>();
    private final GradeList gradeList;
    private final AttendanceList attendanceList;

    /**
     * Every field must be present and not null.
     */
    public Person(Name name, Phone phone, Email email, Address address, Set<Tag> tags, GradeList gradeList,
            AttendanceList attendanceList) {
        requireAllNonNull(name, phone, email, address, tags, gradeList, attendanceList);
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.tags.addAll(tags);
        this.gradeList = gradeList;
        this.attendanceList = attendanceList;
    }

    public Name getName() {
        return name;
    }

    public Phone getPhone() {
        return phone;
    }

    public Email getEmail() {
        return email;
    }

    public Address getAddress() {
        return address;
    }

    public GradeList getGradeList() {
        return gradeList;
    }

    public AttendanceList getAttendanceList() {
        return attendanceList;
    }


    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Add grade to gradeList
     *
     * @param grade New grade to be added
     * @return new immutable Person
     */
    public Person addGrade(Grade grade) {
        requireAllNonNull(grade);

        GradeList newGradeList = this.gradeList.addGrade(grade);

        return new Person(this.name, this.phone, this.email, this.address, this.tags, newGradeList,
                          this.attendanceList);
    }

    /**
     * Remove grade from gradeList based on index.
     *
     * @param index The index to remove the grade.
     * @return new immutable Person
     */
    public Person removeGrade(Index index) {
        requireAllNonNull(index);

        GradeList newGradelist = this.gradeList.removeGrade(index);

        return new Person(this.name, this.phone, this.email, this.address, this.tags, newGradelist,
                          this.attendanceList);
    }

    /**
     * Returns true if both persons have the same name.
     * This defines a weaker notion of equality between two persons.
     */
    public boolean isSamePerson(Person otherPerson) {
        if (otherPerson == this) {
            return true;
        }

        return otherPerson != null
                && otherPerson.getName().equals(getName());
    }

    /**
     * Returns true if both persons have the same identity and data fields.
     * This defines a stronger notion of equality between two persons.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Person)) {
            return false;
        }

        Person otherPerson = (Person) other;
        return name.equals(otherPerson.name)
                && phone.equals(otherPerson.phone)
                && email.equals(otherPerson.email)
                && address.equals(otherPerson.address)
                && tags.equals(otherPerson.tags)
                && gradeList.equals(otherPerson.gradeList)
                && attendanceList.equals(otherPerson.attendanceList);
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(name, phone, email, address, tags, gradeList, attendanceList);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("name", name)
                .add("phone", phone)
                .add("email", email)
                .add("address", address)
                .add("tags", tags)
                .add("gradeList", gradeList)
                .add("attendanceList", attendanceList)
                .toString();
    }

}
