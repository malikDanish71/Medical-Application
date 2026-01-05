public class appointmentformdata
{
    String reason;
    String Date;
    String firstName ;
    String lastName ;

    public appointmentformdata(String reason, String Date, String firstName, String lastName) {
        this.reason = reason;
        this.Date = Date;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getDate() {
        return Date;
    }

    public void setDate(String date) {
        Date = date;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
}
