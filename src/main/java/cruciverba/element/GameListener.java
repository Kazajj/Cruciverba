package cruciverba.element;

public interface GameListener {

    void generateGamePanel();

    void generateDebug();

    void updateGraphic();

    void reset();

    void onTimeExpired();

}
