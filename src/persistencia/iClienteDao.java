/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package persistencia;

import java.util.ArrayList;
import modelos.Cliente;

/**
 *
 * @author ALUNO
 */
public interface iClienteDao {

    void incluir(Cliente objeto) throws Exception;
    void alterar(Cliente objeto,int id) throws Exception;
    Cliente consultar(int id) throws Exception;
    void excluir(int id) throws Exception;
    ArrayList<Cliente> obterClientes()throws Exception;

}
