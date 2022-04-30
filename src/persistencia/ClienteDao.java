package persistencia;

import java.util.ArrayList;
import modelos.Cliente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ClienteDao implements iClienteDao {

    private String nomeDoArquivoNoDisco = "Cliente.txt";

    @Override
    public void incluir(Cliente objeto) throws Exception {
        try {
            int id = GeradorIdentificador.getID();
            objeto.setId(id);
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(objeto.toString() + "\n");
            bw.close();

        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public void alterar(Cliente objeto, int id) throws Exception {

        excluir(id);
        incluir(objeto);

    }

    @Override
    public void excluir(int id) throws Exception {
        try {
            ArrayList<Cliente> listaDeclientes = null;
            listaDeclientes = obterClientes();
            FileWriter fw = new FileWriter(nomeDoArquivoNoDisco);
            BufferedWriter bw = new BufferedWriter(fw);
            for (int i = 0; i < listaDeclientes.size(); i++) {

                if (listaDeclientes.get(i).getId() != id) {
                    bw.write(listaDeclientes.get(i).toString() + "\n");
                }
            }
            bw.close();
        } catch (Exception erro) {

            throw erro;
        }

    }

    @Override
    public Cliente consultar(int id) throws Exception {
        try {
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";
            while ((linha = br.readLine()) != null) {
                Cliente objCliente = new Cliente();
                String vetorString[] = linha.split(";");
                if (vetorString.length != 5) {
                    throw new Exception("Faltam dados na String");
                }
                objCliente.setId(Integer.parseInt(vetorString[0]));
                objCliente.setNomeCompleto(vetorString[1]);
                objCliente.setTelefone(Integer.parseInt(vetorString[2]));
                objCliente.setEmail(vetorString[3]);
                objCliente.setEndereco(vetorString[4]);
                if (objCliente.getId() == id) {
                    br.close();
                    return objCliente;
                }
            }
            br.close();
            throw new Exception("ID não existe");
        } catch (Exception erro) {
            throw erro;
        }
    }

    @Override
    public ArrayList<Cliente> obterClientes() throws Exception {
        try {
            ArrayList<Cliente> listaDeclientes = new ArrayList<Cliente>();
            FileReader fr = new FileReader(nomeDoArquivoNoDisco);
            BufferedReader br = new BufferedReader(fr);
            String linha = "";

            while ((linha = br.readLine()) != null) {
                Cliente obCliente = new Cliente();
                String vetorString[] = linha.split(";");
                if (vetorString.length != 5) {
                    throw new Exception("Faltam dados na String");
                }
                obCliente.setId(Integer.parseInt(vetorString[0]));
                obCliente.setNomeCompleto(vetorString[1]);
                obCliente.setTelefone(Integer.parseInt(vetorString[2]));
                obCliente.setEmail(vetorString[3]);
                obCliente.setEndereco(vetorString[4]);
                listaDeclientes.add(obCliente);
            }
            br.close();
            return listaDeclientes;
        } catch (Exception erro) {
            throw erro;
        }
    }

}
