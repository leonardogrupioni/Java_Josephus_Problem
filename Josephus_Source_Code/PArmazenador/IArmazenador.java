package PArmazenador;

/**
 * IArmazenador interface para armazenadores.
 * 
 * @author Julio Arakaki
 * @co-author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */

public interface IArmazenador {
    public boolean estaVazia(); 
    
    public void inserirInicio(Object novo); 

    public void inserirFim(Object novo);
    
    public boolean inserirApos(long chave, Object novo);

    public Object removerInicio();

    public Object removerFim();
    
    public Object remover(long chave);
    
    public Object buscar(int i);
    
    public int getQtdNos();
    
    public String toStringDoFim();
}

