import java.util.ArrayList;

abstract class Employee{ // can not be instantiated directly because of inheritance hierarchy issues

  private String name;

  private int id;

  public Employee(String name ,int id){
  this.name =name;
  this.id = id;
 }

 
  public String getName(){
    return name;
  }
  public int getid(){
    return id;
  }


   public abstract double calculateSalary(); // Abstract method - here we only declare 

   @Override
   public String toString(){ // good formatting
    return "Employee[name="+name+", id="+id+", salary="+calculateSalary()+"]";
   }
}


class FullTimeEmployee extends Employee{// employee is abstract type
    private double monthlySalary;

    public FullTimeEmployee(String name ,int id ,double monthlySalary){
        super(name,id); // super is used to access parent constructor
        this.monthlySalary = monthlySalary;
    }

    @Override // here polymorphism in action

    public double calculateSalary(){ // here we use abstract method
        return monthlySalary;
    }
}


class PartTimeEmployee extends Employee{
    private int hoursWorked;

    private double hoursRate;

    public PartTimeEmployee(String name ,int id,int hoursWorked,double hoursRate){
        super(name,id);
        this.hoursWorked = hoursWorked;
        this.hoursRate = hoursRate;
    }

    @Override
    public double calculateSalary(){
        return hoursWorked * hoursRate;
    }
}


class PayrollSystem{
    private ArrayList<Employee> employeeList;// collection of similar types of data

    public PayrollSystem(){
        employeeList = new ArrayList<>(); // new memory allocate
    }

    public void addEmployee(Employee employee){
        employeeList.add(employee);
    }


    public void removeEmployee(int id){
        Employee employeeToRemove = null;
        for(Employee employee : employeeList){ // iterate over employee list
            if(employee.getid()== id){
                employeeToRemove = employee;
                break;
            }
        }
        if(employeeToRemove != null){
            employeeList.remove(employeeToRemove);
        }
    }


    public void displayEmployees(){
        for(Employee employee:employeeList){
            System.out.println(employee);
        }
    }
}


public class Main{
    public static void main(String[] args) {
        PayrollSystem payrollSystem = new PayrollSystem();
        FullTimeEmployee emp1 = new FullTimeEmployee("vikas", 1, 70000);
        PartTimeEmployee emp2 = new PartTimeEmployee("Alexeander",2,40,100);

        payrollSystem.addEmployee(emp1);
        payrollSystem.addEmployee(emp2);
        System.out.println("Intial Employee Details :");
        payrollSystem.displayEmployees();
        System.out.println("Removing Employee :");
        payrollSystem.removeEmployee(2);
        System.out.println("Remaining Employee Details :");
        payrollSystem.displayEmployees();

    }
}