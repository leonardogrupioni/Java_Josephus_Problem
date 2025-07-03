
/**
 * A classe individuo cria um individuo que contem um boolean status que informa se o mesmo esta vivo ou morto;
 * 
 * @author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */
public class Individuo
{
    private boolean status; //true ou false para vivo ou morto.
    
    /**
     * Construtor para objetos da classe Pessoa
     */
    public Individuo(boolean status, int pos){
        setStatus(status);
    }
    
    // Setters e Getters
    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus(){
        return this.status;
    }
    
}
