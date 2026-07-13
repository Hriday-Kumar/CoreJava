package java8interview;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.Year;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.Set;
import java.util.TreeMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class InterviewQuestionsJava8 {
	


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// mapToInt() always take (Integer :: intValue) as argument. 
		/*
		 0. Write code for add by implementing functional interface using java 8. 
		 1. Sum of a list. ================= Asked in Wipro
		 2. Min and Max value from a list. ============== Asked in Citius Tech
		 3. Find the duplicate number from a given Integer list.==================Asked in Citius Tech
		 4. Given the list of employee, group them by employee name.=================Asked in Wipro
		 5. Sorting a Map as per key.===================Asked in CAPGEMINEE
		 6. Sort a Map as per key in descending order.
		 */
		/*
		 6. Write the java 8 code for printing the odd and even from a list.
		 6. Write the java 8 code for printing the odd and even from a list in one java code statement.
		 7. Write the java 8 code to remove the duplicate number from a array like {20,20,30,30,45,96,2,}
		 8. Character repeat count.
		 9. To count the occurrences of words from a given string 
		 9. Reverse the order of the array using streams
		 10.Sort the list using Stream API
		 11. Difference between Map & FlatMap
		 
		 	
		 	
		//12. Find the  map of employee as per key "EmployeeId" and value "EmployeeName" and whose salary is less<300000.
		//13. Get all department in a list from Employee list.
		//14. Sort the employee list as per salary.
		//15. Find the max and min paid salary employee from Employee list.
		//16. gender(key) -> [names](value)    groupingBy and mapping
		//17. Find the total number of employee as per male or female.      groupingBy
		//18. anyMatch(predicate), allMatch(predicate), nonMatch(predicate)  all return type is boolean.
		//19. Find top 2 paid employee. limit(2)
		 * 
		*/	
		//0. Write code for add by implementing functional interface using java 8.
		MyInterface<Integer> impl = (a, b)-> a+b;
		System.out.println("Addition is : "+impl.add(20, 10));
		
		
		//1. Sum of a list ================= Asked in Wipro
		List<Integer> listForSum = new ArrayList();
		listForSum.add(20);listForSum.add(20);listForSum.add(30);listForSum.add(10);listForSum.add(20);
		
		//First Method
		Integer sumOfList = listForSum.stream().collect(Collectors.summingInt(Integer::intValue));
		System.out.println("Sum of a list : "+sumOfList);
		
		//Second  Method612
		
		Integer sumOfList1 = listForSum.stream().mapToInt(Integer::intValue).sum();
		System.out.println("Sum of a list : "+sumOfList1);
		
		Integer[] intArray = new Integer[] {20,20,30,30,3,8,6,3};
		List<Integer> intList = Arrays.asList(intArray);
		//2. Min and Max value from a list ============== Asked in Citius Tech
		System.out.println("Given list is : "+Arrays.toString(intArray));
		Integer min = intList.stream().min(Integer::compareTo).get(); // Keep on thing in mind that which method takes Comparator or Comparable then need to use get() method because min, max has used with Optional.
		Integer max = intList.stream().max(Integer::compareTo).get();
		max = intList.stream().min(Comparator.comparing(Integer :: intValue)).get();
		//Second Method :
		OptionalInt opt = intList.stream().mapToInt(Integer::intValue).max();
		max = opt.getAsInt();
		System.out.println("Max is :"+max);
		//Third Method
		max = intList.stream().max((i1, i2) -> i1.compareTo(i2)).get();
		System.out.println("Max number is : "+max);
		
		System.out.println("Minimum value from a list is : "+min);
		System.out.println("Maximum value from a list is : "+max);
		
		int maxx = intList.stream().max(Comparator.comparing(Integer :: intValue)).get();
		System.out.println(maxx);
		
		
		//3. Find the duplicate numbers from a given Integer list.==================Asked in Citius Tech
		System.out.println("A Given list is : "+intList);
		Set<Integer> intSet = new HashSet();
		//First Method
		List<Integer> duplicateList = intList.stream().filter(i -> intSet.add(i)).toList().stream().distinct().toList();
		//Second Method
		//Set<Integer> duplicateSet = intList.stream().filter(n -> ! intSet.add(n)).collect(Collectors.toSet());
		//Third Method
		//List<Integer> dupList = intList.stream().filter(e -> !intSet.add(e)).toList();
		//Fourth Method
		Set<Integer> duplicateSet = intList.stream().filter(i -> Collections.frequency(intList, i) > 1).collect(Collectors.toSet());
		
		System.out.println("Duplicate Number in a given list : "+duplicateSet);
				
		Employee emp1 = new Employee("Jyoti", 30, 30000, "HR","Female");
		Employee emp2 = new Employee("Verma", 32, 40000,  "Finance", "Male");
		Employee emp3 = new Employee("Shyam", 50, 25000, "Training", "Male");
		Employee emp4 = new Employee("Arbind", 44, 50000, "Engineering","Male");
		Employee emp5 = new Employee("Jyoti", 31, 450000, "Engineering","Female");
		
		List<Employee> empList = new ArrayList<>();
		
		empList.add(emp1); empList.add(emp2);empList.add(emp3);empList.add(emp4);empList.add(emp5);
		
		//4. Given the list of employee, group them by employee name.=================Asked in Wipro
		System.out.println("Given Employee list is : "+empList);
		Map<String, List<Employee>> map = empList.stream().collect(Collectors.groupingBy(Employee::getName));
		map.forEach((name,empList1) -> System.out.println("Name : "+name +"->"+empList1));
		
		// Convert List to LinkedHashMap where key is Employee name using Stream API
        var linkedHashMap = empList.stream()
            .collect(Collectors.toMap(
                Employee::getName,   // Key: Employee Name
                e -> e,              // Value: Employee Object
                (existing, replacement) -> existing,  // Merge Function (if duplicates exist)
                LinkedHashMap::new   // Preserve insertion order
            ));
        System.out.println(linkedHashMap);
		
		//5. Sorting a Map as per key.===================Asked in CAPGEMINEE
		Map<String, Integer> marksMap = new HashMap();
		marksMap.put("Math", 97);	marksMap.put("Physics", 95);	marksMap.put("Chemistry", 92);	marksMap.put("Languages", 98);
		
		Map<String, Integer> sortedMap = new LinkedHashMap();
		
		marksMap.entrySet().stream()
		.sorted(Map.Entry.<String, Integer>comparingByKey())
		.forEachOrdered(x -> sortedMap.put(x.getKey(), x.getValue()));
		System.out.println("Sorted Map is : "+sortedMap);
		
		//6. Sorting a Map as per value.===================Asked in CAPGEMINEE
		Map<String, String> tempratureMap = new HashMap<>();
		tempratureMap.put("Aurangabad", "20Degree");		tempratureMap.put("Patna", "22Degree");
		tempratureMap.put("Gaya", "19Degree");		tempratureMap.put("Muzzafarpur", "25Degree");
		
		Map<String, String> sortedTempratureMap = new LinkedHashMap<>();
		
		tempratureMap.entrySet().stream()
		.sorted(Map.Entry.<String,String>comparingByValue())
		.forEachOrdered(x -> sortedTempratureMap.put(x.getKey(), x.getValue()));
		System.out.println("Sorted Map as per value : "+sortedTempratureMap);
		
		// 6. Sort a Map as per key in descending order.
		Map<String, Integer> descendingOrderMap = new LinkedHashMap<>();
		
		marksMap.entrySet().stream().sorted(Map.Entry.<String,Integer>comparingByKey().reversed()).
		forEachOrdered(m -> descendingOrderMap.put(m.getKey(), m.getValue()));
		System.out.println("A map is in descending order by key :"+descendingOrderMap);
		
		//6. Write the java 8 code for printing the odd and even from a list in one java code statement.
		List<Integer> numbers = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
        //First method
		numbers.stream().collect(Collectors.partitioningBy(n -> n % 2 == 0)) // partitioningBy(n -> n % 2 == 0): Groups the numbers into two lists based on whether the condition (n % 2 == 0) is true (even) or false (odd). This groups the elements of the list into a Map<Boolean, List<Integer>>.
        .forEach((keyAsboolean, valueAslist) -> System.out.println((keyAsboolean ? "Even: " : "Odd: ") + valueAslist) ); //forEach(): Iterates over the partitioned map and prints the results. The key keyAsboolean determines whether it's the "Even" group or the "Odd" group.
        //Second method
		numbers.stream().collect(Collectors.groupingBy(i -> i%2 == 0)).
		forEach((k,v) -> System.out.println((k ? "Even : " : "Odd :")+v));
		
        
        
		//7. Write the java 8 code for printing the odd and even from a list.
		
		Integer[] intArray2 = new Integer[] {2,3,4,8,15,23,20,89};
		System.out.println("Given array is : "+Arrays.toString(intArray2));
		
		System.out.println("Even numbers are : ");
		
		Arrays.stream(intArray2).filter(i -> i%2 == 0).forEach(System.out :: println);
		
        System.out.println("Odd numbers are : ");
		
		Arrays.stream(intArray2).filter(i -> i%2 != 0).forEach(System.out :: println);
		
		//8. Write the java 8 code to remove the duplicate number from a array like {20,20,30,30,45,96,2,}
		
		//7. Write the java 8 code to remove the duplicate number from a array like {20,20,30,30,45,96,2,}
		
		Integer[] intArray5 = new Integer[] {20,20,12,23,56,45,45,2,2};
		List<Integer> integerrList = Arrays.asList(intArray5);
		Set<Integer> setInteger = new HashSet<>();
				
		Set<Integer> setInt = integerrList.stream().filter(j -> !setInteger.add(j)).collect(Collectors.toSet());
				
		System.out.println("Duplicate number are eliminated: "+setInteger.toString());
		System.out.println("Duplicate number are "+setInt);
				
		// 8. Character repeat count
		String inputString = "hriday Kumar srivastava";
		Map<Character, Long> charCountMap = inputString.chars()//returns an IntStream of characters from the string.
												.mapToObj(c -> (char)c)//converts each integer value to a character.
												.filter(Character :: isLetterOrDigit)//filters out non-alphanumeric characters
												.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));//groups the characters by identity (i.e., themselves) and counts their occurrences.
		
		
				
		//9.  Display character count
		charCountMap.forEach((character, count) -> System.out.println(character + ": " + count));
		
		//10. To count the occurrences of words from a given string 
		String sentence = "hello world hello java world";
        Map<String, Long> wordCounts = Arrays.stream(sentence.split("\\s+"))// splits the string into words based on whitespace characters (such as space, tab, or newline), creating an array of words.
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));

        // Print word counts
        wordCounts.forEach((word, count) ->
                System.out.println(word + ": " + count));
		        
		// 10. Reverse the order of the array using streams       
		int[] array = {12, 13, 14};
		
		  int[] reversedArray = Arrays.stream(array) .boxed() // convert int to Integer
		  .sorted(Collections.reverseOrder()) // sort in reverse order
		  .mapToInt(Integer::intValue).toArray(); // convert back to int .toArray();
		 
		// 11. Sort the list using Stream API
		        integerrList.stream()
		               .sorted()
		               .forEach(System.out::println);
		        
		        //Second method
		        integerrList.stream().mapToInt(Integer :: intValue).sorted().boxed().toList().forEach(System.out::println);
		        
	   // 12. Sort the list in descending order using Stream API
		        List<Integer> sortedDescending = integerrList.stream()
		                                                .sorted(Comparator.reverseOrder())
		                                                .collect(Collectors.toList());

		        // Print the sorted list
		        System.out.println("Sorted list in descending order:");
		        sortedDescending.forEach(System.out::println);
		        
		// 11. Difference between Map & FlatMap
		List<Customer>  customerList = new ArrayList<>();
		Customer customer1 = new Customer(1, "Hriday", "hri@gamil.com", Arrays.asList("111111222","2221111"));
		Customer customer2 = new Customer(1, "Kumar", "kumar@gamil.com", Arrays.asList("4444222","55551111"));
		Customer customer3 = new Customer(1, "Pammo", "pammo@gamil.com", Arrays.asList("8881222","222115511"));
		
		customerList.add(customer1);customerList.add(customer2);customerList.add(customer3);
		// map()  : need to find here phone numbers of each customer in a list like [ [], [], [] ]
		List<List<String>> phoneNumberCustomerWise = customerList.stream()
				                                     .map(customer -> customer.getPhoneNumbers())
				                                     .collect(Collectors.toList());
		System.out.println("PhoneNumbers are : "+phoneNumberCustomerWise);
		
		//flatMap() : need to combine all phone numbers of Customers in a single list. like [, , , , , , , ,]
		List<String> phoneNumbers = customerList.stream()
				    				.flatMap(customer -> customer.getPhoneNumbers().stream())
									.collect(Collectors.toList());
		System.out.println("Phone Number using flatMap"+"("+") method : "+phoneNumbers);
		
		//===========================2=======0===========2========4============
		// Sort below intArray into descending order and remove 3 and get into an Array using Stream api.
		int[] intArrr = {0,0,1,1,0,0,3};
		int[] actualArray = Arrays.stream(intArrr).boxed().sorted(Comparator.reverseOrder())
		.skip(1).mapToInt(Integer::intValue).toArray();
		System.out.println("Sorted Array after removing 3 : "+Arrays.toString(actualArray));
		
		//We have List of Employee object. group by salary(in descending order) and as per department = Engineering  
		Map<Double, List<Employee>> descedingSalaryMap = empList.stream()
                .filter(e -> "Engineering".equals(e.getDepartment())) // Filter by department "Engineering"
                .collect(Collectors.groupingBy(
                        Employee::getSalary,        // Group by salary
                        () -> new TreeMap<>(Comparator.reverseOrder()),  // Use TreeMap to sort keys in descending order
                        Collectors.toList()         // Collect as List
                    ));
		
		// Print the result
		descedingSalaryMap.forEach((salary, empListt) -> {
            System.out.println("Salary: " + salary);
            empListt.forEach(System.out::println);
        });
		
		
		//12. toMap(). Find the  map of employee as per key "EmployeeSalary" and value "EmployeeName" and whose salary is less<400000.
		Map<Double, String> empMap = empList.stream().filter(emp -> emp.getSalary()<= 40000)
									  .collect(Collectors.toMap(Employee :: getSalary, Employee :: getName, (name1, name2) -> name1));
		System.out.println("Use of toMap() method of Collectors "+empMap);
		
		//13. Get all department in a list from Employee list.
		List<String> departmentList = empList.stream().map(Employee :: getDepartment).distinct().toList();
		System.out.println("All departments are of Employees : "+departmentList);
		
		//14. Sort the employee list as per salary.
		List<Employee> sortedListAsPerSalary = empList.stream().sorted(Comparator.comparing(Employee :: getSalary).reversed()).toList();
		System.out.println("Sorted List as per Salary : "+sortedListAsPerSalary);
		
		//15. Find the max and min paid salary employee from Employee list.
		Employee highestPaidEmployee = empList.stream().max(Comparator.comparing(Employee :: getSalary)).get();
		System.out.println("Highest Paid Salary Employee is : "+highestPaidEmployee);
		Employee minimumPaidEmployee = empList.stream().min(Comparator.comparing(Employee :: getSalary)).get();
		System.out.println("Minimum paid salary employee is : "+minimumPaidEmployee);
		
		//16. gender(key) -> [names](value)
		Map<String, List<String>> employeeAsPerGender = empList.stream()
														.collect(Collectors.groupingBy(Employee :: getGender, Collectors.mapping(Employee :: getName, Collectors.toList())));
		System.out.println("All employees as per gender : "+employeeAsPerGender);
		
		//17. Find the total number of employee as per male and female.
		Map<String, Long> countOfEmpAsPerGender = empList.stream().collect(Collectors.groupingBy(Employee :: getGender, Collectors.counting()));
		System.out.println("Count of employees as per Gender : "+countOfEmpAsPerGender);
		
		//19. Find top 2 paid employee. limit(Long MaxSize);
		List<Employee> top2Employees = empList.stream().sorted(Comparator.comparing(Employee :: getSalary).reversed())
										.limit(2).toList();
		System.out.println("Most paid 2 Employees are : "+top2Employees);
		
		LocalDate date = LocalDate.now();System.out.println("Date is : "+date);
		LocalTime time = LocalTime.now();System.out.println("Time is : "+time);
		LocalDateTime dateTime = LocalDateTime.now();System.out.println(dateTime);
		
		LocalDateTime dateAndTime = LocalDateTime.of(1987, 8, 07, 9, 9);
		System.out.println("My birthdate is : "+dateAndTime);
		
		Period period = Period.between(LocalDate.of(1987, 8, 7), LocalDate.now());
		System.out.printf("I am old : %d Years, %d Months and %d Days", period.getYears(), period.getMonths(), period.getDays());
		System.out.println("\n2000 is leap year or not ? "+Year.of(2000).isLeap());
		
		System.out.println("My TimeZone is : "+ZoneId.systemDefault());
		
		ZoneId america = ZoneId.of("America/Los_Angeles");
		System.out.println("Current Time in America : "+ZonedDateTime.now(america));
		
		System.out.println("What would be date after Six month : "+LocalDate.now().plusMonths(6));
		
		//20. Map1 and Map2 are there. First need to sort map2 by value then need to map map1 key with map2 value. 
		
		Map<Integer, String> map1 = new LinkedHashMap<>(); map1.put(222, "Hriday"); map1.put(333, "Kumar");
		Map<String, Double>  map2 = new LinkedHashMap<>(); map2.put("Sanjeet", 88888.0); map2.put("Pal", 50000.0);
		
		Map<String, Double> sortMap3 = map2.entrySet().stream().sorted(Map.Entry.<String, Double>comparingByValue().reversed())
				.collect(Collectors.toMap(Map.Entry :: getKey, Map.Entry :: getValue , (m1, m2) -> m1, LinkedHashMap :: new));
		//m1 is the value already associated with the key. m2 is the new value trying to be associated with the same key. (m1, m2) -> m1 means "keep the first value (m1) and ignore the second (m2)."
		Iterator<Map.Entry<String, Double>> itr = sortMap3.entrySet().iterator();
		
		Map<Integer, Double> map3 = map1.entrySet().stream()
				.collect(Collectors.toMap(Map.Entry :: getKey, entry -> itr.hasNext() ? itr.next().getValue() : null,
						(m4, m5) -> m4, LinkedHashMap :: new));
		System.out.println(map3);
	        
	        int[] intArr = {2, 2, 2, 3, 5, 7, 9, 9};

	        // Use Stream API to find non-duplicate elements
	        List<Integer> nonDuplicates = IntStream.of(intArr)
	                .boxed()
	                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())) // Count occurrences
	                .entrySet()
	                .stream()
	                .filter(entry -> entry.getValue() == 1) // Keep only elements with count = 1
	                .map(Map.Entry::getKey) // Extract the key (the number)
	                .toList();

	        // Print the result
	        System.out.println(nonDuplicates);
	        
	        //Second Approach :
			var dupArr = Arrays.stream(intArr).boxed().filter(i -> Collections.frequency(Arrays.stream(intArr).boxed().toList(), i) < 2 ).mapToInt(Integer :: intValue).toArray();
			System.out.println(Arrays.toString(dupArr));
			//3rd Approach
			var nonRepeated = Arrays.stream(intArr)
	                .filter(i -> Arrays.stream(intArr).filter(x -> x == i).count() == 1)
	                .toArray();
			System.out.println(Arrays.toString(nonRepeated));
	        
	        String[] input = {"apple", "banana", "orange", "apricot"}; //{a:"appleapricot", b:"banana", o:"orange"}


	        // Use Stream API to group strings by their first character
	        Map<Character, String> joiningValues = Arrays.stream(input)
	                .collect(Collectors.groupingBy(
	                        str -> str.charAt(0),               // Group by the first character
	                        LinkedHashMap::new,                // Preserve insertion order
	                        Collectors.joining()               // Concatenate strings with the same key
	                ));
		
		
	      //21. Find the longest string from a string array.
			String[] strArr = {"java", "computer", "serialization"};
			String longestWord = Arrays.stream(strArr).reduce((word1, word2)-> word1.length() > word2.length() ? word1:word2).get();
			System.out.println(longestWord);
			
			//22.   those has started with 1. int[] intArr1 = {12,23,18,20,15};
			int[] intArr1 = {12,23,18,20,15};
			int[] startWithOne = Arrays.stream(intArr1).boxed().map(c -> c+"")
					.filter(s -> s.startsWith("1")).mapToInt(Integer :: parseInt).toArray();
			System.out.println(startWithOne);
			//Second Method
			var va = Arrays.stream(intArr1).mapToObj(i -> ""+i).filter(i -> i.startsWith("1")).mapToInt(Integer :: parseInt).toArray();
			System.out.println(Arrays.toString(va));
			
			//23. String[] str= {1,2,3,4}; print like 1-2-3-4
			String results = String.join("-", strArr);
			System.out.println(results);
			
			//To print number from 1 to 12 using stream 
			IntStream.rangeClosed(1, 12).forEach(System.out :: print);
			Stream.iterate(1, n -> n + 1).limit(12).forEach(System.out::print);  // Limit the stream to 10 elements
			
			// Input: ABC 		Output:	ABC ACB BAC BCA CAB CBA
			
			List<String> stList = Arrays.asList("Hriday","Kumar","Sinha","Shrivastava");
			System.out.println(String.join(" ", stList));
			
			String sub1 = "Hriday";
			String sub2 = sub1.substring(0, 1);
			System.out.println(sub1.substring(0, 0));
			
			//24 . reverse a String using Stream API
			String str = "Hriday";

	        String reversed = IntStream.range(0, str.length()) // Generate an IntStream of indices
	                .mapToObj(i -> str.charAt(str.length() - (1+i))) // Get characters in reverse order
	                .map(String::valueOf) // Convert each Character to String
	                .collect(Collectors.joining()); // Join all Strings to form the reversed string

	        System.out.println("Reversed String: " + reversed);
	        
	        //25. Calculate factorial using Stream API
	        int number = 5;
	        int factorial = IntStream.rangeClosed(1, number) // Generate a stream from 1 to number
	                .reduce(1, (a, b) -> a * b); // (a, b) -> a * b is the accumulator function. It specifies how two elements from the stream should be combined. In this case, the elements are multiplied.
	        //Second method
	        factorial = IntStream.rangeClosed(1, 6).reduce((a,b) -> a*b).getAsInt();
	        System.out.println("Factorial of " + number + " is: " + factorial);
	        
	     //26. Generate Fibonacci series using Stream API
	        int limit = 8; // Number of Fibonacci terms to generate

	        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]}) // Create the stream
	                .limit(limit) // Limit the stream to the desired number of terms
	                .map(arr -> arr[0]) // Extract the first number of the pair
	                .forEach(System.out::println); // Print each Fibonacci number   // new int[]{0, 1}: यह स्ट्रीम की शुरुआत कर रहा है। मतलब पहला एलिमेंट है {0, 1} — यानी कि फाइबोनैचि सीरीज की शुरुआती दो संख्याएं।   arr -> new int[]{arr[1], arr[0] + arr[1]}: यह एक Lambda expression है जो हर अगले एलिमेंट को परिभाषित करता है। arr[1]: इसका मतलब है पिछले जोड़े की दूसरी संख्या को अब पहले स्थान पर ले आओ। arr[0] + arr[1]: इसका मतलब है पहले और दूसरे स्थान की संख्याओं को जोड़कर नया दूसरा एलिमेंट बनाओ।
	        
	        //Second Method
	        Stream.iterate(new int[]{0, 1}, arr -> new int[]{arr[1], arr[0] + arr[1]}) // Create the stream
            .limit(limit) // Limit the stream to the desired number of terms
            .forEach(arr -> System.out.print(arr[0]+"\t")); // Print each Fibonacci number
			
			

	        //Sort list in ascending order ignoring the negative sign. input - [1,4,-2,-9,5], output - [-9, 5, 4, -2, 1]
	        List<Integer> listInt = Arrays.asList(1,4,-2,-9,5);
			List<Integer> sortIntL = listInt.stream().sorted(Comparator.comparing(i -> Math.abs((int) i)).reversed()).toList();
			System.out.println(sortIntL);
			//Or,
			sortIntL = listInt.stream().sorted(Comparator.comparing(i -> Math.abs((int) i)).reversed()).toList();
			System.out.println(sortIntL);
			
			// Merge arrays into a single array using Stream API
			//First Method
			int[] arr1 = {1, 2}; int[] arr2 = {3, 4, 5}; int[] arr3 = {6, 7, 8, 9}; int[] arr4 = {10, 11, 12, 13};
	        int[] mergedArray = Stream.of(arr1, arr2, arr3, arr4)
	                                  .flatMapToInt(Arrays::stream)
	                                  .toArray();
	        System.out.println(Arrays.toString(mergedArray));
	        //Second method
	        mergedArray = IntStream.concat(IntStream.concat(Arrays.stream(arr1), Arrays.stream(arr2)), IntStream.concat(Arrays.stream(arr3), Arrays.stream(arr4))).toArray();
	        System.out.println(Arrays.toString(mergedArray));
	        // Merge into single List
	        List<Integer> l1 = Arrays.asList(1,2);List<Integer> l2 = Arrays.asList(3,4);List<Integer> l3 = Arrays.asList(5,6);
	        List<Integer> l4 = Stream.of(l1,l2,l3).flatMap(List :: stream).toList();
	        System.out.println(l4);
	        
	        //Find the common elements from 2 arrays.
	        int[] arr11 = {1,2,3,4,5};
			int[] arr12 = {4,5,6,7,8};
			
			int[] arr13 = Arrays.stream(arr11).filter(i -> Arrays.stream(arr12).anyMatch(elm -> elm == i)).toArray();
			System.out.println(Arrays.toString(arr13));
			
			//Reverse an array using java8 or Stream.
			int[] actual = {1,2,3,4,5};
			IntStream.range(0, actual.length/2)
								.forEach(i -> {
								int temp = actual[i];
								actual[i] = actual[actual.length-1 -i ];
								actual[actual.length-1 -i] = temp;
								});
			System.out.println(Arrays.toString(actual));
			
			var r = "hriday";
			System.out.println(r);
		
			//String compressor aabbbccaaa = a2b3c2a3
			String inputt = "aabbbccaaa";
	        String output = IntStream.range(0, inputt.length())
	                .filter(i -> i == 0 || inputt.charAt(i) != inputt.charAt(i - 1)) // Start of a new group
	                .mapToObj(i -> {
	                    char ch = inputt.charAt(i);
	                    int count = 1;

	                    // Count consecutive characters
	                    while (i + count < inputt.length() && inputt.charAt(i + count) == ch) {
	                        count++;
	                    }

	                    return ch + String.valueOf(count);
	                })
	                .collect(Collectors.joining());
	        System.out.println(output); // a2b3c2a3
	        
	        // Find the first non-repeating character from a given string.
	        String givenstr = "throot";
	        var nonRepeating = givenstr.chars().mapToObj(ch -> (char)ch)
	        		.collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
	        		.entrySet().stream().filter(entry -> entry.getValue() == 1).map(Map.Entry :: getKey).findFirst().get();
	        System.out.println(nonRepeating);
	        
	        //Last non-repeatitive char
	        nonRepeating = givenstr.chars().mapToObj(ch -> (char)ch).collect(Collectors.groupingBy(Function.identity(), LinkedHashMap :: new, Collectors.counting()))
			.entrySet().stream().filter(entry -> entry.getValue() == 1).reduce((first,second) -> second).map(Map.Entry :: getKey).get();
	        System.out.println(nonRepeating);
	        
	        //Other way of using Flat map 
	        List<List<String>> departEmps = Arrays.asList(Arrays.asList("Hri", "Day"), 
	        		Arrays.asList("Ku", "Mar"), Arrays.asList("Sri", "Vastava"));
	        System.out.println(departEmps);
	        var ftMap = departEmps.stream().flatMap(List :: stream).toList();
	        System.out.println(ftMap);
	
	        //Move the zero right side  int[] intArray = {1,2,-3,0,0,8,-5,0,4}; like [1, 2, -3, 8, -5, 4, 0, 0, 0] without changing the position of non zeros
	        int[] intArry = {1,2,-3,0,0,8,-5,0,4};
	        var moved = Stream.of(Arrays.stream(intArry).filter(i -> i != 0).toArray(), Arrays.stream(intArry).filter(i -> i == 0).toArray()).flatMapToInt(Arrays :: stream).toArray();
	        System.out.println(Arrays.toString(moved));
	        
	      //Write a Java program using stream api to check if a vowel is present in a string or not ?
	        
	        //1st Approach
	        String inputtt = "ChatGPT"; 

	        boolean containsVowel = inputtt.toLowerCase()
	                                     .chars() // character stream बनाता है
	                                     .mapToObj(c -> (char) c) // int को char में बदला
	                                     .anyMatch(c -> "aeiou".indexOf(c) != -1); // यह c को aeiou में ढूँढता है। अगर मिल जाता है, तो उसका index (स्थान) return करता है, और अगर नहीं मिलता तो -1 return करता है।

	        if (containsVowel) {
	            System.out.println("String contains at least one vowel.");
	        } else {
	            System.out.println("String does not contain any vowel.");
	        }
	        
	        //Second Approach
	        List vowNonVow = inputtt.chars().mapToObj(i -> (char)i).filter(i -> "AaEeIiOoUu".contains(""+i)).toList().stream().toList();
	        if(vowNonVow.isEmpty())System.out.println("String contains vowel");
	        else System.out.println("String doesn't contain vowel");
	        
	        
	        //How we convert immutable collection like list, set into mutable or modifiable collection.
	        List<String> l8 = List.of("A","B","C");
			System.out.println(l8);
			List<String> l9 = new ArrayList<>(l8);
			l9.remove(1);
			
			System.out.println(l9);
			
			// As per multiple condition if we need to update employee list and get the  updated list as output.
			List<Employee> updatedList = empList.stream()
				    .peek(emp -> {
				        if (emp.getDepartment().equalsIgnoreCase("Engineering")
				            && emp.getSalary() < 60000 ) {
				            emp.setSalary(emp.getSalary() * 1.10);
				        }
				    })
				    .collect(Collectors.toList());
			System.out.println(updatedList);
			
			//Or,
			
			updatedList = empList.stream()
				    .map(emp -> {
				        if (emp.getDepartment().equalsIgnoreCase("Engineering") 
				                && emp.getSalary() < 600000) {
				            emp.setSalary(emp.getSalary() + emp.getSalary() * 10 / 100);
				        }
				        return emp;   // ✅ THIS IS IMPORTANT
				    })
				    .toList();
			
			// Find the number of vowels and consonants from a String
			String countVC = "My name is Hriday Kumar";

			countVC.toLowerCase()
			       .chars()
			       .mapToObj(ch -> (char) ch)
			       .filter(Character::isLetter)
			       .collect(Collectors.groupingBy(c -> "aeiou".indexOf(c) >= 0))
			       .forEach((isVowel, letters) -> {
			           if (isVowel)
			               System.out.println("Number of Vowels: " + letters.size() + " " + letters);
			           else
			               System.out.println("Number of Consonants: " + letters.size() + " " + letters);
			       });
			
			
			//20. Map1 and Map2 are there. Need to map map1 key with map2 value.
			Map<String, Integer> m1 = new LinkedHashMap<>(); m1.put("Hriday", 12); m1.put("Anaya", 20); m1.put("Vinay", 39);
			Map<Integer, String> m2 = new LinkedHashMap<>(); m2.put(1, "Patna");m2.put(2, "Delhi");m2.put(3, "Mumbai");
			
					
			Map<String, String> result =
					IntStream.range(0, Math.min(m1.size(), m2.size()))
					    .boxed()
					    .collect(Collectors.toMap(
					        i -> new ArrayList<>(m1.keySet()).get(i),   // Map1 Key
					        i -> new ArrayList<>(m2.values()).get(i),   // Map2 Value
					        (a,b)->a,
					        LinkedHashMap::new
					    ));
			
			System.out.println(result);
			
			Map<String, String> sortedResult =
					IntStream.range(0, m1.size())
					    .boxed()
					    .collect(Collectors.toMap(
					        i -> m1.keySet().stream().sorted().toList().get(i),
					        i -> m2.values().stream().sorted().toList().get(i),
					        (a,b)->b,
					        LinkedHashMap::new
					    ));

					System.out.println(sortedResult);
					
			// Find the sum of 2D array
			int[][] arrOf2D = { {2, 3}, {3, 4}, {4, 5} };
	
			int sum = Arrays.stream(arrOf2D)          // Stream of int[]
            .flatMapToInt(Arrays::stream)  // flatten to IntStream
            .sum();           // sum all elements

			System.out.println("Sum of all elements: " + sum);
			
			
			// String s= "hello world java";//			output =HelloWorldJava
			String s = "hello world java";
			String resultt = Stream.of(s.split(" "))
	                .map(w -> Character.toUpperCase(w.charAt(0)) + w.substring(1))
	                .collect(Collectors.joining());

	        System.out.println(resultt);
	        
	        // Find the highest salary paid employee as per departments.
	        Map<String, Employee> deptHighSalary =
	        	    empList.stream()
	        	        .collect(Collectors.groupingBy(
	        	            Employee::getDepartment,
	        	            Collectors.collectingAndThen(
	        	                Collectors.maxBy(Comparator.comparing(Employee::getSalary)),
	        	                Optional::get
	        	            )
	        	        ));
	        
	        System.out.println(deptHighSalary);

	        //Find the highest salary as per each departments
	        Map<String, Double> resultttt =
	        		empList.stream()
	        	        .collect(Collectors.groupingBy(
	        	            Employee::getDepartment,
	        	            Collectors.mapping(
	        	                Employee::getSalary,
	        	                Collectors.collectingAndThen(
	        	                    Collectors.maxBy(Double::compare),
	        	                    Optional::get
	        	                )
	        	            )
	        	        ));
	        
	        System.out.println(resultttt);
	        
	        
	      // Find sorted employee list.
	       // List<Map<String, Employee>> listEmpMaps;
	        
	       // Find the 3rd highest number from below string String value = "56834901"; // "c - '0' converts a numeric character into its integer value by subtracting the ASCII value of '0'."
	        String value = "56834901";
	        var thirdLargesNumber = value.chars()
	        								.map(c -> (int)c) // c actually digit नहीं है c उसका ASCII value है. 👉 '0' भी एक character है  इसका ASCII value = 48
	        								.boxed()
	        								.distinct().sorted(Comparator.comparing(Integer::intValue).reversed())
	        								.skip(2).findFirst().get();
	        System.out.println(thirdLargesNumber);
	        	// Instead of map we can use mapToObj(Character :: getNumericValue)							
	        							


	}
	
}
