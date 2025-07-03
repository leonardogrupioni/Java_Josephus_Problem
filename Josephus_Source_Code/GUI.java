/**
 * Bibliotecas importadas para uso ao longo do programa
 */
import java.awt.*;
import java.awt.Color;
import java.awt.event.*;
import java.awt.Graphics;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;
import java.util.concurrent.TimeUnit;

/**
 * Classe que executa o programa em uma aplicacao gráfica.
 * 
 * @author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023 
 */
public class GUI implements MouseListener, 
ActionListener, MouseMotionListener{
    public static int qtd = 50;
    public static int pas = 10;
    public static double qtdVel = 0.5;

    Simulacao _simular;

    Aplicacao app = new Aplicacao();

    JLabel []label = null;

    Color blue = new Color(176,224,230);
    Color red = new Color(255,149,149);
    Color green = new Color(144,238,144);

    Container pane = null;
    JPanel content = new JPanel();
    JPanel botoes = new JPanel();
    JPanel caixas = new JPanel();
    JFrame frame = null;

    JButton bIniciar = null;
    JButton bPausar = null;
    JButton bReiniciar = null;
    JButton bSair = null;
    JButton bAplicar = null;
    JButton bRelatorio = null;
    JButton bSobre = null;

    JLabel cQtd, cPassos, cVel, cSob;
    JTextField txfQtd, txfPassos, txfVel;
    JComboBox cbVel;

    /**
     * GUI Construtor
     *
     * @int qtd - parâmetro para quantidade de individuos
     * @int pas - parâmetro para contador de passos
     */
    GUI (int qtd, int pas){
        frame = new JFrame("Josephus Simulator");
        frame.setSize(750,750);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Dimension objDimension = Toolkit.getDefaultToolkit().getScreenSize();
        int iCoordX = (objDimension.width - frame.getWidth()) / 2;
        int iCoordY = (objDimension.height - frame.getHeight()) / 2;
        frame.setLocation(iCoordX, iCoordY); 

        app.simular(qtd,pas);

        label = new JLabel[qtd];

        content.setLayout(new GridLayout(12, 10, 2, 2));

        botoes = new JPanel();
        botoes.setLayout (new FlowLayout (FlowLayout.CENTER));
        caixas = new JPanel();
        caixas.setLayout (new FlowLayout(FlowLayout.CENTER, 35, 0));
    }

    /**
     * Método mostraGUI, inicia a inteface
     *
     */
    public void mostraGUI() {        
        JFrame.setDefaultLookAndFeelDecorated(true); // Estilo Java (default)
        //Define a janela e adiciona os componentes.
        adicionaComponentes();

        //Mostra a janela.
        frame.setVisible(true);
    }

    /**
     * Método adicionaComponentes
     *
     */
    public void adicionaComponentes(){
        // Pega o container da janela principal
        pane = frame.getContentPane();

        // Insere painel de ambiente e as celulas(jlabels)
        inserePainelJoses(pane);

        // Insere painel de botoes e os botoes 
        inserePainelBotoes(pane);
    }

    /**
     * Método inserePainelJoses
     *
     * @param pane - painel gŕafico
     */
    private void inserePainelJoses(Container pane){
        for (int i = 0; i < label.length; i++) {

            int j = i + 1;            
            label[i] = new JLabel("" + j, 0);
            label[i].setOpaque(true);
            label[i].setBackground(blue);

            content.add(label[i]);

        }
        pane.add(content);
    }

    /**
     * Método inserePainelBotoes
     *
     * @param pane - painel gráfico
     */
    private void inserePainelBotoes(Container pane){
        //cria os botoes (Iniciar, Pausar, Reiniciar, Relatorio, Sair e ?)
        bIniciar = new JButton ("Iniciar");
        bIniciar.setToolTipText("Inicia Simulacao");
        bIniciar.setPreferredSize(new Dimension(100,40));
        bIniciar.addMouseListener(this);

        bPausar = new JButton ("Pausar");
        bPausar.setToolTipText("Pausa Simulacao");
        bPausar.setPreferredSize(new Dimension(100,40));
        bPausar.setEnabled(false);
        bPausar.removeMouseListener(this);

        bReiniciar = new JButton ("Reiniciar");
        bReiniciar.setToolTipText("Reinicia Simulacao");
        bReiniciar.setPreferredSize(new Dimension(100,40));
        bReiniciar.addMouseListener(this);

        bAplicar = new JButton ("Aplicar");
        bAplicar.setToolTipText("Aplica as configuracoes");
        bAplicar.setPreferredSize(new Dimension(100,40));
        bAplicar.addMouseListener(this);

        bRelatorio = new JButton ("Relatorio");
        bRelatorio.setToolTipText("Ordem");
        bRelatorio.setPreferredSize(new Dimension(100,40));
        bRelatorio.setEnabled(false);
        bRelatorio.removeMouseListener(this);
        
        bSair = new JButton ("Sair");
        bSair.setToolTipText("Termina programa");
        bSair.setPreferredSize(new Dimension(100,40));
        bSair.addMouseListener(this);

        bSobre = new JButton("?");
        bSobre.setToolTipText("Sobre programa");
        
        bSobre.setBackground(new Color(163,182,188));
        bSobre.setForeground(Color.WHITE);
        bSobre.addMouseListener(this);
        
        //cria a parte de inserção da qtd de individuos, de velocidade e de passos
        cQtd = new JLabel("Individuos: ", JLabel.RIGHT);
        cQtd.setPreferredSize(new Dimension(100,40));
        cPassos = new JLabel("Passos: ", JLabel.RIGHT);
        cPassos.setPreferredSize(new Dimension(100,40));
        cVel = new JLabel("Velocidade: ", JLabel.RIGHT);
        cVel.setPreferredSize(new Dimension(100,40));

        txfQtd = new JTextField(3);
        txfQtd.setPreferredSize(new Dimension(100,27));
        txfPassos = new JTextField(3);
        txfPassos.setPreferredSize(new Dimension(100,27));

        String vel[] = {"0.5","0.75","1","1.25","1.5","1.75","2","2.25","2.5","2.75","3","3.25","3.5","3.75", "4","4.25","4.5","4.75",
                "5","5.25","5.5","5.75", "6","6.25","6.5","6.75", "7","7.25","7.5","7.75", "8","8.25","8.5","8.75", "9","9.25","9.5","9.75", "10"};
        cbVel = new JComboBox(vel);
        cbVel.addActionListener(this);
        
        //insere na interface os botoes
        botoes.add(bIniciar);
        botoes.add(bPausar);
        botoes.add(bReiniciar);
        botoes.add(bAplicar);
        botoes.add(bRelatorio);
        botoes.add(bSair);
        
        //insere na interface as caixas de texto e o painel de seleção
        caixas.add(cQtd);
        caixas.add(txfQtd);
        caixas.add(cPassos);
        caixas.add(txfPassos);
        caixas.add(cVel);
        caixas.add(cbVel);
        caixas.add(bSobre);
        
        //localizacao na interface
        pane.add("South", botoes);
        pane.add("North", caixas);
    }

    /**
     * Método mousePressed
     *
     * @MouseEvent E - parametro do envento do mouse
     */
    public void mousePressed(MouseEvent e) {
        Component c = e.getComponent();

        if (c instanceof JButton) {
            JButton jb = (JButton)c;
            if(jb.getText().equals("Iniciar")){
                iniciaSimulacao();
            }
            else if(jb.getText().equals("Pausar")){
                finalizaSimulacao();
            }
            else if(jb.getText().equals("Reiniciar")){
                reiniciaPainel(pane);
                inserePainelJoses(pane);
                inicializaBotoes();
            }
            else if(jb.getText().equals("Aplicar")){
                String ind = null;
                String passos = null;

                ind = txfQtd.getText();     //armazena a qtd de individuo digitada
                passos = txfPassos.getText();  //armazena a qtd de passos digitada
               
                if(!ind.equals("") && passos.equals("")){
                    try{
                        qtd = Integer.parseInt(txfQtd.getText());

                        if(qtd < 2 || qtd > 120){
                            JOptionPane.showMessageDialog(null, "Apenas numeros de 2 a 120");
                            qtd = 50;
                            qtdVel = 0.5;
                        }
                    }catch(NumberFormatException err){
                        JOptionPane.showMessageDialog(null, "Apenas Numeros!!");
                    }
                }
                else if(ind.equals("") && !passos.equals("")){
                    try{
                        pas = Integer.parseInt(txfPassos.getText());

                        if(pas < 1 || pas > qtd){
                            JOptionPane.showMessageDialog(null, "Apenas numeros de 2 a individuos");
                            pas = 10;
                            qtdVel = 0.5;
                        }
                    }catch(NumberFormatException err){
                        JOptionPane.showMessageDialog(null, "Apenas Numeros!!");
                    }
                }
                else if(!ind.equals("") && !passos.equals("")){
                    try{
                        qtd = Integer.parseInt(txfQtd.getText());
                        pas = Integer.parseInt(txfPassos.getText());

                        if(qtd < 2 || qtd > 120){
                            JOptionPane.showMessageDialog(null, "Apenas numeros de 2 a 120");
                            qtd = 50;
                            pas = 10;
                            qtdVel = 0.5;
                        }
                        if(pas < 1 || pas > qtd){
                            JOptionPane.showMessageDialog(null, "Apenas numeros de 2 a individuos");
                            qtd = 50;
                            pas = 10;
                            qtdVel = 0.5;
                        }
                    }catch(NumberFormatException err){
                        JOptionPane.showMessageDialog(null, "Apenas Numeros!!");
                    } 
                }
                
                app.simular(qtd, pas); 
            }else if(jb.getText().equals("?")){
                JOptionPane.showMessageDialog(null,"===============Josephus===============\n" + 
                                                   " Um programa que implementa o algoritmo\n" +
                                                   " de Josephus com Lista Duplamente\n" + 
                                                   " Ligada Circular");
            }else if(jb.getText().equals("Relatorio")){
                imprimirRelatorio();
            }else if(jb.getText().equals("Sair")){
                System.exit(0);
            }
        }
    }
    
    /**
     * Classe que executa a simulação com Threads para interrupções
     *
     */
    private class Simulacao extends Thread {
        public boolean continuar = true;

        /**
         * Método run, executa a simulação
         *
         */
        public void run() {
            try {
                int morreu = 0;
                int i = 0;
                int time;
                
                //loop que verifica tambem se _simular eh null, para assim conseguir pausar o programa
                while(_simular != null && i < label.length-1){
                    morreu = (int) app.getVetor().buscar(i);  //pega cada valor armazenado no array
                    label[morreu-1].setBackground(red);       

                    time = (int) (qtdVel * 1000);

                    TimeUnit.MILLISECONDS.sleep(time);
                    i++;
                }

                if(_simular != null){
                    morreu = (int) app.getVetor().buscar(label.length-1);   //pega o ultimo valor armazenado(o que sobrou)
                    label[morreu-1].setBackground(green);
                    
                    reiniciaSimulacao();  //funcao que reinicia os botoes
                }

            } catch (InterruptedException e) {
                JOptionPane.showMessageDialog(null, "ERRO INESPERADO");
                System.exit(0);
            }   
        }
    }

    /**
     * Método iniciaSimulacao
     *
     */
    public void iniciaSimulacao() {
        _simular = new Simulacao();
        _simular.start();   //inicia a funcao
        
        bIniciar.setEnabled(false);
        bIniciar.removeMouseListener(this);

        bPausar.setEnabled(true);
        bPausar.addMouseListener(this);

        bReiniciar.setEnabled(false);
        bReiniciar.removeMouseListener(this);
        
        bRelatorio.setEnabled(false);
        bRelatorio.removeMouseListener(this);
        
        bAplicar.setEnabled(false);
        bAplicar.removeMouseListener(this);
    }

    /**
     * Método finalizaSimulacao
     *
     */
    public void finalizaSimulacao() {

        bIniciar.setEnabled(true);
        bIniciar.addMouseListener(this);

        bPausar.setEnabled(false);
        bPausar.removeMouseListener(this);

        bReiniciar.setEnabled(true);
        bReiniciar.addMouseListener(this);

        bAplicar.setEnabled(true);
        bAplicar.addMouseListener(this);
        
        bRelatorio.setEnabled(true);
        bRelatorio.addMouseListener(this);
        
        _simular = null;
    }

    /**
     * Método reiniciaSimulacao
     *
     */
    public void reiniciaSimulacao(){
        bPausar.setEnabled(false);
        bPausar.removeMouseListener(this);

        bReiniciar.setEnabled(true);
        bReiniciar.addMouseListener(this);
        
        bRelatorio.setEnabled(true);
        bRelatorio.addMouseListener(this);
    }
    
    /**
     * Método inicializaBotoes
     *
     */
    public void inicializaBotoes(){
        bIniciar.setEnabled(true);
        bIniciar.addMouseListener(this);

        bPausar.setEnabled(false);
        bPausar.removeMouseListener(this);

        bAplicar.setEnabled(true);
        bAplicar.addMouseListener(this);
        
        bRelatorio.setEnabled(false);
        bRelatorio.removeMouseListener(this);
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseDragged(java.awt.event.MouseEvent)
     */
    public void mouseDragged(MouseEvent e) {
    }

    /* (non-Javadoc)
     * @see java.awt.event.MouseMotionListener#mouseMoved(java.awt.event.MouseEvent)
     */
    public void mouseMoved(MouseEvent e) {
    }

    /**
     * Método actionPerformed
     *
     * @ActionEvente e - ação de alterar a velocidade
     */
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == cbVel){
            String item = cbVel.getSelectedItem().toString();
            qtdVel = Double.parseDouble(item);
        }
    }    

    /**
     * Método reiniciaPainel
     *
     * @param pane - painel gráfico
     */
    private void reiniciaPainel(Container pane){
        //permite que muda a qtd de individuos na interface (numeros de retangulos)
        content.revalidate();
        content.repaint();
        content.removeAll();

        label = new JLabel[qtd];
    }
    
    private void imprimirRelatorio(){
        String txt = "Ordem: ";
        txt = txt + app.getVetor().toString() + "\nSobrou: " + app.getVetor().buscar(qtd-1);
        JOptionPane.showMessageDialog(null, txt);
    }
}