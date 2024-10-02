package diningphilosophers;

public class ChopStick {

    private static int stickCount = 0;
    private boolean iAmFree = true;
    private final int myNumber;

    public ChopStick() {
        myNumber = ++stickCount;
    }

    synchronized public boolean take() throws InterruptedException {
        if (!iAmFree) {
            wait(200);
            if (!iAmFree) // Toujours pas libre, on abandonne
            {
                return false; // Echec
            }
        }
        iAmFree = false;
        // Pas utile de faire notifyAll ici, personne n'attend qu'elle soit occupée
        return true; // Succès
    }

    synchronized public void release() {
        iAmFree = true;
        notifyAll();
        System.out.println("Baguette " + myNumber + " relachée ");
    }

    @Override
    public String toString() {
        return "Baguette#" + myNumber;
    }
}