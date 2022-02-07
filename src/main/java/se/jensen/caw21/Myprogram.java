package se.jensen.caw21;
import java.util.Scanner;

    public class Myprogram {
        private APIClient myApiClient;

        // Vår konstruktor som skapar ett nytt ApiClient-objekt
        public Myprogram() {

            myApiClient = new APIClient("http://127.0.0.1:8080/api/v1");
        }

        public void start() {
            boolean programRunning = true;

            while (programRunning) {
                System.out.println();
                System.out.println("=========================================");

                System.out.println("Hello!. Welcome to our blog");
                System.out.println("Choose menu");
                System.out.println("1. Add new blog");
                System.out.println("2. Get list of blogs");
                System.out.println("3.Get the blog by id");
                System.out.println("4. Edit a blog from the list by id");
                System.out.println("5.Delete a blog from the list by id");
                System.out.println("6. Exit program");
                System.out.println("=========================================");
                System.out.println();

                int userChoice = getUserInt();

                System.out.println("Menu selection: " + userChoice);

                switch (userChoice) {
                    case 1:
                        addnewblog();
                        break;
                    case 2:
                        viewListOfblogs();
                        break;
                    case 3:
                        viewBlogbyid();
                        break;
                    case 4:
                        editblogbyid();
                        break;
                    case 5:
                        deleteblogbyId();
                        break;
                    case 6:
                        System.out.println("Exit program");
                        programRunning = false;

                }


        }
        }

        public void addnewblog() {

            System.out.println("Enter the  blog id");
            int id = getUserInt();

            System.out.println(" Enter the title of the blog : ");
            String title = getUserString();

            System.out.println("Enter the content of the blog ");
            String content = getUserString();

            Clientblog newblog = new Clientblog(id,title,content);

            boolean success = myApiClient.addclientblog(newblog);

            if (success) {
                System.out.println("blog added!");
            } else {
                System.out.println("Issue adding blog. :(");
            }

        }

        public void viewListOfblogs() {
            Clientblog[] blogs = myApiClient.getclientblogs();

            System.out.println("Blogs");
            System.out.println("-----------------------------------------");

            if (blogs!=null) {
                for (int i = 0; i < blogs.length; i++) {
                    int id = blogs[i].id;
                    String title = blogs[i].title;
                    String content = blogs[i].content;

                    System.out.println("ID : " + id);
                    System.out.println("TITLE : " + title);
                    System.out.println("CONTENT : " + content);

                    //System.out.printf("-> %s (%d/10)\n", title, id,content);
                }
            } else {
                System.out.println("The blog list is empty .");
            }
        }
        public void viewBlogbyid() {
            System.out.println("Enter blog id : ");
            int blogid = getUserInt();
            Clientblog Blog =myApiClient.getclientblogbyid(blogid);
            if(Blog!=null){
                int id=Blog.getId();
                if(blogid==id){
                    String title=Blog.title;
                    String content = Blog.content;
                    System.out.println("Title : "+title);
                    System.out.println("Content : "+content);
                    //System.out.println("-> %s (%d/10)\\n\", title, id,content");
                }
            }

         else {
            System.out.println("Incorrect blog id .");
        }
        }


        public void editblogbyid() {
            System.out.println("Enter blog id : ");
            int blogid =getUserInt();
            Clientblog updateblogbyid=myApiClient.getclientblogbyid(blogid);
            //Clientblog Blog=null;
            if(updateblogbyid!=null){
                int id= updateblogbyid.getId();
                if(blogid==id){
                   //updateblogbyid = myApiClient.editblogbyid(updateblogbyid,blogid);
                    System.out.println("What would you like to update ?");
                    System.out.println("1. Title of the blog ");
                    System.out.println("2.Content of the blog");
                    int choice= getUserInt();
                    if (choice == 1){
                        System.out.println("Enter the title to be updated");
                        String title = getUserString();
                        updateblogbyid.setTitle(title);
                         myApiClient.editblogbyid(updateblogbyid,blogid);
                        System.out.println("Title : " + title);
                    }else if (choice==2){
                        System.out.println("Enter the content to be updated");
                        String content = getUserString();
                        updateblogbyid.setContent(content);
                        myApiClient.editblogbyid(updateblogbyid,blogid);
                        System.out.println("Content : " + content);
                    }
                        else{
                        System.out.println("ID" + blogid + "DOES NOT EXIST");

                        }
                    }

                }
            }
            public void deleteblogbyId() {
                System.out.println("Enter the blog id : ");
                int blogid=getUserInt();
                Clientblog Blog = myApiClient.getclientblogbyid(blogid);
                if(Blog!=null){
                    int id = Blog.getId();
                    if(blogid == id){
                        myApiClient.deleteblogbyid(blogid);
                        System.out.println("The specified blog id is deleted");

                    }
                }
                else {
                    System.out.println("ID" + blogid + "does not exist");
                }

        }

        public String getUserString() {
            Scanner myScanner = new Scanner(System.in);

            String myString;

            while (true) {
                try {
                    System.out.print("> ");
                    myString = myScanner.nextLine();
                    break;
                } catch (Exception e) {
                    System.out.println("Incorrect input");
                }
            }

            return myString;
        }

        public int getUserInt() {
            Scanner myScanner = new Scanner(System.in);

            int myInteger;

            while (true) {
                try {
                    System.out.print("> ");
                    myInteger = Integer.parseInt(myScanner.nextLine());
                    break;
                } catch (Exception e) {
                    //System.out.println("Exception: " + e);
                    System.out.println("Incorrect input");
                }
            }

            return myInteger;
        }
    }

