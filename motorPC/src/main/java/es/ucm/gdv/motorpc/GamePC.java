package es.ucm.gdv.motorpc; //Igual debería ser es.ucm.gdv.interfaces.motorpc

import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;

import javax.swing.JFrame;

import es.ucm.gdv.interfaces.Game;
import es.ucm.gdv.interfaces.GameState;
import es.ucm.gdv.interfaces.Graphics;
import es.ucm.gdv.interfaces.Input;

public class GamePC implements Game, Runnable {

    //Referencias para el patrón Singleton
    private GraphicsPC _graphicsPC;
    private InputPC _inputPC;

    //Atributos para los gráficos
    private BufferStrategy _bs;
    private JFrame _frame;

    private GameState _currentGameState;
    private GameState _nextGameState;

    //Para el hilo
    private volatile boolean _running; //Volatile hace que no revise en memoria

    public GamePC(String windowTitle) {
        _frame = new JFrame(windowTitle);

        // Vamos a usar renderizado activo. No queremos que Swing llame al
        // método repaint() porque el repintado es continuo en cualquier caso.
        _frame.setIgnoreRepaint(true);
    }

    /*
    Inicia la ventana, y adquiere la lógica que se va a utilizar.
    Además, la inicia.
    */
    public void init() {
        _frame.setSize((int)(1080 * 0.5625f), 1080);
        _frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        _frame.setVisible(true);

        // Intentamos crear el buffer strategy con 2 buffers.
        int intentos = 100;
        while (intentos-- > 0) {
            try {
                _frame.createBufferStrategy(2);
                break;
            } catch (Exception e) {
            }
        } // while pidiendo la creación de la buffeStrategy
        if (intentos == 0) {
            System.err.println("No pude crear la BufferStrategy");
            return;
        }
        _bs = _frame.getBufferStrategy();

        //Inicializa los motores
        _graphicsPC = new GraphicsPC(_frame);
        _inputPC = new InputPC(_frame, _graphicsPC);
    }

    //-----------------------------------------------
    //                  Ciclo de juego
    //-----------------------------------------------
    @Override
    public void run() {

        long lastFrameTime = System.nanoTime();
        // Bucle principal
        while (true) {
            long currentTime = System.nanoTime();
            long nanoElapsedTime = currentTime - lastFrameTime;
            lastFrameTime = currentTime;
            double elapsedTime = (double) nanoElapsedTime / 1.0E9;

            if (_nextGameState != null) {
                _currentGameState = _nextGameState;
                _nextGameState = null;
                _currentGameState.init(this);
            }
            //Tick de la lógica
            _currentGameState.tick(elapsedTime);

            // Pintamos el frame con el BufferStrategy
            do {
                do {
                    //Actualizas el graphics que va a usar GraphicsPC
                    java.awt.Graphics2D graphics = (Graphics2D)_bs.getDrawGraphics();
                    _graphicsPC.setGraphics(graphics);
                    try {
                        _currentGameState.clear();
                        _graphicsPC.updateCanvasSize();
                        _currentGameState.render();
                    } finally {
                        graphics.dispose();
                    }
                } while (_bs.contentsRestored());
                _bs.show();
            } while (_bs.contentsLost());
        } // while
    }

    //-----------------------------------------------
    //                   Getters
    //-----------------------------------------------

    /**
     * Devuelve la instancia de game, para tener siempre los graphics e input actualizados
     * @return Instancia del motor de juego
     * */
    @Override
    public Game getGame() {
        return this;
    }

    /**
     * Devuelve la instancia de graphics para poder cargar y pintar imagenes
     * @return Instancia de Graphics contenida en el motor
     * */
    @Override
    public Graphics getGraphics() {
        return _graphicsPC;
    }

    /**
     * Devuelve la instancia de input para poder registrar y recoger input
     * @return Instancia de Input contenida en el motor
     * */
    @Override
    public Input getInput() {
        return _inputPC;
    }

    @Override
    public void setGameState(GameState gameState) {
        _nextGameState = gameState;
    }
}
