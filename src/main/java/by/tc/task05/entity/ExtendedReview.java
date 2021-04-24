package by.tc.task05.entity;

import by.tc.task05.entity.Review;

import java.time.LocalDate;

public class ExtendedReview extends Review {
	private static final long serialVersionUID = 7441936224698712730L;
	private String userAvatar;
	private String userEmail;
	public ExtendedReview() {
	}

	public ExtendedReview(int reservationId, String title, String text,
	                      int rating,
	                      LocalDate date, String userAvatar,
	                      String userEmail)
	{
		super(reservationId, title, text, rating, date);
		this.userAvatar = userAvatar;
		this.userEmail = userEmail;
	}

	public String getUserAvatar() {
		return userAvatar;
	}

	public void setUserAvatar(String userAvatar) {
		this.userAvatar = userAvatar;
	}

	public String getUserEmail() {
		return userEmail;
	}

	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		if (!super.equals(o)) {
			return false;
		}

		ExtendedReview that = (ExtendedReview) o;

		if (userAvatar != null ? !userAvatar.equals(that.userAvatar) :
				that.userAvatar != null)
		{
			return false;
		}
		return userEmail != null ? userEmail.equals(that.userEmail) :
				that.userEmail == null;
	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + (userAvatar != null ? userAvatar.hashCode() : 0);
		result = 31 * result + (userEmail != null ? userEmail.hashCode() : 0);
		return result;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder(super.toString());
		sb.deleteCharAt(sb.indexOf("}"));
		sb.append(", userAvatar='").append(userAvatar).append('\'');
		sb.append(", userEmail='").append(userEmail).append('\'').append('}');
		return sb.toString();
	}
}
