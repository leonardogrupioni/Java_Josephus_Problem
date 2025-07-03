
package PArmazenador;

import java.io.Serializable;
/**
 * "simula" um array dinamico.
 * 
 * @author Julio Arakaki
 * @co-author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */
public class VetDin implements IVetor{

    // Atributos
    // array para armazenar qquer objeto
    private Object array[]; 
    // quantidade de objetos no vetor
    private int qtd;

    /**
     * VetDin Construtor - inicia um array e seta a quantidade
     *
     */
    public VetDin(){
        setArray(null);
        setQtd(0);
    }

    /**
     * retorna array de Object
     * 
     * @return vetor de Object
     */
    private Object[] getArray() {
        return array;
    }

    /**
     * Retorna a quntidade
     * 
     * @return int, quantidade de elementos no array
     */
    public int getQtd() {
        return qtd;
    }

    /**
     * @param vet the vet to set
     */
    private void setArray(Object[] array) {
        this.array = array;
    }

    /**
     * @param qtd the qtd to set
     */
    private void setQtd(int qtd) {
        this.qtd = qtd;
    }

    /**
     * MÃ©todo adicionar
     *
     * @param obj Um parÃ¢metro
     */
    public void adicionar(Object obj){
        if (array == null){ // se for o primeiro elemento          
            setArray(new Object[1]);
            array[0] = obj; 
            setQtd(getQtd()+1);
        }
        else { // outros elementos
            // cria vetor auxiliar com mais um elemento
            Object aux[] = new Object[array.length+1];

            // copia todos elementos de vet para aux
            copiar(array, aux);

            // insere elemento novo
            aux[aux.length-1] = obj;

            // Transforma vetor auxiliar no atual
            setArray(aux);

            // incrementa contador de elementos    
            setQtd(getQtd()+1);

        }
    }

    /**
     * MÃ©todo remover
     *
     * @param i Um parÃ¢metro
     */
    public Object remover(int i) {
        Object ret = null;
        if(buscar(i) != null){
            ret = array[i];
            // Libera elemento da sua posicao
            array[i] = null;

            if(getQtd() > 1){
                // cria vetor auxiliar com mes um elemento
                Object aux[] = new Object[array.length-1];

                // copia todos elementos de vet para aux
                copiar(array, aux);

                // Transforma vetor auxiliar no atual
                setArray(aux);
                // decrementa contador de elementos    
                setQtd(getQtd() - 1);
            } else {
                // acabou os elementos
                setArray(null);
                // decrementa contador de elementos    
                setQtd(0);

            }
        }
        return ret;
    }

    /**
     * MÃ©todo buscar
     *
     * @param i Um parÃ¢metro
     * @return O valor de retorno
     */
    public Object buscar (int i){
        Object ret = null;
        if(array != null && (i >= 0 && i < getQtd())) {
            ret = array[i];
        }
        return ret;
    }

    /**
     * MÃ©todo vazia
     *
     * @return O valor de retorno
     */
    public boolean estaVazia(){
        return (getQtd()==0 && getArray() == null);
    }

    /**
     * MÃ©todo copiar
     *
     * @param origem Um parÃ¢metro
     * @param destino Um parÃ¢metro
     */
    private void copiar(Object origem[], Object destino[]){
        // copia todos
        int i, k = 0;
        for (i = 0; i < origem.length; i++){
            if (origem[i] != null) {
                destino[k] = origem[i];
                k++;
            }
        }       
    }

    /**
     * MÃ©todo toString
     *
     * @return O valor de retorno
     */
    public String toString(){
        String s = "[ ";
        if(array != null){
            for (int i = 0; i < array.length; i++){
                s += array[i].toString() + " ";
            }
        }
        s = s + "]";
        return s;
    }
}