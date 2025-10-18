package Lab3;

import java.util.Objects;

/**
 * Class representing an educational institution.
 * Contains information about name, type, foundation year, student count and rating.
 *
 * @author Korotaiev Mykhailo
 */
public class EducationalInstitution implements Comparable<EducationalInstitution> {
	/** Name of the educational institution (for example, "Kyiv National University") */
	private String name;
	/** Type of the institution (University, College, School, Technical School, etc.) */
	private String type;
	/** Foundation year of the institution (for example, 1834) */
	private int foundationYear;
	/** Total number of students (integer) */
	private int studentCount;
	/** Institution rating in the range 0.0 - 100.0 */
	private double rating;

	public EducationalInstitution() {
	}

	public EducationalInstitution(String name, String type, int foundationYear,
								  int studentCount, double rating) {
		this.name = name;
		this.type = type;
		this.foundationYear = foundationYear;
		this.studentCount = studentCount;
		this.rating = rating;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFoundationYear() {
		return foundationYear;
	}

	public void setFoundationYear(int foundationYear) {
		this.foundationYear = foundationYear;
	}

	public int getStudentCount() {
		return studentCount;
	}

	public void setStudentCount(int studentCount) {
		this.studentCount = studentCount;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	@Override
	public int compareTo(EducationalInstitution other) {
		return Integer.compare(this.foundationYear, other.foundationYear);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null || getClass() != obj.getClass()) {
			return false;
		}
		EducationalInstitution that = (EducationalInstitution) obj;
		return foundationYear == that.foundationYear &&
			   studentCount == that.studentCount &&
			   Double.compare(that.rating, rating) == 0 &&
			   Objects.equals(name, that.name) &&
			   Objects.equals(type, that.type);
	}

	@Override
	public int hashCode() {
		return Objects.hash(name, type, foundationYear, studentCount, rating);
	}

	@Override
	public String toString() {
		return String.format("EducationalInstitution{name='%s', type='%s', foundationYear=%d, " +
						   "studentCount=%d, rating=%.2f}",
						   name, type, foundationYear, studentCount, rating);
	}
}
