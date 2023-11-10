class Node:
    def __init__(self, data, question):
        self.data = data
        self.question = question
        self.left = None
        self.right = None


class BinaryTreeQuiz:
    def __init__(self):
        self.root = None

    def insert(self, data, question):
        self.root = self._insert_rec(self.root, data, question)

    def _insert_rec(self, root, data, question):
        if root is None:
            return Node(data, question)

        if data < root.data:
            root.left = self._insert_rec(root.left, data, question)
        elif data > root.data:
            root.right = self._insert_rec(root.right, data, question)

        return root

    def query_tree(self):
        current_node = self.root

        while current_node.left or current_node.right:
            print(current_node.question)
            user_input = input().strip().lower()

            if user_input == "y" and current_node.left:
                current_node = current_node.left
            elif user_input == "n" and current_node.right:
                current_node = current_node.right
            else:
                print("Invalid input. Please enter 'y' or 'n'.")

        return "Final Answer: " + current_node.question

    def read_input_from_file(self, file_name):
        with open(file_name, 'r') as file:
            lines = file.readlines()
            for line in lines[2:]:
                data, question = line.strip().split(' ', 1)
                self.insert(int(data), question)

    def display_tree(self):
        while True:
            print("Tree Traversal Options:")
            print("1 - In-order Traversal")
            print("2 - Pre-order Traversal")
            print("3 - Post-order Traversal")
            print("4 - Return to Main Menu")
            choice = input("Enter your choice: ").strip().lower()

            if choice == "1":
                print("In-order Traversal:")
                self._in_order(self.root)
            elif choice == "2":
                print("Pre-order Traversal:")
                self._pre_order(self.root)
            elif choice == "3":
                print("Post-order Traversal:")
                self._post_order(self.root)
            elif choice == "4":
                return
            else:
                print("Invalid choice")

    def _in_order(self, node):
        if node is None:
            return
        self._in_order(node.left)
        print(node.data, node.question)
        self._in_order(node.right)

    def _pre_order(self, node):
        if node is None:
            return
        print(node.data, node.question)
        self._pre_order(node.left)
        self._pre_order(node.right)

    def _post_order(self, node):
        if node is None:
            return
        self._post_order(node.left)
        self._post_order(node.right)
        print(node.data, node.question)


if __name__ == "__main__":
    bt = BinaryTreeQuiz()
    file_name = "cargame.txt"
    bt.read_input_from_file(file_name)

    while True:
        print("Menu:")
        print("P - Play Quiz")
        print("L - Load Game File")
        print("D - Display Tree")
        print("H - Help")
        print("X - Quit")
        choice = input("Enter your choice: ").strip().lower()

        if choice == "p":
            final_answer = bt.query_tree()
            print(final_answer)
        elif choice == "l":
            print("Enter file path or name")
            file_name = input().strip()
            bt.read_input_from_file(file_name)
        elif choice == "d":
            bt.display_tree()
        elif choice == "h":
            with open(file_name, 'r') as file:
                print(file.readline().strip())
                print(file.readline().strip())
        elif choice == "x":
            break
        else:
            print("Invalid choice")
