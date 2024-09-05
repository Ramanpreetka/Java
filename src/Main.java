import java.util.Scanner;

class OtagoStudentrecordhandle {
    int Otagostudentid;
    String Otagostudentname;
      //create a two variable with constructor function
    OtagoStudentrecordhandle(int id, String name) {
        this.Otagostudentid = id;
        this.Otagostudentname = name;
    }
    // print student id and student name
    @Override
    public String toString() {
        return "Otago student id: " + Otagostudentid + ", Otago student Name: " + Otagostudentname;
    }
}

class RBNode {
    OtagoStudentrecordhandle Otagostudent;
    RBNode parent;
    RBNode left;
    RBNode right;
    int color; // comment section 1 -> Red, 0 -> Black

    public RBNode(OtagoStudentrecordhandle student) {
        this.Otagostudent = student;
    }
}
//red black node tree class created
class Redblacktreestructure {
    private RBNode root;
    private RBNode TNULL;
    // constructor function created
    public Redblacktreestructure() {
        TNULL = new RBNode(null);
        TNULL.color = 0;
        TNULL.left = null;
        TNULL.right = null;
        root = TNULL;
    }



    // Inorder function create to order tree nodes
    private void inorderhelperfnction(RBNode node) {
        if (node != TNULL) {
            inorderhelperfnction(node.left);
            System.out.println(node.Otagostudent);
            inorderhelperfnction(node.right);
        }
    }



    // Search the tree from structure
    public RBNode searchTree(int key) {
        return searchtreetelperstructure(this.root, key);
    }

    // Search the tree from structure
    private RBNode searchtreetelperstructure(RBNode node, int key) {
        if (node == TNULL || key == node.Otagostudent.Otagostudentid) {
            return node;
        }

        if (key < node.Otagostudent.Otagostudentid) {
            return searchtreetelperstructure(node.left, key);
        }
        return searchtreetelperstructure(node.right, key);
    }

    // Next try to balance the tree after deletion of a node
    private void fixdeletefuncton(RBNode x) {
        RBNode s;
        while (x != root && x.color == 0) {
            if (x == x.parent.left) {
                s = x.parent.right;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    leftRotate(x.parent);
                    s = x.parent.right;
                }

                if (s.left.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.right.color == 0) {
                        s.left.color = 0;
                        s.color = 1;
                        rightRotate(s);
                        s = x.parent.right;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.right.color = 0;
                    leftRotate(x.parent);
                    x = root;
                }
            } else {
                s = x.parent.left;
                if (s.color == 1) {
                    s.color = 0;
                    x.parent.color = 1;
                    rightRotate(x.parent);
                    s = x.parent.left;
                }

                if (s.right.color == 0 && s.right.color == 0) {
                    s.color = 1;
                    x = x.parent;
                } else {
                    if (s.left.color == 0) {
                        s.right.color = 0;
                        s.color = 1;
                        leftRotate(s);
                        s = x.parent.left;
                    }

                    s.color = x.parent.color;
                    x.parent.color = 0;
                    s.left.color = 0;
                    rightRotate(x.parent);
                    x = root;
                }
            }
        }
        x.color = 0;
    }
    // red black tree node transplant function
    private void rbtransplantfuncton(RBNode u, RBNode v) {
        if (u.parent == null) {
            root = v;
        } else if (u == u.parent.left) {
            u.parent.left = v;
        } else {
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    private void deletenodehelperfuncton(RBNode node, int key) {
        RBNode z = TNULL;
        RBNode x, y;
        while (node != TNULL) {
            if (node.Otagostudent.Otagostudentid == key) {
                z = node;
            }

            if (node.Otagostudent.Otagostudentid <= key) {
                node = node.right;
            } else {
                node = node.left;
            }
        }

        if (z == TNULL) {
            System.out.println("Couldn't or unable to find key in the tree");
            return;
        }

        y = z;
        int yOriginalColor = y.color;
        if (z.left == TNULL) {
            x = z.right;
            rbtransplantfuncton(z, z.right);
        } else if (z.right == TNULL) {
            x = z.left;
            rbtransplantfuncton(z, z.left);
        } else {
            y = minimum(z.right);
            yOriginalColor = y.color;
            x = y.right;
            if (y.parent == z) {
                x.parent = y;
            } else {
                rbtransplantfuncton(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }

            rbtransplantfuncton(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if (yOriginalColor == 0) {
            fixdeletefuncton(x);
        }
    }

    // Balance the node after insertion
    private void fixInsert(RBNode k) {
        RBNode u;
        while (k.parent.color == 1) {
            if (k.parent == k.parent.parent.right) {
                u = k.parent.parent.left;
                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.left) {
                        k = k.parent;
                        rightRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    leftRotate(k.parent.parent);
                }
            } else {
                u = k.parent.parent.right;

                if (u.color == 1) {
                    u.color = 0;
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    k = k.parent.parent;
                } else {
                    if (k == k.parent.right) {
                        k = k.parent;
                        leftRotate(k);
                    }
                    k.parent.color = 0;
                    k.parent.parent.color = 1;
                    rightRotate(k.parent.parent);
                }
            }
            if (k == root) {
                break;
            }
        }
        root.color = 0;
    }

    private void printhelpernode(RBNode root, String indent, boolean last) {
        if (root != TNULL) {
            System.out.print(indent);
            if (last) {
                System.out.print("R----");
                indent += "   ";
            } else {
                System.out.print("L----");
                indent += "|  ";
            }

            String sColor = root.color == 1 ? "RED" : "BLACK";
            System.out.println(root.Otagostudent + "(" + sColor + ")");
            printhelpernode(root.left, indent, false);
            printhelpernode(root.right, indent, true);
        }
    }

    public RBNode minimum(RBNode node) {
        while (node.left != TNULL) {
            node = node.left;
        }
        return node;
    }

    public RBNode maximum(RBNode node) {
        while (node.right != TNULL) {
            node = node.right;
        }
        return node;
    }

    public void leftRotate(RBNode x) {
        RBNode y = x.right;
        x.right = y.left;
        if (y.left != TNULL) {
            y.left.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.left) {
            x.parent.left = y;
        } else {
            x.parent.right = y;
        }
        y.left = x;
        x.parent = y;
    }

    public void rightRotate(RBNode x) {
        RBNode y = x.left;
        x.left = y.right;
        if (y.right != TNULL) {
            y.right.parent = x;
        }
        y.parent = x.parent;
        if (x.parent == null) {
            this.root = y;
        } else if (x == x.parent.right) {
            x.parent.right = y;
        } else {
            x.parent.left = y;
        }
        y.right = x;
        x.parent = y;
    }

    public void insert(OtagoStudentrecordhandle student) {
        RBNode node = new RBNode(student);
        node.parent = null;
        node.Otagostudent = student;
        node.left = TNULL;
        node.right = TNULL;
        node.color = 1; // New node must be red

        RBNode y = null;
        RBNode x = this.root;

        while (x != TNULL) {
            y = x;
            if (node.Otagostudent.Otagostudentid < x.Otagostudent.Otagostudentid) {
                x = x.left;
            } else {
                x = x.right;
            }
        }

        node.parent = y;
        if (y == null) {
            root = node;
        } else if (node.Otagostudent.Otagostudentid < y.Otagostudent.Otagostudentid) {
            y.left = node;
        } else {
            y.right = node;
        }

        if (node.parent == null) {
            node.color = 0;
            return;
        }

        if (node.parent.parent == null) {
            return;
        }

        fixInsert(node);
    }
    public void deletenodefuncton(int studentId) {
        deletenodehelperfuncton(this.root, studentId);
    }

    public void printtreefunct() {
        printhelpernode(this.root, "", true);
    }

    // Find the minimum student (smallest ID)
    public OtagoStudentrecordhandle findMin() {
        RBNode node = minimum(root);
        return node.Otagostudent;
    }

    // Find the maximum student (largest ID)
    public OtagoStudentrecordhandle findMax() {
        RBNode node = maximum(root);
        return node.Otagostudent;
    }

    // Count total number of students
    public int countnumofodes(RBNode node) {
        if (node == TNULL) {
            return 0;
        }
        return 1 + countnumofodes(node.left) + countnumofodes(node.right);
    }

    public int gettotalotagostudents() {
        return countnumofodes(root);
    }

    // Inorder traversal to sort
    public void sort() {
        inorderhelperfnction(this.root);
    }
}

public class Main {
    public static void main(String[] args) {
        Redblacktreestructure rbt = new Redblacktreestructure();
        Scanner scanner = new Scanner(System.in);
        System.out.println("\n ************************Welcome to Otago Student Record Management App ***************************");
        System.out.println("\n Choose any Option but first insert student record:");
        while (true) {

            // System Print.
            System.out.println("\n Choose any Option now");
            System.out.println("1. Insert student ID and name ");
            System.out.println("2. Delete added student  ");
            System.out.println("3. Search any student with id");
            System.out.println("4. Sort all students record");
            System.out.println("5. IF find student with minimum ID");
            System.out.println("6. IF find student with maximum ID");
            System.out.println("7. Count Total Students in record");
            System.out.println("8. IF print the tree");
            System.out.println("9. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    System.out.print("Enter Otago Student ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine(); // consume the newline
                    System.out.print("Enter Otago Student Name: ");
                    String name = scanner.nextLine();
                    rbt.insert(new OtagoStudentrecordhandle(id, name));
                    break;

                case 2:
                    System.out.print("Enter Otago Student ID to delete: ");
                    id = scanner.nextInt();
                    rbt.deletenodefuncton(id);
                    System.out.println("Otago Student record deleted");
                    break;

                case 3:
                    System.out.print("Enter Otago Student ID to be search: ");
                    id = scanner.nextInt();
                    RBNode node = rbt.searchTree(id);
                    if (node != null && node.Otagostudent != null) {
                        System.out.println("given id Student found: " + node.Otagostudent);
                    } else {
                        System.out.println("given Student id not found.");
                    }
                    break;

                case 4:
                    System.out.println("Otago Students record sorted by ID:");
                    rbt.sort();
                    break;

                case 5:
                    OtagoStudentrecordhandle minStudent = rbt.findMin();
                    System.out.println("Otago Student print with Minimum ID: " + minStudent);
                    break;

                case 6:
                    OtagoStudentrecordhandle maxStudent = rbt.findMax();
                    System.out.println("Otago Student print with Maximum ID: " + maxStudent);
                    break;

                case 7:
                    int totalStudents = rbt.gettotalotagostudents();
                    System.out.println("Otago total students are: " + totalStudents);
                    break;

                case 8:
                    System.out.println("Student record by RED and BLACK Tree Structure:");
                    rbt.printtreefunct();
                    break;

                case 9:
                    System.out.println("Exit from app");
                    scanner.close();
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid option chosen please chose right one");
            }
        }
    }
}
