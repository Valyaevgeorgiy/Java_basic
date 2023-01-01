package org.example;

/** Represents an employee.
 * @author Someone (Provides information about the author, typically the author’s name, e-mail address, website information, and so on.)
 * @version 1.5 (Indicates the version number.)
 * @since 1.0 (Used to indicate the version with which this class, field, or method was added).
 */
public class Employee
{
    private String lastName;
    private String firstName;
    private Double salary;
    /** Represents the employee’s address.
     */
    public String address;
    /** Creates an employee with the specified name.
     * @param lastName The employee’s last name.
     * @param firstName The employee’s first name.
     */
    public Employee(String lastName, String firstName)
    {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = " ";
    }
    /** Gets the employee’s last name.
     * @return A string representing the employee’s last
     *     name.
     */
    public String getLastName()
    {
        return this.lastName;
    }
    /** Sets the employee’s last name.
     * @param lastName A String containing the employee’s
     *     last name.
     */
    public void setLastName(String lastName)
    {
        this.lastName = lastName;
    }
    /** Gets the employee’s first name.
     * @return A string representing the employee’s first
     *     name.
     */
    public String getFirstName()
    {
        return this.firstName;
    }
    /** Sets the employee’s first name.
     * @param firstName A String containing the
     *     employee’s first name.
     */
    public void setFirstName(String firstName)
    {
        this.firstName = firstName;
    }
    /** Gets the employee’s salary.
     * @return A double representing the employee’s salary.
     */
    public double getSalary()
    {
        return this.salary;
    }
    /** Sets the employee’s salary.
     * @param salary A double containing the employee’s
     *     salary.
     */
    public void setSalary(double salary)
    {
        this.salary = salary;
    }
}
