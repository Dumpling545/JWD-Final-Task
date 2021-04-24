package by.tc.task05.entity;

import java.io.Serializable;

public class RoomRatingInformation implements Serializable {
	private static final long serialVersionUID = -3092021102901187569L;
	private double rating;
	private int numberOfReviews;

	public RoomRatingInformation() {
	}

	public RoomRatingInformation(double rating, int numberOfReviews) {
		this.rating = rating;
		this.numberOfReviews = numberOfReviews;
	}

	public double getRating() {
		return rating;
	}

	public void setRating(double rating) {
		this.rating = rating;
	}

	public int getNumberOfReviews() {
		return numberOfReviews;
	}

	public void setNumberOfReviews(int numberOfReviews) {
		this.numberOfReviews = numberOfReviews;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}

		RoomRatingInformation that = (RoomRatingInformation) o;

		if (Double.compare(that.rating, rating) != 0) {
			return false;
		}
		return numberOfReviews == that.numberOfReviews;
	}

	@Override
	public int hashCode() {
		int result;
		long temp;
		temp = Double.doubleToLongBits(rating);
		result = (int) (temp ^ (temp >>> 32));
		result = 31 * result + numberOfReviews;
		return result;
	}

	@Override
	public String toString() {
		return "RoomRatingInformation{" +
				"rating=" + rating +
				", numberOfReviews=" + numberOfReviews +
				'}';
	}
}
