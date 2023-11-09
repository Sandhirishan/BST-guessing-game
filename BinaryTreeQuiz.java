import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

class Node {
    int data;
    String question;
    Node left;
    Node right;

    public Node(int data, String question) {
        this.data = data;
        this.question = question;
        this.left = null;
        this.right = null;
    }
}

public class BinaryTreeQuiz {
    private Node root;

    public void insert(int data, String question) {
        root = insertRec(root, data, question);
    }

    private Node insertRec(Node root, int data, String question) {
        if (root == null) {
            root = new Node(data, question);
            return root;
        }

        if (data < root.data) {
            root.left = insertRec(root.left, data, question);
        } else if (data > root.data) {
            root.right = insertRec(root.right, data, question);
        }

        return root;
    }

    public String queryTree(Scanner scanner) {
        Node currentNode = root;

        while (currentNode.left != null || currentNode.right != null) {
            System.out.println(currentNode.question);
            String userInput = scanner.nextLine().trim().toLowerCase();

            if (userInput.equals("yes") && currentNode.left != null) {
                currentNode = currentNode.left;
            } else if (userInput.equals("no") && currentNode.right != null) {
                currentNode = currentNode.right;
            } else {
                System.out.println("Invalid input. Please enter 'Yes' or 'No'.");
            }
        }

        //scanner.close();
        return "Final Answer: " + currentNode.question;
    }

    public void readInputFromFile(String fileName) {
        int lineCounter = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (lineCounter >= 2) {
                    String[] parts = line.split(" ", 2);
                    int data = Integer.parseInt(parts[0]);
                    String question = parts[1];
                    insert(data, question);
                }
                lineCounter++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displaytree(Scanner scanner) {
        while (true) {
            System.out.println("Tree Traversal Options:");
            System.out.println("1 - In-order Traversal");
            System.out.println("2 - Pre-order Traversal");
            System.out.println("3 - Post-order Traversal");
            System.out.println("4 - Return to Main Menu");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toLowerCase();

            switch (choice) {
                case "1":
                    System.out.println("In-order Traversal:");
                    inOrder(root);
                    break;
                case "2":
                    System.out.println("Pre-order Traversal:");
                    preOrder(root);
                    break;
                case "3":
                    System.out.println("Post-order Traversal:");
                    postOrder(root);
                    break;
                case "4":
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void inOrder(Node node) {
        if (node == null)
            return;
        inOrder(node.left);
        System.out.println(node.data + " " + node.question);
        inOrder(node.right);
    }

    private void preOrder(Node node) {
        if (node == null)
            return;
        System.out.println(node.data + " " + node.question);
        preOrder(node.left);
        preOrder(node.right);
    }

    private void postOrder(Node node) {
        if (node == null)
            return;
        postOrder(node.left);
        postOrder(node.right);
        System.out.println(node.data + " " + node.question);
    }



    public static void main(String[] args) {
        BinaryTreeQuiz bt = new BinaryTreeQuiz();
        String filename="game1.txt";
        bt.readInputFromFile("game1.txt");
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Menu:");
            System.out.println("P - Play Quiz");
            System.out.println("L - Load Game File");
            System.out.println("D - Display Tree");
            System.out.println("H - Help");
            System.out.println("X - Quit");
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim().toLowerCase();
            switch (choice) {
                case "p":
                    String finalAnswer = bt.queryTree(scanner);
                    System.out.println(finalAnswer);
                    break;
                case "l":
                    System.out.println("Enter file path or name");
                    filename = scanner.nextLine();
                    bt.readInputFromFile("game1.txt");
                case "d":
                    bt.displaytree(scanner);
                    break;
                case "h":
                    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
                        System.out.println(br.readLine());
                        System.out.println(br.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                default:
                    System.out.println("Invalid choice");
            }
        }

    }
}
