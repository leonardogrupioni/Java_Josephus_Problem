import PArmazenador.*;
/**
 * As regras executam a simulação de acordo com os parametro passados de qtd e passos
 * 
 * @author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */
public class Regras
{
    public IArmazenador arm = new ListaDuplamenteLigadaCircular();
    public IVetor vetor = new VetDin();
    
    /**
     * Método simular
     *
     * @int qtd - parâmetro para quantidade de individuos
     * @int m - parâmetro para contador de passos
     */
    public void simular(int qtd, int m){
        inicializarArray();
        criarIndividuos(qtd);
        matarIndividuos(qtd,m); 
        ultimoIndividuo(qtd);
    }
        
    /**
     * Método criarIndividuos
     *
     * @int qtd - parâmetro para quantidade de individuos
     */
    private void criarIndividuos(int qtd){
        for(int i = 0; i < qtd; i++){
            Individuo individuo = new Individuo(true, i);
            arm.inserirFim(individuo);
        }        
    }
    
    /**
     * Método matarIndividuos
     *
     * @int qtd - parâmetro para quantidade de individuos
     * @int m - parâmetro para contador de passos
     */
    private void matarIndividuos(int qtd, int m){
        int morto = 0;
        Individuo vaiMorre = (Individuo) arm.buscar(0);
        int count = 0;
        int j = 0;
        int k = 0;
        for(int i = 0; i < qtd-1; i++){
            count = 0;
            while(count != m){
                vaiMorre = (Individuo) arm.buscar(j);
                if(vaiMorre.getStatus()){
                    count++; 
                }
                j++;
                j = j % qtd;
                //if(j == 0) j++;
            }
            vaiMorre.setStatus(false);
            k = j;
            if(k == 0) k = qtd; 
            vetor.adicionar(k);
        }
    }
    
    /**
     * Método ultimoIndividuo
     *
     * @int qtd - parâmetro para quantidade de individuos
     */
    private void ultimoIndividuo(int qtd){
        Individuo ultimo = (Individuo) arm.buscar(0);
        int count = 0;
        int vivo = 0;
        int sinal = 0;
        while(count < qtd && sinal == 0){
            ultimo = (Individuo) arm.buscar(count);
            count++;
            if(ultimo.getStatus()){
                vivo = count;
                sinal = 1;
            }
        }
        vetor.adicionar(vivo);
    }
    
    /**
     * Método inicializarArray
     *
     */
    public void inicializarArray(){
        arm = new ListaDuplamenteLigadaCircular();
        vetor = new VetDin();
    }
}
