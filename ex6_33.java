    class MyName {
        String firstName;
        String lastName;
         
        MyName(String name) {
            String[] list = name.split(" ", 2);
            firstName = list[0];
            lastName = list[1];
        }
     
        MyName(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName  = lastName;
        }
     
        public String toString(){
            return lastName + " " + firstName;
        }
    }
     
    class MyDate {
        int year;
        int month;
        int date;
         
        MyDate(int y, int m, int d) {
            this.year  = y;
            this.month = m;
            this.date  = d;
        }
     
        MyDate(String dateString) {
            String[] list = dateString.split("/", 3);
            this.year  = Integer.parseInt(list[0]);
            this.month = Integer.parseInt(list[1]);
            this.date  = Integer.parseInt(list[2]);
        }
     
        public String toString(){
            return year + "/" + month + "/" + date;
        }
    }
     
    class Person {
        MyName name;
        MyDate birthday;
        String phoneNumber;
        Person[] friendArray;
         
        Person(String name, MyDate birthday, String phone) {
            this.name        = new MyName(name);
            this.birthday    = birthday;
            this.phoneNumber = phone;
        }
     
        Person(String name, int year, int month, int date, String phone) {
            this.name        = new MyName(name);
            this.birthday    = new MyDate(year, month, date);
            this.phoneNumber = phone;
        }
     
        Person(MyName name, int year, int month, int date, String phone) {
            this.name        = name;
            this.birthday    = new MyDate(year, month, date);
            this.phoneNumber = phone;
        }
     
        Person(MyName name, MyDate birthday, String phone) {
            this.name        = name;
            this.birthday    = birthday;
            this.phoneNumber = phone;
        }
     
        public String toString(){
            int i;
            String str;
            str = "NAME: " + name 
                +" (Date of Birth: " + this.birthday + ")\n"
                + "  Number of friends = " + this.friendArray.length + "\n";
            
            for( i = 0; i < this.friendArray.length; i++ ) {
                str = str + "    Name of friend " + i
                    + ": " + this.friendArray[i].name 
                    + " (Date of Birth: " + this.friendArray[i].birthday + ")\n"; 
            }
            return str;
        }
    }
     
         
    class ex6_33 {
        public static void main(String args[]) {
            /* for protection of personal data, pseudo values are used */
         
            Person person1 = new Person("Taro Tokyo"  , 1985,  9, 20, "080-1234-5678");
            Person person2 = new Person(new MyName("Jiro", "Osaka")  , 1981,  6, 18, "090-8765-4321");
            Person person3 = new Person(new MyName("Saburo Aichi"), new MyDate(1981, 12, 23), "090-3333-5555");
            Person person4 = new Person("Shiro Gifu"  , new MyDate("1974/2/3"), "090-2222-1111");
         
            Person[] person1FriendArray = new Person[2]; // Friends are Jiro and Saburo
            Person[] person2FriendArray = new Person[3]; // Friends are Taro, Saburo and Shiro
            Person[] person3FriendArray = new Person[1]; // Friend is Shiro
            Person[] person4FriendArray = new Person[2]; // Friend are Jiro and Saburo
         
            person1FriendArray[0] = person2;
            person1FriendArray[1] = person3;
         
            person2FriendArray[0] = person1;
            person2FriendArray[1] = person3;
            person2FriendArray[2] = person4;
         
            person3FriendArray[0] = person4;
         
            person4FriendArray[0] = person2;
            person4FriendArray[1] = person3;
     
            person1.friendArray = person1FriendArray;
            person2.friendArray = person2FriendArray;
            person3.friendArray = person3FriendArray;
            person4.friendArray = person4FriendArray;
     
            /* print the input data */
            System.out.print(person1);
            System.out.print(person2);
            System.out.print(person3);
            System.out.print(person4);
        }
    }
