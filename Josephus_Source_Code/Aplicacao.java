
/**
 * A aplicação conecta a aplicação grafica com a execucao da simulacao.
 * 
 * @author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */
public class Aplicacao {
    Regras regras = new Regras();   
    
    /**
     * Método simular
     *
     * @int qtd - parâmetro para quantidade de objetos
     * @int pas - parâmetro para contador de passos
     */    
    public void simular(int qtd, int m){
        // inicializa variáveis de instância
        regras.simular(qtd, m);
    }
    
    /**
     * Método getVetor
     *
     * @retorna um VetDin que contem a ordem
     */
    public PArmazenador.IVetor getVetor(){
        return regras.vetor;
    } 

}
