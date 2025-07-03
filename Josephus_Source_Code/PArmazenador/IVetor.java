package PArmazenador;

/**
 * IArmazenador interface para armazenadores.
 * 
 * @author Julio Arakaki
 * @co-author Igor Matos, João Pedro Figols, Julia Schmidt, Leonardo Grupioni 
 * @version 13/06/2023
 */

public interface IVetor { 
    public void adicionar(Object a);
    public Object remover(int i);
    public boolean estaVazia();
    public Object buscar (int i);
    public int getQtd();
}
